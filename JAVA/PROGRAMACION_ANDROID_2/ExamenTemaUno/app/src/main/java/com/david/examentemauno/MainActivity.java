package com.david.examentemauno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // SE CREAN LOS OBJETOS QUE SE ENLAZARÁN CON LA VISTA
    Button btnLimpiar, btnAzul, btnNegro, btnRojo;
    ConstraintLayout campoDibujo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // SE ENLAZAN LOS OBJETOS CON LA VISTA
        btnAzul = (Button) findViewById(R.id.btnAzul);
        btnNegro = (Button) findViewById(R.id.btnNegro);
        btnRojo = (Button) findViewById(R.id.btnRojo);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        campoDibujo = (ConstraintLayout) findViewById(R.id.campoDibujo);

        // CREAMOS EL OBJETO DE LA CLASE VISTADIBUJO
        final VistaDibujo vd = new VistaDibujo(this);
        // OYENTES DE LOS BOTONES
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vd.limpiarLienzo();
            }
        });
        btnRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vd.setColor("rojo");
            }
        });
        btnNegro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vd.setColor("negro");
            }
        });
        btnAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vd.setColor("azul");
            }
        });
        // ASIGNAMOS LA CLASE QUE HEREDA DE VISTA
        campoDibujo.addView(vd);
    }

    public class VistaDibujo extends View{
        // SE DECLARAN LAS VARIABLES A UTILIZAR
        float x;
        float y;
        String texto = "COMIENZA A DIBUJAR", accion ="", color ="negro";
        Path ruta = new Path();

        // CONSTRUCTOR
        public VistaDibujo(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // OBTENEMOS COORDENADAS DE LA PANTALLA
            int ancho = getMeasuredWidth();

            // PINTAMOS EL LIENZO DE COLOR VERDE CLARO
            canvas.drawColor(Color.rgb(180, 246, 103));
            // CREAMOS UN PINCEL
            Paint pincel = new Paint();

            switch (color){
                case "azul":
                    pincel.setColor(getResources().getColor(R.color.colorAzul));
                    break;
                case "negro":
                    pincel.setColor(getResources().getColor(R.color.colorNegro));
                    break;
                case "rojo":
                    pincel.setColor(getResources().getColor(R.color.colorRojo));
                    break;
                default:
                    pincel.setColor(getResources().getColor(R.color.colorNegro));
                    break;
            }
            pincel.setStrokeWidth(10);
            pincel.setStyle(Paint.Style.STROKE);

            if(accion.equals("move")){
                ruta.lineTo(x, y);
            }
            if(accion.equals("down")){
                ruta.moveTo(x, y);
            }
            canvas.drawPath(ruta, pincel);
            // CAMBIAMOS EL COLOR NUEVAMENTE DEL PINCEL
            pincel.setColor(Color.BLACK);
            pincel.setStyle(Paint.Style.FILL);
            pincel.setTextSize(20);
            pincel.setStrokeWidth(5);
            pincel.setTextAlign(Paint.Align.CENTER);
            // PINTAMOS UN TEXTO DE AUTOR
            canvas.drawText("By David Martinez Martinez - EXAMEN", ancho/2, 50, pincel);

            pincel.setTextSize(40);
            pincel.setStrokeWidth(5);
            pincel.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(texto, ancho/2, 100, pincel);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            x = event.getX();
            y = event.getY();

            if(event.getAction() == MotionEvent.ACTION_MOVE){
                texto = "Dibujando";
                accion = "move";
            }
            if(event.getAction() == MotionEvent.ACTION_UP){
                accion = "up";
                texto = "Dibujo Listo";
            }
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                accion = "down";
            }
            invalidate();
            return true;
        }

        public void setColor(String color){
            this.color = color;
            invalidate();
        }

        public void limpiarLienzo(){
            ruta.reset();
            texto = "COMIENZA A DIBUJAR";
            invalidate();
        }
    }
}