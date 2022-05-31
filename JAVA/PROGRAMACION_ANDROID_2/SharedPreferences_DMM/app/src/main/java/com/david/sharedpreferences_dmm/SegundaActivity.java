package com.david.sharedpreferences_dmm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {
    // DECLARAMOS LOS OBJETOS UTILIZADOS
    TextView tvNombre, tvApellidoP, tvApellidoM, tvEdad, tvUsuario, tvCorreo, tvPassword;
    Button btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        // ENLAZAMOS LOS OBJETOS CON LA VISTA
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvApellidoP = (TextView) findViewById(R.id.tvApellidoP);
        tvApellidoM = (TextView) findViewById(R.id.tvApellidoM);
        tvEdad = (TextView) findViewById(R.id.tvEdad);
        tvUsuario = (TextView) findViewById(R.id.tvUsuario);
        tvCorreo = (TextView) findViewById(R.id.tvCorreo);
        tvPassword = (TextView) findViewById(R.id.tvPassword);
        btnRegresar = (Button) findViewById(R.id.btnRegresar);

        // CREAMOS EL OYENTE DEL BOTÓN
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regreso = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(regreso);
            }
        });
        cargarDatos(); // INVOCAMOS EL MÉTODO QUE RELLENARÁ LOS TEXTVIEW CON LOS DATOS DEL SHARED PREFERENCES
    }
    private void cargarDatos(){
        SharedPreferences sharedPreferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        // OBTENEMOS LOS DATOS DEL SHARED PREFERENCES
        String nombre = sharedPreferences.getString("nombre", "");
        String apellidoP = sharedPreferences.getString("apellidoP", "");
        String apellidoM = sharedPreferences.getString("apellidoM", "");
        String edad = sharedPreferences.getString("edad", "");
        String usuario = sharedPreferences.getString("usuario", "");
        String correo = sharedPreferences.getString("correo", "");
        String pass = sharedPreferences.getString("pass", "");
        tvNombre.append(nombre); // ASIGNAMOS LOS VALORES A LOS TEXTVIEW
        tvApellidoP.append(apellidoP);
        tvApellidoM.append(apellidoM);
        tvEdad.append(edad);
        tvUsuario.append(usuario);
        tvCorreo.append(correo);
        tvPassword.append(pass);
    }
}