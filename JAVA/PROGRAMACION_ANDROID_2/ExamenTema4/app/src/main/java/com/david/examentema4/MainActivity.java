package com.david.examentema4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.david.examentema4.modulos.bluetooth.Bluetooth_basic;
import com.david.examentema4.modulos.wifi.Wifi_basic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class MainActivity extends AppCompatActivity {
    // CLASES DE FUNCIONALIDAD
    Bluetooth_basic bluetooth_basic;
    Wifi_basic wifi_basic;

    BluetoothAdapter myBluetooth;

    // OBJETOS
    ScrollView caseBluetooth, caseWiFi;
    // WiFi
    Button WiFiBuscar, WiFiGuardar, WiFiMostrar;
    TextView tvEstado, tvRedes;
    // Bluetooth
    Button BluetoothVisible, BluetoothDispositivos;
    TextView tvDispositivos;

    private Boolean ifBlue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enlaces();

        caseWiFi.setVisibility(View.GONE);
        caseBluetooth.setVisibility(View.GONE);

        // INSTANCIA DEL ADAPTADOR DEL BLUETOOTH
        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if(myBluetooth == null){
            toast("El dispositivo no cuenta con Bluetooth :c");
            ifBlue = false;
        }
        else{
            toast("Bluetooth ENCONTRADO :D");
            ifBlue = true;
        }

        bluetooth_basic = new Bluetooth_basic(this, this, myBluetooth, tvDispositivos);
        wifi_basic = new Wifi_basic(this, tvEstado, tvRedes);

        // OYENTES DE LOS BOTONES
        // WIFI *****************************************************************************
        WiFiBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifi_basic.buscarWifi();
            }
        });

        WiFiGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifi_basic.guardarWifi();
            }
        });

        WiFiMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifi_basic.mostrarWifi();
            }
        });

        // BLUETOOTH *****************************************************************************
        BluetoothVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetooth_basic.BluetoothVisible();
            }
        });

        BluetoothDispositivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetooth_basic.BluettothDispEmparejados();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_examen, menu);
        return true;
    }

    // FUNCIONALIDAD DE OPCIONES
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.WiFiOn:
                // VERIFICAMOS QUE EL WIFI SE ENCUENTRE APAGADO
                if(!wifi_basic.verifyWifi()){
                    wifi_basic.encenderWifi();
                    caseWiFi.setVisibility(View.VISIBLE);
                    caseBluetooth.setVisibility(View.GONE);
                }else{
                    toast("El WiFi ya se encuentra encendido");
                    caseWiFi.setVisibility(View.VISIBLE);
                    caseBluetooth.setVisibility(View.GONE);
                }
                break;
            case R.id.BluetoothOn:
                // VERIFICAMOS QUE EL BLUETOOTH SE ENCUENTRE APAGADO
                if(ifBlue){
                    if(!bluetooth_basic.verifyBluetooth()){
                        bluetooth_basic.BluetoothOn();
                        caseBluetooth.setVisibility(View.VISIBLE);
                        caseWiFi.setVisibility(View.GONE);
                    }else{
                        toast("El Bluetooth ya se encuentra encendido");
                        caseBluetooth.setVisibility(View.VISIBLE);
                        caseWiFi.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.WiFiOff:
                // VERIFICAMOS QUE EL WIFI SE ENCUENTRE ENCENDIDO
                if(wifi_basic.verifyWifi()){
                    wifi_basic.apagarWifi();
                    caseWiFi.setVisibility(View.GONE);
                    caseBluetooth.setVisibility(View.GONE);
                }else{
                    toast("El WiFi ya se encuentra apagado");
                }
                break;
            case R.id.BluetoothOff:
                if(ifBlue){
                    // VERIFICAMOS QUE EL BLUETOOTH SE ENCUENTRE ENCENDIDO
                    if(bluetooth_basic.verifyBluetooth()){
                        bluetooth_basic.BluetoothOff();
                        caseBluetooth.setVisibility(View.GONE);
                        caseWiFi.setVisibility(View.GONE);
                    }else{
                        toast("El Bluetooth ya se encuentra apagado");
                    }
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch(requestCode){
            case 0:
                if(resultCode == RESULT_OK){
                    toast("Encendido");
                }
                else{
                    toast("No reesponde el Bluetooth");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void toast(String mensaje){ Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show(); }
    private void enlaces(){
        // ENLACES CON LA VISTA
        caseBluetooth = (ScrollView) findViewById(R.id.caseBluetooth);
        caseWiFi = (ScrollView) findViewById(R.id.caseWiFi);
        WiFiBuscar = (Button) findViewById(R.id.WiFiBuscar);
        WiFiGuardar = (Button) findViewById(R.id.WiFiGuardar);
        WiFiMostrar = (Button) findViewById(R.id.WiFiMostrar);
        BluetoothVisible = (Button) findViewById(R.id.BluetoothVisible);
        BluetoothDispositivos = (Button) findViewById(R.id.BluetoothDispositivos);
        tvEstado = (TextView) findViewById(R.id.tvEstado);
        tvRedes = (TextView) findViewById(R.id.tvEstado);
        tvDispositivos = (TextView) findViewById(R.id.tvDispositivos);
    }
}