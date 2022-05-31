package com.david.appsumadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // PASO 1 CREO LAS VARIABLES U OBJETOS LOCALES PARA REFERENCIARLOS CON LA VISTA
    EditText etnumero1, etnumero2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // PASO 2 ENLAZO LOS CONTROLADORES DE LA VISTA CON LAS VARIABLES LOCALES
        etnumero1 = findViewById(R.id.etnumero1);
        etnumero2 = findViewById(R.id.etnumero2);
    }

    public void calcula (View v) {
        if (etnumero1.getText().toString().isEmpty() || etnumero2.getText().toString().isEmpty()) {
            Toast.makeText(this, "NO DEJES NINGÚN CAMPO VACÍO", Toast.LENGTH_LONG).show();
        } else {

            if(siNumero(etnumero1.getText().toString()) && siNumero(etnumero2.getText().toString())){
                Double num1 = Double.parseDouble(etnumero1.getText().toString());
                Double num2 = Double.parseDouble(etnumero2.getText().toString());
                Double suma = num1 + num2;
                Toast.makeText(this, "EL RESULTADO ES: " + suma, Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "POR FAVOR, INGRESA SOLO NÚMEROS", Toast.LENGTH_LONG).show();
            }

        }
    }

    private static boolean siNumero(String numero){
        try {
            Double.parseDouble(numero);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
