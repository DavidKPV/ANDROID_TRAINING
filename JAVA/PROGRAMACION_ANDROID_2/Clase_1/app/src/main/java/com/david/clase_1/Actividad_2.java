package com.david.clase_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

public class Actividad_2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_2);
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
            // ACTIVIDAD 2 ****************************************************************************
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
            // DAMOS COLOR EN FORMATO RGB AL PINCEL
            pincel.setColor(Color.rgb(0, 100, 20));
            // DIBUJAMOS UNA LÍNEA
            canvas.drawLine(anchura/2, 0, anchura/2, altura, pincel);
            pincel.setColor(Color.BLACK);
            // ALINEAMOS EL TEXTO A LA DERECHA
            pincel.setTextAlign(Paint.Align.RIGHT);
            // DIBUJAMOS UN TEXTO
            canvas.drawText("ALINEADO A LA DERECHA", anchura/2, 160, pincel);
            // SE ALINEA EL TEXTO AL CENTRO
            pincel.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("ALINEADO AL CENTRO", anchura/2, 120, pincel);
            // SE ALINEA EL TEXTO A LA IZQUIERDA
            pincel.setTextAlign(Paint.Align.LEFT);
            canvas.drawText("ALINEADO A LA IZQUIERDA", anchura/2, 80, pincel);
            pincel.setTextSkewX(0.2f);
            canvas.drawText("Propiedad SKewX 0.2", 20, 210, pincel);
            pincel.setTextSkewX(-0.2f);
            canvas.drawText("Propiedad SKewX -0.2", anchura/2, 210, pincel);
            pincel.setTextSkewX(0f);

            pincel.setTextScaleX(2f);
            canvas.drawText("Propiedad TextScale 2", 10, 250, pincel);
            pincel.setTextScaleX(-2f);
            canvas.drawText("Propiedad TextScale -2", anchura, 290, pincel);
            pincel.setTextSize(60);
            pincel.setTextScaleX(0.5f);
            canvas.drawText("Propiedad TextScale 0.5",  anchura/2, 360, pincel);
            pincel.setTextScaleX(1f);
            pincel.setTextSize(30);

            pincel.setTypeface(Typeface.SANS_SERIF);
            canvas.drawText("Fuente SANS_SERIF", 20, 330, pincel);
            pincel.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("Fuente DEFAULT_BOLD", 20, 370, pincel);
            pincel.setTypeface(Typeface.MONOSPACE);
            canvas.drawText("Fuente MONOSPACE", 20, 410, pincel);
            pincel.setTypeface(Typeface.SERIF);
            canvas.drawText("Fuente SERIF", 20, 450, pincel);
            // REGRESAMOS EL TIPO DE FUENTE POR DEFAULT
            pincel.setTypeface(Typeface.DEFAULT);

            // VERIFICAMOS EL ANTI-ALIAS
            pincel.setTextSize(30);
            pincel.setAntiAlias(false);
            canvas.drawText("ANTIALIAS - False", 20, 500, pincel);
            pincel.setAntiAlias(true);
            canvas.drawText("ANTIALIAS - True", 20, 550, pincel);

            // COLOCAMOS UN TEXTO PARA INDICAR QUE ES LA ACTIVIDAD 2
            pincel.setTextSize(40);
            canvas.drawText("ACTIVIDAD 2 - DAVID MARTINEZ MARTINEZ", 20, altura/2, pincel);

        }
    }
}