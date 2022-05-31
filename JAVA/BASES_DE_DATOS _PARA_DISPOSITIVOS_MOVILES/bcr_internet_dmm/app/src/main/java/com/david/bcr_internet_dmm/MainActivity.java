package com.david.bcr_internet_dmm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    // OBJETOS
    public static TextView tvResult;
    ConexionInternet_Receiver conexionInternet_receiver = new ConexionInternet_Receiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ENLACES
        tvResult = (TextView) findViewById(R.id.tvResult);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // DARÁ EL VALOR FALSE CUANDO LA ACTIVIDAD ESTÉ EN PAUSA (NO VISIBLE)
        MyApplication.activityPaused();
        unregisterReceiver(conexionInternet_receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // DARÁ EL VALOR TRUE CUANDO LA ACTIVIDAD ESTÉ EN RESUMEN (VISIBLE)
        MyApplication.activityResumed();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        this.registerReceiver(conexionInternet_receiver, intentFilter);
    }

    public static void checarEstado(boolean isVisible){
        if(isVisible){
            tvResult.setText("Internet CONECTADO");
            tvResult.setTextColor(Color.GREEN);
        }else{
            tvResult.setText("Internet DESCONECTADO");
            tvResult.setTextColor(Color.RED);
        }
    }
}