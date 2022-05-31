package com.david.sharedpreferences_dmm;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // DECLARAMOS LOS OBJETOS A UTILIZAR
    EditText etNombre, etApellidoP, etApellidoM, etEdad, etUsuario, etCorreo, etPassword;
    Button btnRegistrar, btnVerdatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ENLAZAMOS LOS OBJETOS CON LA VISTA
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellidoP = (EditText) findViewById(R.id.etApellidoP);
        etApellidoM = (EditText) findViewById(R.id.etApellidoM);
        etEdad = (EditText) findViewById(R.id.etEdad);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnVerdatos = (Button) findViewById(R.id.btnVerdatos);

        // CREAMOS LOS OYENTES DE LOS BOTONES
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                almacenarEnShared();
            }
        });
        btnVerdatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cambio = new Intent(getApplicationContext(), SegundaActivity.class);
                startActivity(cambio);
            }
        });
    }
    private void almacenarEnShared(){
        // CREAMOS EL SHARED PREFENCES
        SharedPreferences sharedPreferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        // SE OBTIENEN LOS DATOS DE LAS CAJAS DE TEXTO
        String nombre = etNombre.getText().toString();
        String apellidoP = etApellidoP.getText().toString();
        String apellidoM = etApellidoM.getText().toString();
        String edad = etEdad.getText().toString();
        String usuario = etUsuario.getText().toString();
        String correo = etCorreo.getText().toString();
        String pass = etPassword.getText().toString();
        // VALIDAMOS QUE TODOS LOS CAMPOS TRAIGAN DATOS CONSIGO
        if(!nombre.isEmpty() && !apellidoP.isEmpty() && !apellidoM.isEmpty() && !edad.isEmpty() && !usuario.isEmpty()
        && !correo.isEmpty() && !pass.isEmpty()){
            // ACTIVAMOS EL EDITOR DEL SHARED PARA PODER MANIPULARLO E INSERTAR LOS DATOS
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nombre", nombre); // VAMOS ALMACENANDO DATO POR DATO
            editor.putString("apellidoP", apellidoP);
            editor.putString("apellidoM", apellidoM);
            editor.putString("edad", edad);
            editor.putString("usuario", usuario);
            editor.putString("correo", correo);
            editor.putString("pass", pass);
            // REALIZAMOS UN COMMIT SOBRE EL EDITOR PARA APLICAR LOS CAMBIOS SOBRE EL SHARED PREFERENCES
            editor.commit();
            // INDICAMOS AL USUARIO QUE SE ALMACENARON LOS DATOS EXITOSAMENTE
            Toast.makeText(getApplicationContext(), "REGISTRO EXITOSO", Toast.LENGTH_LONG).show();
            // LIMPIAMOS LAS CAJAS DE TEXTO
            etNombre.getText().clear();
            etApellidoP.getText().clear();
            etApellidoM.getText().clear();
            etEdad.getText().clear();
            etUsuario.getText().clear();
            etCorreo.getText().clear();
            etPassword.getText().clear();
        }
        else{
            Toast.makeText(getApplicationContext(), "NO DEJES NINGÚN CAMPO VACÍO :D", Toast.LENGTH_LONG).show();
        }
    }
}