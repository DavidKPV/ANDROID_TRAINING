package com.david.clase_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class Actividad_1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_1);
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
            // ACTIVIDAD_1 ********************************************************************
            // SE INSTANCIA EL PINCEL
            Paint pincel = new Paint();
            // SE LE COLOCA UN COLOR AL PINCEL
            pincel.setColor(Color.WHITE);
            // PINTAMOS EL LIENZO
            canvas.drawPaint(pincel);
            // OBTENEMOS LAS LONGITUDES DE LOS PIXELES DE LA PANTALLA
            int altura = canvas.getHeight();
            int anchura = canvas.getWidth();
            // CAMBIAMOS EL COLOR DEL PINCEL
            pincel.setColor(Color.BLACK);
            // AUMENTAMOS EL TAMAÑO DE LA LETRA QUE SE ESCRIBIRÁ
            pincel.setTextSize(30);
            // ACTIVAMOS EL ANTIALIAS PARA MEJORAR LA VELOCIDAD DEL PROCESAMIENTO SACRIFICANDO LA CALIDAD
            pincel.setAntiAlias(true);

            //DIBUJAMOS UN TEXTO
            canvas.drawText("ACTIVIDAD 1, DAVID MARTINEZ MARTINEZ, ANCHO = "+anchura+" alto = "+altura, 20, 40, pincel);
            // DAMOS COLOR EN FORMATO RGB AL PINCEL
            pincel.setColor(Color.rgb(100, 20, 0));
            // DIBUJAMOS UNA LÍNEA
            canvas.drawLine(0, 40, anchura, 40, pincel);
            // NUEVAMENTE CAMBIAMOS EL COLOR DEL PINCEL Y DIBUJAMOS OTRA LÍNEA
            pincel.setColor(Color.rgb(0, 100, 20));
            canvas.drawLine(20, 0, 20, altura, pincel);

        }
    }
}