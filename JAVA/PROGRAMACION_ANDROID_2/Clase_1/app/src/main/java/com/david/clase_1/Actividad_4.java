package com.david.clase_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class Actividad_4 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_4);
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
            // ACTIVIDAD 4 **********************************************************************
            // PONEMOS DE COLOR BLANCO EL LIENZO
            canvas.drawColor(Color.WHITE);
            Paint pincel = new Paint(); // CREAMOS EL PINCEL
            pincel.setColor(Color.BLACK); // COLOCAMOS EL COLOR NEGRO AL PINCEL
            // CAMBIAMOS EL TAMAÑO DE TEXTO DEL PINCEL
            pincel.setTextSize(30);
            pincel.setAntiAlias(true);
            // OBTENEMOS MEDIDAS DE LA PANTALLA
            int anchura = getMeasuredWidth();
            int altura = getMeasuredHeight();
            int abajo = getBottom();
            int derecha = getRight();
            // ALINEAMOS EL TEXTO AL CENTRO
            pincel.setTextAlign(Paint.Align.CENTER);
            // DIBUJAMOS LOS TEXTOS
            canvas.drawText("ANCHO = "+anchura, anchura/2, 40, pincel);
            canvas.drawText("ALTO = "+altura, anchura/2, 80, pincel);
            canvas.drawText("DERECHO = "+derecha, anchura/2, 120, pincel);
            canvas.drawText("ABAJO (BOTTOM) = "+abajo, anchura/2, 160, pincel);
            // CAMBIAMOS EL COLOR DEL PINCEL A AZUL
            pincel.setColor(Color.BLUE);
            // DIBUJAMOS DOS LÍNEAS CRUZADAS
            canvas.drawLine(0, 0, derecha, altura, pincel);
            canvas.drawLine(derecha, 0, 0, abajo, pincel);
            // COLOCAMOS TEXTO DE LA ACTIVIDAD 4
            canvas.drawText("ACTIVIDAD 4 - DAVID MARTINEZ MARTINEZ", anchura/2, altura/2, pincel);
        }
    }
}