package com.david.qr_practica;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    // OBJETO
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ENLACE
        button = (Button) findViewById(R.id.button);

        // OYENTE DEL BOTÓN
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escanear();
            }
        });
    }

    private void escanear(){
        Toast.makeText(getApplicationContext(), "INICIANDO ESCANEO", Toast.LENGTH_LONG).show();
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CameraClass.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Escaneando...");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult resultado = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(resultado != null){
            if(resultado.getContents() != null){
                AlertDialog.Builder constructor = new AlertDialog.Builder(this);
                constructor.setMessage(resultado.getContents());
                constructor.setTitle("Escaneando el resultado");
                constructor.setPositiveButton("Escanear de nuevo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        escanear();
                    }
                }).setNegativeButton("Finalizado", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialogo = constructor.create();
                dialogo.show();
            }
            else{
                Toast.makeText(this, "No existe resultado", Toast.LENGTH_LONG).show();
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}