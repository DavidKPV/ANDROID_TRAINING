package com.david.examen_tema_2_dmm.vista;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.david.examen_tema_2_dmm.R;

public class SplashScreen extends AppCompatActivity {
    // SE DECLARAN LOS OBJETOS Y VARIABLES
    TextView tvNombre, tvAutor;
    ImageView ivLogo;

    public static final int TIME_SPLASH = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // SE ENLAZAN LOS OBJETOS CON LA VISTA
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvAutor = (TextView) findViewById(R.id.tvAutor);
        ivLogo = (ImageView) findViewById(R.id.ivLogo);

        animaciones();

        // QUE SE MUESTRE EL SPLASH DURANTE 4 SEGUNDOS
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // CAMBIAMOS A LA ACTIVIDAD PRINCIPAL (REGISTRO)
                Intent cambio = new Intent(getApplicationContext(), Activity_registro.class);
                startActivity(cambio);
            }
        }, TIME_SPLASH);
    }

    private void animaciones(){
        Animation animacionLogo = AnimationUtils.loadAnimation(this, R.anim.escalar_rotar);
        ivLogo.setAnimation(animacionLogo);
        Animation animacionLetras = AnimationUtils.loadAnimation(this, R.anim.degrada_escala);
        tvNombre.setAnimation(animacionLetras);
        tvAutor.setAnimation(animacionLetras);
    }
}