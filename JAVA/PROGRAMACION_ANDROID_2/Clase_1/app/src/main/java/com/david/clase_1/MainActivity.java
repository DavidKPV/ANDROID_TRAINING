package com.david.clase_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // DECLARAMOS LOS OBJETOS A UTILIZAR
    Button btn_a1, btn_a2, btn_a3, btn_a4, btn_a5;
    Button btn_a6, btn_a7, btn_a8, btn_a9, btn_a10, btn_a11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ENLAZAMOS LOS OBJETOS CON LA VISTA
        btn_a1 = (Button) findViewById(R.id.btn_a1);
        btn_a2 = (Button) findViewById(R.id.btn_a2);
        btn_a3 = (Button) findViewById(R.id.btn_a3);
        btn_a4 = (Button) findViewById(R.id.btn_a4);
        btn_a5 = (Button) findViewById(R.id.btn_a5);
        btn_a6 = (Button) findViewById(R.id.btn_a6);
        btn_a7 = (Button) findViewById(R.id.btn_a7);
        btn_a8 = (Button) findViewById(R.id.btn_a8);
        btn_a9 = (Button) findViewById(R.id.btn_a9);
        btn_a10 = (Button) findViewById(R.id.btn_a10);
        btn_a11 = (Button) findViewById(R.id.btn_a11);
        // SE CREAN LOS OYENTES DE CADA BOTÓN
        btn_a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SE REDIRIGE A LA ACTIVITY CORRESPONDIENTE
                Intent cambio = new Intent(getApplicationContext(), Actividad_1.class);
                startActivity(cambio);
            }
        });
        btn_a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SE REDIRIGE A LA ACTIVITY CORRESPONDIENTE
                Intent cambio = new Intent(getApplicationContext(), Actividad_2.class);
                startActivity(cambio);
            }
        });
        btn_a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SE REDIRIGE A LA ACTIVITY CORRESPONDIENTE
                Intent cambio = new Intent(getApplicationContext(), Actividad_3.class);
                startActivity(cambio);
            }
        });
        btn_a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SE REDIRIGE A LA ACTIVITY CORRESPONDIENTE
                Intent cambio = new Intent(getApplicationContext(), Actividad_4.class);
                startActivity(cambio);
            }
        });
        btn_a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SE REDIRIGE A LA ACTIVITY CORRESPONDIENTE
                Intent cambio = new Intent(getApplicationContext(), Actividad_5.class);
                startActivity(cambio);
            }
        });
        btn_a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SE REDIRIGE A LA ACTIVITY CORRESPONDIENTE
                Intent cambio = new Intent(getApplicationContext(), Actividad_6.class);
                startActivity(cambio);
            }
        });
        btn_a7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SE REDIRIGE A LA ACTIVITY CORRESPONDIENTE
                Intent cambio = new Intent(getApplicationContext(), Actividad_7.class);
                startActivity(cambio);
            }
        });
        btn_a8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SE REDIRIGE A LA ACTIVITY CORRESPONDIENTE
                Intent cambio = new Intent(getApplicationContext(), Actividad_8.class);
                startActivity(cambio);
            }
        });
        btn_a9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SE REDIRIGE A LA ACTIVITY CORRESPONDIENTE
                Intent cambio = new Intent(getApplicationContext(), Actividad_9.class);
                startActivity(cambio);
            }
        });
        btn_a10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SE REDIRIGE A LA ACTIVITY CORRESPONDIENTE
                Intent cambio = new Intent(getApplicationContext(), Actividad_10.class);
                startActivity(cambio);
            }
        });
        btn_a11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SE REDIRIGE A LA ACTIVITY CORRESPONDIENTE
                Intent cambio = new Intent(getApplicationContext(), Actividad_11.class);
                startActivity(cambio);
            }
        });
    }
}