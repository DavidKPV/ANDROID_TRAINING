package com.david.clase_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Actividad_11 extends AppCompatActivity {
    // DECLARAMOS LOS OBJETOS
    Button btn_anim1, btn_anim2, btn_anim3;
    Button btn_anim4, btn_anim5, btn_anim6;
    ImageView ivKratos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_11);

        // ENLAZAMOS LOS OBJETOS CON LA VISTA
        btn_anim1 = (Button) findViewById(R.id.btn_anim1);
        btn_anim2 = (Button) findViewById(R.id.btn_anim2);
        btn_anim3 = (Button) findViewById(R.id.btn_anim3);
        btn_anim4 = (Button) findViewById(R.id.btn_anim4);
        btn_anim5 = (Button) findViewById(R.id.btn_anim5);
        btn_anim6 = (Button) findViewById(R.id.btn_anim6);
        ivKratos = (ImageView) findViewById(R.id.ivKratos);

        // CREAMOS LOS OYENTES PARA CADA BOTÓN
        btn_anim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transicion();
            }
        });

        btn_anim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotacion();
            }
        });

        btn_anim3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dilatacion();
            }
        });

        btn_anim4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aparicion();
            }
        });

        btn_anim5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transicionInversa();
            }
        });

        btn_anim6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animacionPersonalizada();
            }
        });
    }

    // SE CREAN LOS MÉTODOS QUE LLAMARÁN A LAS ANIMACIONES CORRESPONDIENTES
    private void transicion(){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.transicion);
        animacion.setFillAfter(false); // RESPETA LA POSICIÓN FINAL DONDE SE QUEDÓ LA ANIMACIÓN
        ivKratos.startAnimation(animacion);
    }

    private void rotacion(){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.tras_y_rot);
        animacion.setFillAfter(false); // RESPETA LA POSICIÓN FINAL DONDE SE QUEDÓ LA ANIMACIÓN
        ivKratos.startAnimation(animacion);
    }

    private void dilatacion(){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.desvanecer);
        animacion.setFillAfter(false); // RESPETA LA POSICIÓN FINAL DONDE SE QUEDÓ LA ANIMACIÓN
        ivKratos.startAnimation(animacion);
    }

    private void aparicion(){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.desvanecer);
        animacion.setFillAfter(false); // RESPETA LA POSICIÓN FINAL DONDE SE QUEDÓ LA ANIMACIÓN
        ivKratos.startAnimation(animacion);
    }

    private void transicionInversa(){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.transicioninversa);
        animacion.setFillAfter(false); // RESPETA LA POSICIÓN FINAL DONDE SE QUEDÓ LA ANIMACIÓN
        ivKratos.startAnimation(animacion);
    }

    private void animacionPersonalizada(){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.personalizado);
        animacion.setFillAfter(false); // RESPETA LA POSICIÓN FINAL DONDE SE QUEDÓ LA ANIMACIÓN
        ivKratos.startAnimation(animacion);
    }
}