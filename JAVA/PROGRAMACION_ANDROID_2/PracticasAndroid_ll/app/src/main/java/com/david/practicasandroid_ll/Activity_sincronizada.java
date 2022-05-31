package com.david.practicasandroid_ll;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Activity_sincronizada extends AppCompatActivity {
    Button btnStartSincronizada;
    ImageView ivSincronizada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronizada);

        btnStartSincronizada = (Button) findViewById(R.id.btnStartSincronizada);
        ivSincronizada = (ImageView) findViewById(R.id.ivSincronizada);

        // OYENTE DEL BOTÓN INICIAR ANIMACIÓN TWEEN
        btnStartSincronizada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tweenAnimation();
            }
        });
    }
    // MÉTODO QUE APLICARÁ LA ANIMACIÓN
    private void tweenAnimation(){
        Animation animacion = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
        // INICIAMOS ANIMACIÓN
        ivSincronizada.startAnimation(animacion);
    }
}