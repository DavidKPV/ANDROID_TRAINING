package com.david.operaciones_wifi_dmm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // OBJETOS
    WifiManager wifi;
    Button btnEncender, btnApagar, btnBuscar, btnGuardar, btnMostrar;
    TextView tvResult, tvRedes;

    boolean tarea = false;
    int contador = 0;
    int encendidoApagado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ENLACES
        btnEncender = (Button) findViewById(R.id.btnEncender);
        btnApagar = (Button) findViewById(R.id.btnApagar);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        tvResult = (TextView) findViewById(R.id.tvResult);
        tvRedes = (TextView) findViewById(R.id.tvRedes);

        wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        verificarStatus();

        // OYENTES DE LOS BOTONES
        btnEncender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encenderWifi();
            }
        });

        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apagarWifi();
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarWifi();
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarWifi();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarWifi();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        guardarStatus();
    }

    // MÉTODOS DE LOS BOTONES
    private void encenderWifi() {
        if (!wifi.isWifiEnabled()) {
            toast("Encendiendo WIFI...");
            wifi.setWifiEnabled(true);
            tvResult.setText("Encendido");
            encendidoApagado = 1;
        } else {
            toast("Debes Apagar el WIFI");
        }
    }

    private void apagarWifi() {
        if (wifi.isWifiEnabled()) {
            toast("Apagando WIFI...");
            wifi.setWifiEnabled(false);
            tvResult.setText("Apagado");
            encendidoApagado = 2;
        } else {
            toast("Debes Encender el WIFI");
        }
    }

    private void buscarWifi() {
        if (wifi.isWifiEnabled()) {
            if (tarea == false) {
                tarea = true;
                toast("Iniciando Búsqueda");
                new MyAsyncTask().execute();
            } else {
                tarea = false;
                toast("Búsqueda Detenida");
            }
        } else {
            toast("Debes Encender el WIFI");
        }
    }

    private void guardarWifi() {
        if (wifi.isWifiEnabled()) {
            ArrayList<Red> reds = buscar();
            guardarRedes(reds);
        } else {
            toast("Debes Encender el WIFI");
        }
    }

    private void mostrarWifi() {
        Intent intentsecond = new Intent(this, SecondActivity.class);
        startActivity(intentsecond);
    }

    private void toast(String texto) {
        Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();
    }

    // MÉTODOS INVOCADOS POR LOS MÉTODOS DE LOS BOTONES
    private ArrayList<Red> buscar() {
        ArrayList<Red> reds = new ArrayList<Red>();
        if (wifi.isWifiEnabled()) {
            List<WifiConfiguration> lista = wifi.getConfiguredNetworks();
            for (WifiConfiguration w : lista) {
                reds.add(new Red(w.networkId, w.SSID, w.BSSID));
            }
        } else {
            toast("Debes Encender el WIFI");
        }
        return reds;
    }

    private void guardarRedes(ArrayList<Red> reds) {
        Sqlite sqlite = new Sqlite(getApplicationContext(), "wifi", null, 1);
        SQLiteDatabase db = sqlite.getWritableDatabase();
        for (Red red : reds) {
            ContentValues valores = new ContentValues();
            valores.put("idnetwork", String.valueOf(red.getIdNetworkId()));
            valores.put("ssid", red.getSSID());
            valores.put("bssid", red.getBSSID());
            try {
                db.insert("Red", null, valores);
            } catch (Exception e) {
                toast("Error" + e.getMessage());
            }
        }
        toast("Redes Guardadas");
    }

    private void guardarStatus() {
        SharedPreferences sp = getSharedPreferences("wifiStatus", Context.MODE_PRIVATE);
        if (sp != null) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("status", encendidoApagado);
            editor.commit();
        }
    }

    private void verificarStatus() {
        SharedPreferences sp = getSharedPreferences("wifiStatus", Context.MODE_PRIVATE);
        Integer wifistatus = sp.getInt("status", 0);
        if (wifistatus == 1) { // ESTÁ ENCENDIDO
            if (!wifi.isWifiEnabled()) {
                toast("Encendiendo WIFI...");
                wifi.setWifiEnabled(true);
                tvResult.setText("Encendido");
                encendidoApagado = 1;
            } else {
                tvResult.setText("Encendido");
                encendidoApagado = 1;
            }
        } else if (wifistatus == 2) { // ESTA APAGADO
            if (wifi.isWifiEnabled()) {
                toast("Apagando el WIFI...");
                wifi.setWifiEnabled(false);
                tvResult.setText("Apagado");
                encendidoApagado = 2;
            } else {
                tvResult.setText("Apagado");
                encendidoApagado = 2;
            }
        }
    }

    // CLASE MY ASYNCTASK
    class MyAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            while (tarea) {
                List<WifiConfiguration> DataNetwork = wifi.getConfiguredNetworks();
                int net = 0;
                while (tarea && net < DataNetwork.size()) {
                    WifiConfiguration w = DataNetwork.get(net);
                    String red = w.networkId + " " + w.SSID + " " + w.BSSID + "\n";
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(red);
                    net++;
                    contador++;
                }
                contador = 0;
            }
            return "Red";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            if (contador == 0) {
                tvRedes.setText("");
            }
            String red = values[0];
            tvRedes.append(red);
        }
    }
}