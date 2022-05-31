package com.david.loginconregistro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    // SE DECLARAN LOS LEMENTOS UTILIZADOS
    Button BtnOk;
    TextView TvRegister;
    TextInputLayout tilUser, tilPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // VER SI SE HA INICIADO ANTES UNA SESIÓN
        comprobar();

        // ENLAZAR LOS CONTROLADORES DE LA VISTA
        BtnOk = (Button) findViewById(R.id.BtnOk);
        tilUser = (TextInputLayout) findViewById(R.id.tilUser);
        tilPass = (TextInputLayout) findViewById(R.id.tilPass);
        TvRegister = (TextView) findViewById(R.id.TvRegister);

        // PROGRAMAR EL OYENTE PARA ACCEDER
        BtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acceder();
            }
        });

        // SE PROGRAMA EL OYENTE PARA LA TEXT VIEW DE REGISTRAR
        TvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });

        // SE CREA EL SHARED PREFERENCES
        CrearSharedPreferences();
    }

    // SE PROGRAMA LA FUNCIÓN
    private void Acceder(){
        // SE INTANCIA EL SHARED
        SharedPreferences sp1 = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        // DECLARAMOS VARIABLES PARA COMPARAR VALORES
        String u = tilUser.getEditText().getText().toString();
        String p = tilPass.getEditText().getText().toString();

        // VALIDAMOS QUE NO ESTEN VACÍOS LOS CAMPOS
        if (u.isEmpty() || p.isEmpty()) {
            Toast.makeText(getApplicationContext(), "NO DEJES NINGÚN CAMPO VACÍO", Toast.LENGTH_LONG).show();
        } else {
            // COMPARAMOS LO DE LA CAJAS DE TEXTO CON EL SHARED PREFERENCES
            // OBTENER LOS DATOS QUE YA SE HAN ALMACENADO
            String usuario = sp1.getString("usuario", "NO SE ENCONTRÓ EL USUARIO");
            String password = sp1.getString("password", "NO SE ENCONTRÓ LA CONTRASEÑA");

            if (u.equals(usuario) && p.equals(password)) {
                // AGREGAMOS UN TOAST PARA QUE NOS DIGA BIENVENIDO XD
                Toast.makeText(getApplicationContext(), "BIENVENIDO "+usuario, Toast.LENGTH_LONG).show();

                // SE INSTANCIA EL ACTIVITY               contexto, nombre de la clase compilada
                Intent intent1 = new Intent(getApplicationContext(), InicioActivity.class);

                // SE MANDA A EJECUTAR EL ACTIVITY
                startActivity(intent1);
            } else {
                // MUESTRA UN TOAST EN CASO DE QUE LOS DATOS NO SEAN IGUAL A LOS DEL SHARED PREFERENCES
                Toast.makeText(getApplicationContext(), "DATOS INCORRECTOS", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // SE CREA LA FUNCIÓN DEL SHARED PREFERENCES
    private void CrearSharedPreferences(){
        // SE INSTANCIA EL SHARED                   Nombre del archivo,   forma en como se abrirá
        SharedPreferences prefer1 = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        // SE ACTIVA LA EDICIÓN DEL ARCHIVO
        SharedPreferences.Editor editor = prefer1.edit();

        // SE MANDAN LOS VALORES QUE QUEREMOS ALMACENAR
        // Nombre de la variable, valor de la variable
        editor.putString("usuario", "David");
        editor.putString("password", "102030");

        // PARA CONTROLAR LOS CIERRES DE SESIÓN
        editor.putBoolean("ingreso", false);

        // SE MANDA UNA INSTRUCCIÓN COMMIT PARA QUE SE GUARDEN LOS CAMBIOS
        editor.commit();
    }

    // METODO QUE VERIFICA SI SE HA CERRADO O NO UNA SESION
    private void comprobar(){
        // ACTIVAMOS EL EDITOR DEL SHARED
        SharedPreferences sp1 = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        // TRAEMOS EL VALOR DE LA SESION
        boolean val = sp1.getBoolean("ingreso", false);
        String usuario = sp1.getString("usuario", "NO SE ENCONTRÓ EL USUARIO");

        if(val==true) {
            // AGREGAMOS UN TOAST PARA QUE NOS DIGA BIENVENIDO XD
            Toast.makeText(getApplicationContext(), "BIENVENIDO "+usuario, Toast.LENGTH_LONG).show();

            // SE INSTANCIA EL ACTIVITY               contexto, nombre de la clase compilada
            Intent intent1 = new Intent(getApplicationContext(), InicioActivity.class);

            // SE MANDA A EJECUTAR EL ACTIVITY
            startActivity(intent1);
        }
    }

    public void registrar(){
        // SE INSTANCIA EL ACTIVITY
        Intent intent2 = new Intent(getApplicationContext(), RegisterActivity.class);

        // SE MANDA A EJECUTAR EL ACTIVITY
        startActivity(intent2);
    }
}
