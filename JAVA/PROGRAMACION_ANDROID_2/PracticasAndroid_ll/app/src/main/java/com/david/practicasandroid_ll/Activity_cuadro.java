package com.david.practicasandroid_ll;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Activity_cuadro extends AppCompatActivity {
    Button btnStartCuadro, btnStopCuadro;
    ImageView ivCuadro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuadro);
        // SE ENLAZAN LOS OBJETOS CON LA VISTA (LAYOUT)
        btnStartCuadro = (Button) findViewById(R.id.btnStartSincronizada);
        ivCuadro = (ImageView) findViewById(R.id.ivSincronizada);
        ivCuadro.setImageResource(R.drawable.runingbird); // LE DAMOS EL ARCHIVO DRAWABLE AL IMAGE VIEW
        final AnimationDrawable runningbird = (AnimationDrawable)ivCuadro.getDrawable(); // INSTANCIAMOS UNA ANIMACIÓN DE IMÁGENES
        // OYENTE DEL BOTÓN
        btnStartCuadro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(runningbird.isRunning()){
                    btnStartCuadro.setText("INICAR ANIMACIÓN");
                    runningbird.stop();  // SE PARA
                }
                else{
                    btnStartCuadro.setText("DETENER ANIMACIÓN");
                    runningbird.start();  // SE INICIA LA ANIMACIÓN
                }
            }
        });
    }
}