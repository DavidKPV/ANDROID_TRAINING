Urispackage com.david.appagendaws_materialdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    // DECLARAMOS LOS OBJETOS UTILIZADOS
    private EditText EtNombre, EtEmail, EtUsuario, EtPassword;
    private Button BtnIngresar;
    private TextInputLayout TilNombre, TilEmail, TilUsuario, TilPassword;

    // SE INSTANCIA EL PROGRESS DIALOG
    ProgressDialog progressDialog;

    // SE CREA EL REQUEST QUEUE PARA MANDAR LOS DATOS EN UN STRING (JSON)
    RequestQueue requestQueue;

    // CREAMOS UNA CADENA LA CUAL CONTENDRÁ LA CADENA DE NUESTRO WEB SERVICE
    String HttpUri = "http://192.168.43.200:8080/agendaWS/usuario.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // SE ENLAZAN LOS OBJETOS CON LOS CONTROLADORES DE LA VISTA
        EtNombre = (EditText) findViewById(R.id.EtNombre);
        EtEmail = (EditText) findViewById(R.id.EtEmail);
        EtUsuario = (EditText) findViewById(R.id.EtUsuario);
        EtPassword = (EditText) findViewById(R.id.EtPassword);
        BtnIngresar = (Button) findViewById(R.id.BtnIngresar);
        TilNombre = (TextInputLayout) findViewById(R.id.TilNombre);
        TilEmail = (TextInputLayout) findViewById(R.id.TilEmail);
        TilUsuario = (TextInputLayout) findViewById(R.id.TilUsuario);
        TilPassword = (TextInputLayout) findViewById(R.id.TilPassword);

        // INICIALIZAR EL REQUEST QUEUE
        requestQueue = Volley.newRequestQueue(RegistroActivity.this);
        // INICIALIZAMOS EL OBJETO PROGRESS DIALOG
        progressDialog = new ProgressDialog(RegistroActivity.this);

        // ACTIVAR EL BOTÓN DE RETORNO (BACK / HOME)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // SE CREAN LOS MÉTODOS PARA QUE EL EDIT TEXT PUEDA REALIZAR SUS COMPROBACIONES EN TIEMPO REAL
        // PARA EL CAMPO DE NOMBRE
        EtNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // PARA LIMPIAR LOS ERRORES DURANTE EL LLENADO DEL CAMPO
                TilNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // PARA EL CAMPO DE EMAIL
        EtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // PARA LIMPIAR LOS ERRORES DURANTE EL LLENADO DEL CAMPO
                TilEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // PARA EL CAMPO DE USUARIO
        EtUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // PARA LIMPIAR LOS ERRORES DURANTE EL LLENADO DEL CAMPO
                TilUsuario.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // PARA EL CAMPO DE PASSWORD
        EtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // PARA LIMPIAR LOS ERRORES DURANTE EL LLENADO DEL CAMPO
                TilPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // SE CREA EL OYENTE DEL BOTÓN
        BtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarDatos();
            }
        });
    }

    // METODO PARA DARLE FUNCIONALIDAD AL BOTÓN DE RETORNO
    @Override
    public boolean onSupportNavigateUp() {
        // SE EJECUTE Y REGRESE A LA PANTALLA ANTERIOR
        onBackPressed();
        return true;
    }
    // SE CREA EL MÉTODO UNA VEZ VALIDADOS LOS DATOS
    private void registrarDatos(){
        // SE OBTIENEN LOS DATOSDE LOS EDIT TEXT
        final String nombre = TilNombre.getEditText().getText().toString();
        final String email = TilEmail.getEditText().getText().toString();
        final String usuario = TilUsuario.getEditText().getText().toString();
        final String password = TilPassword.getEditText().getText().toString();
        if(nombre.isEmpty() || email.isEmpty() || usuario.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(),"LLENA TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
        }
        else{
            // SE OBTIENEN Y ALMACENAN LOS DATOS BOOLEANOS PARA VERIFICAR QUE TODOS LOS DATOS SE HAYAN INGRESADO DE MANERA CORRECTA
            Boolean vnombre = nombreAceptado(nombre);
            Boolean vemail = emailAceptado(email);
            Boolean vusuario = usuarioAceptado(usuario);
            Boolean vpassword = passwordAceptado(password);
            // CONDICIONAL PARA REGISTRAR LOS DATOS EN LA BD
            if (vnombre && vemail && vusuario && vpassword) {
                // AQUÍ SE REALIZA EL PROCESO PARA REALIZAR LA INSERCIÓN EN LA BASE DE DATOS
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
                                    JSONObject obj2 = new JSONObject(serverresponse);
                                    // INTERPRETAR EL VALOR DEL JSON OBTENIDO DEL WEB SERVICE
                                    Boolean error = obj2.getBoolean("error");
                                    // OBTENET EL MENSAJE
                                    String mensaje = obj2.getString("mensaje");
                                    // INTERPRETAR LOS VALORES
                                    if(error = true){
                                        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "Registro Exitoso", Toast.LENGTH_LONG).show();
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
                        parametros.put("nombre", nombre);
                        parametros.put("email", email);
                        parametros.put("usuario", usuario);
                        parametros.put("password", password);
                        parametros.put("opcion", "registro");
                        return parametros;
                    }
                };

                // SE MANDA A EJECUTAR EL STRING PARA LA LIBRERÍA DE VOLLEY
                requestQueue.add(stringRequest);
            }
        }
    }

    // MÉTODOS QUE VALIDAN LOS CAMPOS CON EL NÚMERO DE CARACTERES ESTABLECIDOS
    private boolean nombreAceptado(String nombre){
        // SE ASEGURA QUE TENGA DATOS ESPECÍFICOS DE UN NOMBRE
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        // Y NO PASE DEL LÍMTE PERMITIDO
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            TilNombre.setError("Nombre no válido XC");
            return false;
        }else{
            TilNombre.setError(null);
        }
        return true;
    }

    private boolean emailAceptado(String email){
        // SE ASEGURA QUE TENGA DATOS ESPECÍFICOS DE UN EMAIL
        Pattern patternEmail = Patterns.EMAIL_ADDRESS;
        // SE VERIFICA QUE SEA UNA DIRECCIÓN DE EMAIL ADEMÁS DE QUE NO SUPERE EL LÍMITE DE 30 CARACTERES
        if (!patternEmail.matcher(email).matches() || email.length() > 30) {
            TilEmail.setError("Correo electrónico no válido XC");
            return false;
        }else{
            TilEmail.setError(null);
        }
        return true;
    }

    private boolean usuarioAceptado(String usuario){
        // SE ASEGURA QUE TENGA DATOS ESPECÍFICOS DE UN NOMBRE
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        // VERIFICA QUE SEA UN NOMBRE DE USUARIO Y NO PASE DEL LÍMTE PERMITIDO
        if (!patron.matcher(usuario).matches() || usuario.length() > 15) {
            TilUsuario.setError("Usuario no válido XC");
            return false;
        }else{
            TilUsuario.setError(null);
        }
        return true;
    }

    private boolean passwordAceptado(String password){
        // VALIDA QUE NO PASE DEL LÍMTE PERMITIDO
        if (password.length() > 12) {
            TilPassword.setError("Contraseña no válida XC");
            return false;
        }else{
            TilPassword.setError(null);
        }
        return true;
    }
}
