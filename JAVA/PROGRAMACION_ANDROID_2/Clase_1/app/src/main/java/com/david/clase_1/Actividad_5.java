package com.david.clase_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class Actividad_5 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_5);
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
            // ACTIVIDAD_5 ********************************************************************
            // PINTAMOS EL LIENZO DE COLOR BLANCO
            canvas.drawColor(Color.WHITE);
            //CREAMOS EL PINCEL
            Paint pincel = new Paint();
            // COLOCAMOS COLOR AL PINCEL
            pincel.setColor(Color.BLACK);
            // COLOCAMOS UN ESTILO AL PINCEL STROKE (CONTORNO)
            pincel.setStyle(Paint.Style.STROKE);
            // DAMOS UNA ANCHURA AL CONTORNO
            pincel.setStrokeWidth(2);
            // HABILITAMOS EL ANTIALIAS
            pincel.setAntiAlias(true);
            // OBTENEMOS LOS VALORES DE LA PANTALLA
            int anchura = getMeasuredWidth();
            int altura = getMeasuredHeight();
            // INSTANCIAMOS UNA RUTA
            Path ruta = new Path();
            // INICIALIZAMOS LA RUTA EN EL ORIGEN
            ruta.moveTo(0, 0);
            // DIBUJAMOS LA RUTA UTILIZANDO UN CICLO FOR
            for(int con = 1; con < anchura; con++){
                ruta.lineTo(con, (float) Math.sin(con/20f)*(-50f));
            }
            // VOLVEMOS A ACOMODAR LAS CORDENADAS DE LA RUTA EN LA SIGUIENTE POSICIÓN
            ruta.offset(0, 100);
            canvas.drawPath(ruta, pincel);
            // CREAMOS UN NUEVO DIBUJO DE UNA LÍNEA TRAZADA *****************************
            // CREAMOS UN ARREGLO DE VALORES PARA IMPLEMENTARLO COMO UN EFECTO DE LÍNEA
            float [] intervalos = {10, 10};
            // CREAMOS EL EFECTO
            DashPathEffect dash = new DashPathEffect(intervalos, 1);
            // COLOCAMOS LA CONFIGURACIÓN DEL EFECTO
            pincel.setPathEffect(dash);
            // POSICIONAMOS DESDE DONDE COMENZARÁ A DIBUJAR LA RUTA
            ruta.offset(0, 100);
            // DIBUJAMOS LA RUTA
            canvas.drawPath(ruta, pincel);
            // CREAMOS UN NUEVO DIBUJO DE UNA LÍNEA TRAZADA *****************************
            // CREAMOS UN ARREGLO DE VALORES PARA IMPLEMENTARLO COMO UN EFECTO DE LÍNEA
            float [] intervalos2 = {10, 10, 2, 10};
            // CREAMOS EL EFECTO
            DashPathEffect dash2 = new DashPathEffect(intervalos2, 0);
            // COLOCAMOS LA CONFIGURACIÓN DEL EFECTO
            pincel.setPathEffect(dash2);
            // POSICIONAMOS DESDE DONDE COMENZARÁ A DIBUJAR LA RUTA A PARTIR DE LA POSICIÓN ANTERIOR
            ruta.offset(0, 100);
            // DIBUJAMOS LA RUTA
            canvas.drawPath(ruta, pincel);
            // CREAMOS UN NUEVO DIBUJO DE UNA LÍNEA TRAZADA *****************************
            // CREAMOS UN ARREGLO DE VALORES PARA IMPLEMENTARLO COMO UN EFECTO DE LÍNEA
            float [] intervalos3 = {2, 4};
            // CREAMOS EL EFECTO
            DashPathEffect dash3 = new DashPathEffect(intervalos3, 0);
            // COLOCAMOS LA CONFIGURACIÓN DEL EFECTO
            pincel.setPathEffect(dash3);
            // POSICIONAMOS DESDE DONDE COMENZARÁ A DIBUJAR LA RUTA A PARTIR DE LA POSICIÓN ANTERIOR
            ruta.offset(0, 100);
            // DIBUJAMOS LA RUTA
            canvas.drawPath(ruta, pincel);
            // CREAMOS UN NUEVO DIBUJO DE UNA LÍNEA TRAZADA *****************************
            // CREAMOS UN ARREGLO DE VALORES PARA IMPLEMENTARLO COMO UN EFECTO DE LÍNEA
            float [] intervalos4 = {10, 15, 20, 25};
            // CREAMOS EL EFECTO
            DashPathEffect dash4 = new DashPathEffect(intervalos4, 3);
            // COLOCAMOS LA CONFIGURACIÓN DEL EFECTO
            pincel.setPathEffect(dash4);
            // POSICIONAMOS DESDE DONDE COMENZARÁ A DIBUJAR LA RUTA A PARTIR DE LA POSICIÓN ANTERIOR
            ruta.offset(0, 100);
            // DIBUJAMOS LA RUTA
            canvas.drawPath(ruta, pincel);

            // COLOCAMOS TEXTO DE LA ACTIVIDAD 5
            pincel.setStyle(Paint.Style.FILL_AND_STROKE);
            pincel.setTextSize(30);
            pincel.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("ACTIVIDAD 5 - DAVID MARTINEZ MARTINEZ", anchura/2, altura/2, pincel);
        }
    }
}