package com.david.examentema4.modulos.wifi;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
public class Wifi_basic {
    // OBJETOS
    WifiManager wifi;
    Context contexto;
    TextView tvEstado, tvRedes;
    boolean tarea = false;
    int contador = 0;
    int encendidoApagado = 0;
    public Wifi_basic(Context contexto, TextView tvEstado, TextView tvRedes) {
        this.contexto = contexto;
        this.wifi = (WifiManager) contexto.getSystemService(Context.WIFI_SERVICE);
        this.tvEstado = tvEstado;
        this.tvRedes = tvRedes;
    }
    public boolean verifyWifi(){
        if(wifi.isWifiEnabled()){ return true; }
        else{ return false; }
    }
    // MÉTODOS DE FUNCIONALIDAD
    public void encenderWifi() {
        toast("Encendiendo WIFI...");
        wifi.setWifiEnabled(true);
        tvEstado.setText("Encendido"); encendidoApagado = 1;
    }
    public void apagarWifi() {
        toast("Apagando WIFI...");
        wifi.setWifiEnabled(false);
        tvEstado.setText("Apagado");
        encendidoApagado = 2;
    }

    public void buscarWifi() {
        if (tarea == false) {
            tarea = true;
            toast("Iniciando Búsqueda");
            new MyAsyncTask().execute();
        } else {
            tarea = false;
            toast("Búsqueda Detenida");
        }
    }

    public void guardarWifi() {
        ArrayList<Red> reds = buscar();
        guardarRedes(reds);
    }

    public void mostrarWifi() {
        Intent intentsecond = new Intent(contexto, ActivityRedesWiFi.class);
        contexto.startActivity(intentsecond);
    }

    private void toast(String texto) {
        Toast.makeText(contexto, texto, Toast.LENGTH_LONG).show();
    }

    // MÉTODOS INVOCADOS POR LOS MÉTODOS DE FUNCIONALIDAD
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
        Sqlite sqlite = new Sqlite(contexto, "wifi", null, 1);
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
                    } catch (InterruptedException e) { e.printStackTrace(); }
                    publishProgress(red);
                    net++; contador++;
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
            String red = values[0];tvRedes.append(red);
        }
    }
}
