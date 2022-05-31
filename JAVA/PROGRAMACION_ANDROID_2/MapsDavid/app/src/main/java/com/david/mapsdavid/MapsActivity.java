package com.david.mapsdavid;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {
    private static int PETICION_PERMISO_LOCALIZACION = 101;
    private GoogleMap mMap;
    Button var;
    double lat = 0.0, lng = 0.0;
    LatLng coordenadas;
    private Marker marcador;
    String calle2 = "";
    TextView calle;
    Button lugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        lugares = (Button) findViewById(R.id.blocalizacion);
        calle = (TextView) findViewById(R.id.tvlocalizacion);
        calle.setText(calle2);
        lugares.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // CONFIGURACIONES
        UiSettings settings =  mMap.getUiSettings();
        settings.setCompassEnabled(true);
        settings.setAllGesturesEnabled(true);
        settings.setIndoorLevelPickerEnabled(true);
        settings.setMapToolbarEnabled(true);
        settings.setMyLocationButtonEnabled(true);
        settings.setRotateGesturesEnabled(true);
        settings.setScrollGesturesEnabled(true);
        settings.setScrollGesturesEnabledDuringRotateOrZoom(true);
        settings.setTiltGesturesEnabled(true);
        settings.setZoomControlsEnabled(true);
        settings.setZoomGesturesEnabled(true);
        // AÑADIMOS UN MARCADOR CODA QUE HAYA UN CLIC LARGO
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ave))
                .anchor(0.0f, 1.0f).position(latLng)).setTitle(""+latLng);
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getApplicationContext(), "click "+marker.getPosition(), Toast.LENGTH_LONG).show();
                return true;
            }
        });
        direccionAct();
    }



    private void direccionAct(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PETICION_PERMISO_LOCALIZACION);
            return;
        }else{
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            updatelocation(location);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 0, locationListener);
            mMap.setMyLocationEnabled(true);
            mMap.setIndoorEnabled(true);
        }
    }

    private void Marcador(double lat, double lng){
        coordenadas = new LatLng(lat, lng);
        CameraUpdate ubicacionCam = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);

        if(marcador != null) marcador.remove();

        marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).title("Dirección: "+calle2+"("+coordenadas+")")
                .icon(BitmapDescriptorFactory.defaultMarker()));
        mMap.animateCamera(ubicacionCam);
    }

    private void updatelocation(Location location){
        if(location != null){
            lat = location.getLatitude();
            lng = location.getLongitude();
            Marcador(lat, lng);
            guardar();
        }
    }
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updatelocation(location);
            setLocation(location);
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(getApplicationContext(), "GPS ACTIVADO", Toast.LENGTH_LONG).show();
        }
        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(getApplicationContext(), "GPS DESACTIVADO", Toast.LENGTH_LONG).show();
            locationStart();
        }
    };

    public void guardar(){
        calle.setText(calle2);
        Sqllocalizacion lugar = new Sqllocalizacion(this, "ubicaciones", null, 1);
        SQLiteDatabase db = lugar.getWritableDatabase();
        String direccionCon = calle2;
        Double latit = coordenadas.latitude;
        Double longit = coordenadas.longitude;
        ContentValues valores = new ContentValues();
        valores.put("calle", direccionCon);
        valores.put("latitud", latit);
        valores.put("longitud", longit);
        db.insert("ubicaciones", null, valores);
        db.close();
    }
    public void setLocation(Location location){
        if (location.getLatitude() != 0.0 && location.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),
                        1
                );
                if(!list.isEmpty()){
                    Address DirCalle = list.get(0);
                    calle2 = (DirCalle.getAddressLine(0));
                }
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    private void locationStart(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsActivado = locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);
        if(!gpsActivado){
            Intent configIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(configIntent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.blocalizacion:
                Intent verUbicacion = new Intent(MapsActivity.this, UbicacionActivity.class);
                startActivity(verUbicacion);
                break;
        }
    }
}