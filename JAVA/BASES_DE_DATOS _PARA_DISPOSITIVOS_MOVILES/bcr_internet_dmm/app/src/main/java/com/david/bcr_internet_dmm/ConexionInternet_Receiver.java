package com.david.bcr_internet_dmm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;

public class ConexionInternet_Receiver extends BroadcastReceiver {
    public ConexionInternet_Receiver(){
        // ...
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            // CHECAMOS SI LA ACTIVIDAD ESTÁ VISIBLE O NO
            boolean isVisible = MyApplication.isActivityVisible();
            int estadoWifi = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);

            // SI ES VISIBLE EJECUTAMOS LA TAREA DEL BROADCAST
            if(isVisible){
                // CHECANMOS LA CONEXIÓN A INTERNET Y SE COLOCA EL ESTADO DENTRO DEL TEXTVIEW
                if(estadoWifi == WifiManager.WIFI_STATE_ENABLED){
                    new MainActivity().checarEstado(true);
                }else{
                    new MainActivity().checarEstado(false);
                }
            }
        }catch (Exception e){
            Log.e("Excepcion",""+e);
        }
    }
}
