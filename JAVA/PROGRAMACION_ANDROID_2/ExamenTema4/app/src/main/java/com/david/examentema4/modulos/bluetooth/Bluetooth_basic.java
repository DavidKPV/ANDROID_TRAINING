package com.david.examentema4.modulos.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Set;

public class Bluetooth_basic {
    // CLASES Y OBJETOS
    Context contexto;
    BluetoothAdapter myBluetooth;
    Activity activity;
    TextView tvDispositivos;

    public Bluetooth_basic(Context contexto, Activity activity, BluetoothAdapter myBluetooth, TextView tvDispositivos) {
        this.myBluetooth = myBluetooth;
        this.contexto = contexto;
        this.activity = activity;
        this.tvDispositivos = tvDispositivos;
    }
    public boolean verifyBluetooth() {
        if(myBluetooth.isEnabled()){
            return true;
        } else {
            return false;
        }
    }
    public void BluetoothOn() {
        toast("Encendiendo Bluetooth...");
        Intent intencionEncender = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(intencionEncender, 0);
    }

    public void BluetoothOff() {
        toast("Apagando Bluetooth...");
        myBluetooth.disable();
    }

    public void BluetoothVisible() {
        if (!myBluetooth.isDiscovering()) {
            toast("Bluetooth visible");
            Intent intencionVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            activity.startActivityForResult(intencionVisible, 1);
        } else {
            toast("El Bluetooth ya está visible");
        }
    }

    public void BluettothDispEmparejados() {
        tvDispositivos.setText("Dispositivos Emparejados\n");
        Set<BluetoothDevice> dispositivos = myBluetooth.getBondedDevices();
        for (BluetoothDevice dispositivo : dispositivos) {
            tvDispositivos.append("\nDispositivo: " + dispositivo.getName() + ", " +
                    dispositivo.getAddress() + ", " + dispositivo.getBondState() +
                    ", " + dispositivo.getUuids() + "\n");
        }
    }

    private void toast(String mensaje) {
        Toast.makeText(contexto, mensaje, Toast.LENGTH_LONG).show();
    }
}
