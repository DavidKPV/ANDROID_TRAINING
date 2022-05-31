package com.david.syncadapter_dmm;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;
public class MainActivity extends AppCompatActivity {
    public static final String URL_SAVE_DATA = "http://192.168.43.200:8080/SYNC_ADAPTER/saveData.php";

    SQLiteDataHelper db;
    // OBJETOS
    Button btnGuardar;
    EditText etNombre, etTelefono;
    ListView lvContactos;
    // LISTA PARA ALMACENAR TODOS LOS NOMBRES
    private List<Datos> datos = new ArrayList<>();
    // 1 SIGNIFICA QUE LOS DATOS ESTÁN SINCRONIZADOS Y 0 QUE NO
    public static final int DATOS_SINCRO_CON_SERV = 1;
    public static final int DATOS_NO_SINCRO_CON_SERV = 0;
    // RECEPTOR PARA SABER SI LOS DATOS ESTÁN SINCRONIZADOS O NO
    public static final String DATA_SAVED_BROADCAST = "com.david.datasaved";
    // BROADCASTE RECEIVER PARA SABER EL STATUS DE LA SINCRONIZACIÓN
    private BroadcastReceiver broadcastReceiver;
    private NetworkStateChecker networkStateChecker = new NetworkStateChecker();
    // ADAPTADOR PARA EL LISTVIEW
    private DatosAdapter datosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // INSTANCIA DEL OBJETO DB
        db = new SQLiteDataHelper(this);

        // CREACIÓN DEL BREADCAST RECEIVER
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);
        this.registerReceiver(networkStateChecker, intentFilter);

        // ENLACES CON LA VISTA
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        lvContactos = (ListView) findViewById(R.id.lvContactos);
        // OYENTE DEL BOTÓN GUARDAR
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaCampos();
            }
        });

        // LLAMADA AL MÉTODO PARA CARGAR TODOS LOS DATOS ALMACENADOS
        cargarDatos();

        // ACTUALIZACIÓN DEL BROADCAST RECEIVER PARA ACTUALIZAR EL STATUS DE LA SSINCRONIZACIÓN
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                cargarDatos();
            }
        };
        // REGISTRANDO EL BROADCAST RECEIVER PARA ACTUALIZAR EL STATUS DE LA SINCRONIZACIÓN
        registerReceiver(broadcastReceiver, new IntentFilter(DATA_SAVED_BROADCAST));
    }

    private void validaCampos(){
        String nombre = etNombre.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();

        if(!nombre.isEmpty() && !telefono.isEmpty()){
            guardarEnServ(nombre, telefono);
        }
        else{
            Toast.makeText(getApplicationContext(), "LLENA TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
        }
    }
    private void guardarEnServ(String name, String tel){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Guardando Datos...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try{
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                // SI SE GUARDA EXITOSAMENTE SE MARCA COMO SINCRONIZADO
                                guardarDatosLocal(name, tel, DATOS_SINCRO_CON_SERV);
                            }
                            else{
                                // SI NO SE GUARDA SE ALMACENA EN LA BD LOCAL Y SE COLOCA COMO NO SINCRONIZADO
                                guardarDatosLocal(name, tel, DATOS_NO_SINCRO_CON_SERV);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        guardarDatosLocal(name, tel, DATOS_NO_SINCRO_CON_SERV);
                    }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("phone", tel);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void cargarDatos(){
        datos.clear();
        Cursor cursor = db.getDatos();
        if(cursor.moveToFirst()){
            do{
                Datos dato = new Datos(
                  cursor.getString(cursor.getColumnIndex(SQLiteDataHelper.COLUMN_NAME)),
                  cursor.getString(cursor.getColumnIndex(SQLiteDataHelper.COLUMN_PHONE)),
                  cursor.getInt(cursor.getColumnIndex(SQLiteDataHelper.COLUMN_STATUS))
                );
                datos.add(dato);
            } while (cursor.moveToNext());
        }

        datosAdapter = new DatosAdapter(this, R.layout.datos, datos);
        lvContactos.setAdapter(datosAdapter);
    }

    private void refreshList(){
        datosAdapter.notifyDataSetChanged();
    }

    private void guardarDatosLocal(String nombre, String telefono, int status){
        etNombre.getText().clear();
        etTelefono.getText().clear();

        db.addDatos(nombre, telefono, status);
        Datos datos1 = new Datos(nombre, telefono, status);
        datos.add(datos1);
        refreshList();
    }
}