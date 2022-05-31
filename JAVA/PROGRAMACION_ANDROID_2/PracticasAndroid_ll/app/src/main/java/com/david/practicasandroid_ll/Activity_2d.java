package com.david.practicasandroid_ll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class Activity_2d extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2d);

        // CLASE DE CANVAS
        VistaDibujo vd = new VistaDibujo(this);
        // COLOCAMOS EL DIBUJO EN EL LAYOUT
        setContentView(vd);
    }

    public class VistaDibujo extends View {
        public VistaDibujo(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
             // OBTENEMOS LAS DIMENSIONES DE LA PANTALLA
            int altura = getMeasuredHeight();
            int anchura = canvas.getWidth();
            // INSTANCIAMOS UN PINCEL
            Paint pincel = new Paint();
            pincel.setColor(Color.BLACK);
            pincel.setAntiAlias(true);
            // PINTAMOS TODO EL LIENZO DE COLOR AZUL
            canvas.drawColor(Color.BLUE);
            pincel.setStyle(Paint.Style.FILL);
            pincel.setColor(Color.YELLOW);
            //CIRCULOS
            canvas.drawCircle(200, 200, 200, pincel);
            canvas.drawCircle(600, 500, 250, pincel);
            // RECTÁNGULOS
            pincel.setColor(Color.RED);
            canvas.drawRect(anchura/4, 1300, 700, 900, pincel);
            pincel.setColor(Color.GREEN);
            canvas.drawRect(anchura/8, 1800, 700, 1400, pincel);
            pincel.setColor(Color.CYAN);
            canvas.drawRect(800, 1800, 700, 1400, pincel);
        }
    }
}