package com.david.appintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // PASO 1 DECLRAR EL TIPO DE ELEMENTOS
    EditText etMensaje;
    Button  btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // PASO 2 ENLAZAR LA VISTA POR ID
        etMensaje = (EditText) findViewById(R.id.etMensaje);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        // PROGRAMA EL OYENTE DEL BOTÓN
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SE COLOCAN TODAS LAS INTRUCCIONES (PASAR ACTIVITY'S)
                EnviarMensaje();
            }
        });
    }

    private void EnviarMensaje(){
        String msj = etMensaje.getText().toString();
        if(msj.isEmpty()){
            Toast.makeText(this, "DEBES INGRESAR UN MENSAJE", Toast.LENGTH_SHORT).show();
        }
        else {
            // intent(contexto (instruccion gráfica),  la clase compilada del Activity)
            Intent intent1 = new Intent(this, SecondActivity.class);
            intent1.putExtra("Mensaje:", msj);
            startActivity(intent1);
        }
    }

}
