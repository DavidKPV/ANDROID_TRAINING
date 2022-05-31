package com.david.almacenamientodearchivos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnIngresar, btnRegistrar; // DECLARAMOS LOS OBJETOS A UTILIZAR
    EditText etNombre, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ENLAZAMOS LOS OBJETOS CON LOS ELEMENTOS DE LA VISTA
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnIngresar.setOnClickListener(new View.OnClickListener() {  // SE CREAN LOS OYENTES DE LOS BOTONES
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irRegistro();
            }
        });
    }
    private void login(){
        // SE OBTIENEN LOS VALORES DE LOS CAMPOS INGRESADOS
        String nombre = etNombre.getText().toString();
        String password = etPassword.getText().toString();
        // CREAMOS UNA CONDICIONAL PARA VFALIDAR LOS CAMPOS
        if(nombre.equals("David Martinez") && password.equals("Hola102030*")){
            // LIMPIAMOS LAS CAJAS DE TEXTO
            etNombre.getText().clear();
            etPassword.getText().clear();
            // INGRESAMOS A LA PANTALLA DE INICIO
            Intent inicio = new Intent(getApplicationContext(), Activity_inicio.class);
            startActivity(inicio);
        }else{
            Toast.makeText(getApplicationContext(), "Usuario Incorrecto", Toast.LENGTH_LONG).show();
        }
    }
    private void irRegistro(){
        // SIMPLEMENTE REDIRIGIMOS A LA ACTIVIDAD QUE SE ENCARGARÁ DEL REGISTRO DE LOS DATOS
        Intent registro = new Intent(getApplicationContext(), Activity_registro.class);
        startActivity(registro);
    }
}