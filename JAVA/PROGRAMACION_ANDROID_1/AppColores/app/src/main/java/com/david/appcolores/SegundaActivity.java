package com.david.appcolores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.widget.LinearLayout;
import android.widget.Toast;

public class SegundaActivity extends AppCompatActivity {

    LinearLayout ventana;

    @Override
    // MÉTODO QUE DETECTA CUANDO SE PRESIONA LA FLECHA DE REGRESO
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home: // POR SI EN UN FUTURO SE AGREGAN MÁS OPCIONES
                Log.i("ActionBar", "Regresar!");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        // LAS SIGUIENTES DOS LÍNEAS HABILITAN LA FLECHA DE RETORNO EN LA ACTICITY
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        ventana = (LinearLayout) findViewById(R.id.ventana);


        recibirMensaje();
    }

    private void recibirMensaje(){
        Intent intent2 = getIntent();
        // RECIBE TODOS LOS PARÁMETROS
        String color = intent2.getStringExtra("valor");
        String valor = "#fff";
        switch (color){
            case "Rojo":
                valor="#FF0000";
                break;
            case "Verde":
                valor="#04B404";
                break;
            case "Azul":
                valor="#0040FF";
                break;
            case "Negro":
                valor="#000000";
                break;
            case "Amarillo":
                valor="#FFFF00";
                break;
            case "Blanco":
                valor="#ffffff";
                break;
            default:
                Toast.makeText(this, "OH! Ha acurrido un error", Toast.LENGTH_LONG).show();
                break;
        }

        // PASAR LOS DATOS A LA VENTANA DE LA SEGUNDA ACRTIVIDAD
        try {
            ventana.setBackgroundColor(Color.parseColor(valor));
        }
        catch (Exception e) {
            Toast.makeText(this, "OH! Ha acurrido un error", Toast.LENGTH_LONG).show();
        }
    }
}
