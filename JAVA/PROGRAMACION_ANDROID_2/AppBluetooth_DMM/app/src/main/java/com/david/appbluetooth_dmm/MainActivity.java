package com.david.appbluetooth_dmm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    // OBJETOS
    ImageView ivEstado;
    TextView tvDispEmp;
    BluetoothAdapter myBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ENLACES CON LA VISTA
        ivEstado = (ImageView) findViewById(R.id.ivEstado);
        tvDispEmp = (TextView) findViewById(R.id.tvDispEmp);

        // INSTANCIA DEL ADAPTADOR DEL BLUETOOTH
        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if(myBluetooth == null){
            toast("El dispositivo no cuenta con Bluetooth :c");
            ivEstado.setImageResource(R.drawable.noblue);
        }
        else{
            toast("Bluetooth ENCONTRADO :D");
            ivEstado.setImageResource(R.drawable.siblue);
        }
    }

    private void toast(String texto){
        Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflar = getMenuInflater();
        inflar.inflate(R.menu.opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean estado = false;
        switch (item.getItemId()){
            case R.id.idBluetoothEncender:
                if(!myBluetooth.isEnabled()){
                    toast("Encendiendo Bluetooth...");
                    Intent intencionEncender = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intencionEncender, 0);
                    ivEstado.setImageResource(R.drawable.encendido);
                }
                else{
                    toast("El Bluetooth ya está encendido");
                }
                estado = true;
                break;
            case R.id.idBluetoothVisible:
                if(!myBluetooth.isDiscovering()){
                    toast("Bluetooth visible");
                    Intent intencionVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intencionVisible, 1);
                    ivEstado.setImageResource(R.drawable.visible);
                }
                else{
                    toast("El Bluetooth ya está visible");
                }
                estado = true;
                break;
            case R.id.idBluetoothApagar:
                if(myBluetooth.isEnabled()){
                    toast("Apagando Bluetooth...");myBluetooth.disable();
                    ivEstado.setImageResource(R.drawable.apagado); }
                else{
                    toast("El Bluetooth ya está apgado"); }
                estado = true;
                break;
            case R.id.idDispEmparejados:
                if(myBluetooth.isEnabled()){
                    tvDispEmp.setText("Dispositivos Emparejados\n");
                    Set<BluetoothDevice> dispositivos = myBluetooth.getBondedDevices();
                    for(BluetoothDevice dispositivo:dispositivos){
                        tvDispEmp.append("\nDispositivo: "+dispositivo.getName()+", "+
                                dispositivo.getAddress()+", "+dispositivo.getBondState()+
                                ", "+dispositivo.getUuids()+"\n");
                    }
                }
                else{
                    toast("Primero debes de encender el Bluetooth :D");
                }
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch(requestCode){
            case 0:
                if(resultCode == RESULT_OK){
                    ivEstado.setImageResource(R.drawable.encendido);
                    toast("Encendido");
                }
                else{
                    toast("No reesponde el Bluetooth");
                    ivEstado.setImageResource(R.drawable.apagado);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}