package com.david.crud_con_sqlite_dmm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // DECLARAMOS LOS OBJETOS A UTILIZAR
    Button btnRegistrar, btnConsultar, btnVisualizarUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ENLAZAMOS LOS OBJETOS CON LA VISTA
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnConsultar = (Button) findViewById(R.id.btnConsultar);
        btnVisualizarUsers = (Button) findViewById(R.id.btnVisualizarUsers);
        // CREAMOS LOS OYENTES DE LOS BOTONES QUE CAMBIARÁN A LA ACTIVIDAD CORRESPONDIENTE
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Abriendo sección de REGISTRO", Toast.LENGTH_LONG).show();
                Intent cambio = new Intent(getApplicationContext(), Activity_insertar.class);
                startActivity(cambio);
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Abriendo sección de CONSULTA", Toast.LENGTH_LONG).show();
                Intent cambio = new Intent(getApplicationContext(), Activity_consultar.class);
                startActivity(cambio);
            }
        });

        btnVisualizarUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Abriendo sección de LIST VIEW", Toast.LENGTH_LONG).show();
                Intent cambio = new Intent(getApplicationContext(), Activity_listview.class);
                startActivity(cambio);
            }
        });
    }
}