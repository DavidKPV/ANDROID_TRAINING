package com.david.appcolores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // DECLARAR LOS ELEMENTOS
    Spinner spColorsotes;
    Button btnVer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ENCONTRAR LOS ELEMENTOS POR ID
        spColorsotes = (Spinner) findViewById(R.id.spcolorsotes);
        btnVer = (Button) findViewById(R.id.btnEnviar);

        // CREAR UN ADAPTER  PARA EL SPINNER                                       contexto         values          clase
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.colores_principal, android.R.layout.simple_spinner_item);
        spColorsotes.setAdapter(adaptador);

        // SE CREA EL OYENTE PARA EL BOTON
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SE COLOCAN TODAS LAS INTRUCCIONES DEL OYENTE PARA RECIBIR LOS MENSAJES
                Enviar();
            }
        });
    }

    private void Enviar(){
        // RECIBE EL PARÁMETRO DEL SPINNER
        String nombre_color = spColorsotes.getSelectedItem().toString();

        // SE INSTANCIA EL INTENT
        Intent inten1 = new Intent(this, SegundaActivity.class);
        inten1.putExtra("valor", nombre_color);

        // INICIAR LA ACTIVIDAD
        startActivity(inten1);
    }
}
