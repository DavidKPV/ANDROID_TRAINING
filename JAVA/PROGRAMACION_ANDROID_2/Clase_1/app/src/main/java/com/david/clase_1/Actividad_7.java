package com.david.clase_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class Actividad_7 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_7);
        // SE CREA LA PIZARRA EN DONDE SE DIBUJARÁN LOS ELEMENTOS
        VistaDibujo vd = new VistaDibujo(this);
        setContentView(vd);
    }
    public class VistaDibujo extends View {
        public VistaDibujo(Context context) {
            super(context);
        }
        // MÉTODO PARA DIBUJAR
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // ACTIVIDAD_7 ********************************************************************
            // PINTAMOS EL LIENZO DE COLOR BLANCO
            canvas.drawColor(Color.WHITE);
            // INSTANCIAMOS EL PINCEL
            Paint pincel = new Paint();
            pincel.setTextSize(60);
            pincel.setAntiAlias(true);
            // OBTENEMOS LA ANCHURA DE LA PANTALLA
            int anchura = getWidth();
            pincel.setColor(Color.BLUE);
            pincel.setStyle(Paint.Style.STROKE);

            // INSTANCIAMOS UNA RUTA
            Path ruta = new Path();

            // CREAMOS UN OBJETO PARA ALMACENAR UNA DIRECCIÓN
            Path.Direction dir = Path.Direction.CCW;
            // VARIABLE DE RADIO
            float radio = 200;

            // AÑADIMOS A LA RUTA UN TRAZO DE UN CÍRCULO
            ruta.addCircle(anchura/2, 500, radio, dir);
            // INDICAMOS EN DONDE SE COMENZARÁ A DIBUJAR
            ruta.offset(0, 0);

            // DECLARAMOS LOS VALORES HOFFSET Y VOFFSET
            float hoffsset = 5;
            float voffset = 5;
            // DIBUJAMOS LA RUTA
            canvas.drawPath(ruta, pincel);
            // CAMBIAMOS COLOR DEL PINCEL
            pincel.setColor(Color.RED);
            // DIBUJAMOS EL TEXTO SOBRE LA RUTA
            canvas.drawTextOnPath("David en la ruta externa, punto inicial "+ hoffsset, ruta, hoffsset, voffset, pincel);

            // CAMBIAMOS LOS VALORES DE HOFFSET Y VOFFSET
            hoffsset = 200;
            voffset = 100;
            pincel.setStyle(Paint.Style.FILL);
            pincel.setTextSize(40);
            canvas.drawTextOnPath("David en el interior, iniciando de "+hoffsset+" del punto final", ruta, hoffsset, voffset, pincel);

            // DIBUJAMOS EL TEXTO CON MI NOMBRE XD
            pincel.setColor(Color.BLUE);
            canvas.drawText("ACTIVIDAD 7 - DAVID MARTINEZ MARTINEZ", 100, 1000, pincel);
        }
    }
}