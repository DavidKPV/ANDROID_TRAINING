package com.david.clase_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

public class Actividad_6 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_6);
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
            // ACTIVIDAD_6 ********************************************************************
            // PINTAMOS EL LIENZO DE COLOR BALCO
            canvas.drawColor(Color.WHITE);
            // INSTANCIAMOS EL PINCEL
            Paint pincel = new Paint();
            pincel.setTextSize(60);
            pincel.setAntiAlias(true);
            // TRASLADAMOS EL ORIGEN DESDE COMENZARÁ A PINTAR EL CANVAS
            int x = 50;
            int y = 100;
            canvas.translate(x, y);
            String texto = "Rotación CANVAS Por David Martinez";
            // INTANCIAMOS EL OBJETO BOUNDING-BOX PARA DIBUJAR EL RECTÁNGULO DONDE SE PINTA EL TEXTO
            Rect lados = new Rect();
            // LE DAMOS LOS LADOS A LA PROPIEDAD BOUNDS (LÍMITES)
            pincel.getTextBounds(texto, 0, texto.length(), lados);
            // CAMBIAMOS EL COLOR DEL PINCEL
            pincel.setColor(Color.BLUE);
            // COLOCAMOS EL ESTILO PARA PINTAR SOLO BORDE
            pincel.setStyle(Paint.Style.STROKE);
            // PINTAMOS LOS LÍMTES
            canvas.drawRect(lados, pincel);
            pincel.setStyle(Paint.Style.FILL);
            canvas.drawText(texto, 0, 0, pincel);
            // GUARDAMOS LO PINTADO
            canvas.save();
            // OBTENEMOS EL CENTRO DE LOS RECTÁNGULOS
            float centroenX = lados.exactCenterX();
            float centroenY = lados.exactCenterY();

            // ROTAMOS DE ACUERDO A LAS COORDENADAS
            canvas.rotate(-20, centroenX, centroenY);

            // NUEVAMENTE CAMBIANMOS EL COLOR DEL PINCEL
            pincel.setColor(Color.RED);
            pincel.setStyle(Paint.Style.FILL);
            canvas.drawText("Tras la Rotación", 100, 400, pincel);

            // COLOCAMOS UN TEXTO CON MI NOMBRE
            canvas.restore();
            pincel.setTextSize(40);
            pincel.setColor(Color.BLACK);
            canvas.drawText("ACTIVIDAD 6 - DAVID MARTINEZ MARTINEZ", 100, 800, pincel);
        }
    }
}