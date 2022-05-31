package com.david.appdatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    // DECLARAR LAS VARIABLES DE LOS ELEMENTOS
    TextView tvNombre, tvEdad, tvSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // ENLAZAR CON LA VISTA
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvEdad = (TextView) findViewById(R.id.tvEdad);
        tvSexo = (TextView) findViewById(R.id.tvSexo);

        recibirMensaje();
    }

    private void recibirMensaje(){
        Intent intent2 = getIntent();
        // RECIBE TODOS LOS PARÁMETROS
        String nombre = intent2.getStringExtra("nombre");
        String edad = intent2.getStringExtra("edadfinal");
        String sexo = intent2.getStringExtra("sexo");

        // PASAR LOS DATOS A LA VENTANA DE LA SEGUNDA ACRTIVIDAD
        tvNombre.setText(nombre);
        tvEdad.setText(edad);
        tvSexo.setText(sexo);
    }
}
