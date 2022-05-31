package com.david.appagendaws_materialdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // SE CREAN LOS OBJETOS PARA LOS ELEMENTOS UTILIZADOS EN LA VISTA
    EditText EtUsuario, EtPassword;
    Button BtnIngresar, BtnSalir;
    TextView TvRegistrarme;
    TextInputLayout TilUsuario, TilPassword;

    // CREAMOS UN PROGRESS DIALOG PARA COLOCAR UNA ANIMACIÓN DE CARGA CUANDO LA APP SE ALENTA
    // POR CULPA DEL SERVIDOR
    ProgressDialog progressDialog;

    // CREAMOS UN OBJETO QUE CUANDO MANDAMOS EL DATO LO MANDAMOS POR UN STRING
    RequestQueue requestQueue;

    // CREAMOS UNA CADENA LA CUAL CONTENDRÁ LA CADENA DE NUESTRO WEB SERVICE
    String HttpUri = "http://192.168.43.200:8080/agendaWS/usuario.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SE ENLAZAN LOS OBJETOS CON LA VISTA ENCONTRANDO LOS ELEMENTOS
        EtUsuario = (EditText) findViewById(R.id.EtUsuario);
        EtPassword = (EditText) findViewById(R.id.EtPassword);
        BtnIngresar = (Button) findViewById(R.id.BtnIngresar);
        BtnSalir = (Button) findViewById(R.id.BtnSalir);
        TvRegistrarme = (TextView) findViewById(R.id.TvRegistrarme);
        TilUsuario = (TextInputLayout) findViewById(R.id.TilUsuario);
        TilPassword = (TextInputLayout) findViewById(R.id.TilPassword);

        // INICIALIZAR EL REQUEST QUEUE
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        // INICIALIZAMOS EL OBJETO PROGRESS DIALOG
        progressDialog = new ProgressDialog(MainActivity.this);
        // SE CREAN LOS OYENTES DE LOS BOTONES
        // BOTON INGRESAR
        BtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresar();
            }
        });
        // BOTON SALIR
        BtnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir();
            }
        });
        // OYENTE DEL TEXTVIEW
        TvRegistrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registro();
            }
        });
    }

    // METODO INGRESAR
    private void ingresar(){
        // OBTENEMOS LOS VALORES DE LOS CAMPOS
        final String u, p;
        u = TilUsuario.getEditText().getText().toString();
        p = TilPassword.getEditText().getText().toString();

        // VERIFICAMOS QUE LOS CAMPOS NO ESTEN VACÍOS
        if(u.isEmpty() || p.isEmpty()){
            Toast.makeText(getApplicationContext(),"LLENA TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
        }
        else{
            // MOSTRAMOS EL PROGRESS DIALOG ---- AQUÍ SE COMIENZA EL ARMADO Y LA EJECUCIÓN DEL WEB SERVICE
            progressDialog.setMessage("CARGANDO...");
            progressDialog.show();

            // CREACIÓN DE LA CADENA A EJECUTAR EN EL WEB SERVICE MEDIANTE VOLLEY
            // Objeto de volley
            StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUri,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String serverresponse) {
                            // UNA VEZ QUE SE MANDAN TODOS LOS VALORES AL WEB SERVICE
                            // QUITAMOS EL PROGRESS DIALOG PARA QUE UNA VEZ QUE SE MANDEN LOS DATOS
                            // YA SE PUEDA TRABAJAR
                            progressDialog.dismiss();
                            // MANEJO DE ERRORES CON RESPECTO A LA RESPUESTA
                            try {
                                // CREAR UN OBJETO DE TIPO JSON PARA OBTENER EL ARCHIVO QUE MANDARÁ EL WEB SERVICE
                                JSONObject obj = new JSONObject(serverresponse);
                                // INTERPRETAR EL VALOR DEL JSON OBTENIDO DEL WEB SERVICE
                                Boolean error = obj.getBoolean("error");
                                // OBTENET EL MENSAJE
                                String mensaje = obj.getString("mensaje");
                                // INTERPRETAR LOS VALORES
                                if(error = true){
                                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Datos correctos", Toast.LENGTH_LONG).show();
                                }
                            }catch(JSONException e){
                                e.printStackTrace();
                            }

                        }
                        // ESTE SE EJECUTA SI HAY UN ERROR EN LA RESPUESTA
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // SE OCULTA EL PROGRESS DIALOG
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            }){
                // MAPEO DE LOS VALORES QUE MANDAMOS AL WEB SERVICE
                protected Map<String, String> getParams(){
                    // RETORNAR LOS VALORES
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("email", u);
                    parametros.put("pass", p);
                    parametros.put("opcion", "login");
                    return parametros;
                }
            };

            // SE MANDA A EJECUTAR EL STRING PARA LA LIBRERÍA DE VOLLEY
            requestQueue.add(stringRequest);
        }
    }

    // MÉTODO SALIR
    private void salir(){
        // SE SALDRÁ DE LA APP
        finish();
    }

    private void registro(){
        // INSTANCIAMOS LA ACTIVITY
        Intent actregistro = new Intent(getApplicationContext(), RegistroActivity.class);

        // INICIAMOS EL ACTIVITY
        startActivity(actregistro);
    }
}
