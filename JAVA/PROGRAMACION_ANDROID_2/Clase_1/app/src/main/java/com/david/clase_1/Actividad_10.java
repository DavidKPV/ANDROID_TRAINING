package com.david.clase_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class Actividad_10 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_10);
        // SE CREA LA PIZARRA EN DONDE SE DIBUJARÁN LOS ELEMENTOS
        VistaDibujo vd = new VistaDibujo(this);
        setContentView(vd);
    }
    public class VistaDibujo extends View {
        // DECLARAMOS VARIABLES DE COORDENADAS X y Y y DEL NOMBRE DEL MÉTODO DETECTADO
        float x = 50;
        float y = 50;
        String texto="";
        public VistaDibujo(Context context) {
            super(context);
        }
        // MÉTODO PARA DIBUJAR
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // ACTIVIDAD_10 ********************************************************************
            // PINTAMOS EL LIENZO DE COLOR GRIS CLARO
            canvas.drawColor(Color.LTGRAY);
            // INSTANCIAMOS EL PINCEL
            Paint pincel = new Paint();
            pincel.setAntiAlias(true);
            pincel.setColor(Color.RED);
            // DIBUJAMOS EL CIRCULO
            canvas.drawCircle(x, y, 20, pincel);
            // CAMBIAMOS DE COLOR EL PINCEL
            pincel.setColor(Color.BLACK);
            pincel.setTextSize(35);
            canvas.drawText("ACTIVIDAD 10 - DAVID MARTINEZ MARTINEZ", 100, 50, pincel);
            canvas.drawText("X = "+x, 100, 130, pincel);
            canvas.drawText("Y = "+y, 100, 170, pincel);
            canvas.drawText(texto, 250, 250, pincel);
        }

        // CREAMOS EL EVENTO ONTOUCH PARA QUE "ESCUCHE"
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // COLOCAMOS SI SE REALIZA LA ACCIÓN DE TOQUE EN LA PANTALLA
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                texto = "ACTION_DOWN";
                x = event.getX();
                y = event.getY();
            }
            if(event.getAction() == MotionEvent.ACTION_UP){
                texto = "ACTION_UP";
                x = event.getX();
                y = event.getY();
            }
            if(event.getAction() == MotionEvent.ACTION_MOVE){
                texto = "ACTION_MOVE";
                x = event.getX();
                y = event.getY();

            }

            // COLOCAMOS LA INSTRUCCIÓN PARA ACTUALIZAR LOS PARÁMETROS
            invalidate();
            return true;
        }
    }
}