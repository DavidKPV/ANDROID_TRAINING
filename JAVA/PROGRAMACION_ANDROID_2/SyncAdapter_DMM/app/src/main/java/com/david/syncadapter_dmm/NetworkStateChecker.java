package com.david.syncadapter_dmm;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
public class NetworkStateChecker extends BroadcastReceiver {
    private Context contexto;
    private SQLiteDataHelper db;
    public NetworkStateChecker(){
        // ...
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        this.contexto = context;
        db = new SQLiteDataHelper(contexto);
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        // SI EXISTE CONEXIÓN
        if(activeNetwork != null){
            // SI ESTÁ CONECTADO A WIFI O DATOS
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI
                    || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE){
                // SE OBTIENEN LOS DATOS NO SINCRONIZADOS
                Cursor cursor = db.getUnsyncedDatos();
                if(cursor.moveToFirst()){
                    do{
                        // GUARDA LOS DATOS NO SINCRONIZADOS
                        guardarDatos(
                                cursor.getInt(cursor.getColumnIndex(SQLiteDataHelper.COLUMN_ID)),
                                cursor.getString(cursor.getColumnIndex(SQLiteDataHelper.COLUMN_NAME)),
                                cursor.getString(cursor.getColumnIndex(SQLiteDataHelper.COLUMN_PHONE))
                        );
                    } while(cursor.moveToNext());
                }
            }
        }
    }
    private void guardarDatos(final int id, final String nombre, final String telefono){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MainActivity.URL_SAVE_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                // ACTUALIZAMOS EL STATUS EN SQLITE
                                db.updateDatosStatus(id, MainActivity.DATOS_SINCRO_CON_SERV);
                                // ENVIANDO A REFRESCAR LA LISTA CON EL BROADCAST
                                contexto.sendBroadcast(new Intent(MainActivity.DATA_SAVED_BROADCAST));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", nombre);
                params.put("phone", telefono);
                return params;
            }
        };

        VolleySingleton.getInstance(contexto).addToRequestQueue(stringRequest);
    }
}
