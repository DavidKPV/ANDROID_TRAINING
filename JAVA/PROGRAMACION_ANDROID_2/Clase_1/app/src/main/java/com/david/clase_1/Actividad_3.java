package com.david.clase_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class Actividad_3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_3);
        // SE CREA LA PIZARRA EN DONDE SE DIBUJARÁN LOS ELEMENTOS
        VistaDibujo vd = new VistaDibujo(this);
        setContentView(vd);
    }
    public class VistaDibujo extends View{
        public VistaDibujo(Context context) {
            super(context);
        }
        // MÉTODO PARA DIBUJAR
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // ACTIVIDAD 3 **************************************************************************
            // OBTENEMOS EL TAMAÑO DE LA PANTALLA
            int anchura = canvas.getWidth();
            int altura = canvas.getHeight();
            // PINTAMOS EL LIENZO DE COLOR BLANCO
            canvas.drawColor(Color.WHITE);
            // CREAMOS DOS PINCELES DE DIFERENTE COLOR
            Paint pincelNegro = new Paint();
            Paint pincelGris = new Paint();
            // LES DAMOS SUS RESPECTIVOS COLORES
            pincelNegro.setColor(Color.BLACK);
            pincelGris.setColor(Color.GRAY);
            // HACEMOS UN CICLO PARA REPETIR LÍNEAS CONSTANTEMENTE EN TODA LA PANTALLA
            for(int con=30; con <= altura; con+=30){
                canvas.drawLine(0, con, anchura, con, pincelGris);
                canvas.drawText(""+con, 30, con, pincelNegro);
            }
            // CAMBIAMOS EL TAMAÑO DE LA LETRA PARA COLOCAR UN TEXTO
            pincelNegro.setTextSize(40);
            // ACTIVAMOS EL ANTI-ALIAS
            pincelNegro.setAntiAlias(true);
            // DIBUJAMOS EL TEXTO
            canvas.drawText("Altura = "+altura, 100, altura/2, pincelNegro);

            // COLOCAMOS TEXTO DE ACTIVIDAD 3
            canvas.drawText("ACTIVIDAD 3 - DAVID MARTINEZ MARTINEZ", 100, altura/3, pincelNegro);
        }
    }
}