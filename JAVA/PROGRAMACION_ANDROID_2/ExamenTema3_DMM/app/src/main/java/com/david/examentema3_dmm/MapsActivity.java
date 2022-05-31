package com.david.examentema3_dmm;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static int PETICION_PERMISO_LOCALIZACION = 101;
    private GoogleMap mMap;
    double lat = 0.0, lng = 0.0;
    LatLng coordenadas;
    private Marker marcador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

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

        // AÑADIMOS LOS MARCADORES DE LOS HOSPITALES
        LatLng vistaMorelosChalco = new LatLng(19.1027391, -99.0470195);
        CameraUpdate ubicacionCam = CameraUpdateFactory.newLatLngZoom(vistaMorelosChalco, 10);
        mMap.animateCamera(ubicacionCam);

        // HOSPITALES
        LatLng Hosp_1 = new LatLng(18.836994, -99.238821);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))
                .anchor(0.0f, 1.0f).position(Hosp_1)).setTitle("Hospital General de Temixco");

        LatLng Hosp_2 = new LatLng(18.917446, -99.210824);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))
                .anchor(0.0f, 1.0f).position(Hosp_2)).setTitle("Hospital Morelos SA de CV");

        LatLng Hosp_3 = new LatLng(18.935889, -99.233538);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))
                .anchor(0.0f, 1.0f).position(Hosp_3)).setTitle("Hospital General de Cuernavaca \"Dr. José G. Parres\"");

        LatLng Hosp_4 = new LatLng(18.942808, -99.210098);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))
                .anchor(0.0f, 1.0f).position(Hosp_4)).setTitle("Hospital San Diego");

        LatLng Hosp_5 = new LatLng(18.902792, -99.232020);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))
                .anchor(0.0f, 1.0f).position(Hosp_5)).setTitle("Torre Médica Cuernavaca");

        checarPermisoyActualizaUbi();
    }

    private void checarPermisoyActualizaUbi(){
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
            actualizarLocalizacion(location);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 0, locationListener);
            mMap.setMyLocationEnabled(true);
            mMap.setIndoorEnabled(true);
        }
    }

    private void Marcador(double lat, double lng){
        coordenadas = new LatLng(lat, lng);

        if(marcador != null) marcador.remove();

        marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).
                title("Mi Ubicación").
                icon(BitmapDescriptorFactory.fromResource(R.drawable.user)));
    }

    private void actualizarLocalizacion(Location location){
        if(location != null){
            lat = location.getLatitude();
            lng = location.getLongitude();
            Marcador(lat, lng);
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarLocalizacion(location);
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
            Toast.makeText(getApplicationContext(),
                    "GPS DESACTIVADO, ACTIVALO PARA LA FUNCIONALIDAD DE LA APP", Toast.LENGTH_LONG).show();
            activarGPS();
        }
    };

    private void activarGPS(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsActivado = locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);
        if(!gpsActivado){
            Intent configIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(configIntent);
        }
    }
}