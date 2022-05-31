package com.david.reportes_dmm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout llAreaPDF;
    Button btnGenerarPDF, btnVerPDF;
    Bitmap bitmap1;
    BarChart graficaBarras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comprobarPermisos();

        llAreaPDF = (LinearLayout) findViewById(R.id.llAreaPDF);
        btnGenerarPDF = (Button) findViewById(R.id.btnGenerarPDF);
        btnVerPDF = (Button) findViewById(R.id.btnVerPDF);
        graficaBarras = (BarChart) findViewById(R.id.graficaBarras);

        generarGrafica();

        btnGenerarPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // LE DECIMOS A PARTIR DE QUE VISTA QUEREMOS QUE SE GENERE
                bitmap1 = cargarBitmapFromView(llAreaPDF, llAreaPDF.getWidth(), llAreaPDF.getHeight());
                generarPDF();
            }
        });

        btnVerPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verPDF();
            }
        });
    }

    private static Bitmap cargarBitmapFromView(View v, int ancho, int alto){
        // SE INICIALIZA EL BITMAP
        Bitmap bitmap2 = Bitmap.createBitmap(ancho, alto, Bitmap.Config.RGB_565);
        // SE CREA EL CANVAS        confoguracion
        Canvas canvas1 = new Canvas(bitmap2);

        v.draw(canvas1);

        return  bitmap2;
    }

    private void generarPDF(){
        // DEFINIR MÉTRICAS
        DisplayMetrics dm = new DisplayMetrics();
        // CONFIGURAR DIMENSIONES
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int ancho = dm.widthPixels;
        int alto = dm.heightPixels;

        PdfDocument document = new PdfDocument();
        // DEFINIR CARACTERÍSTICAS QUE TENDRÁ EL PDF
        PdfDocument.PageInfo info = new PdfDocument.PageInfo.Builder(ancho, alto, 1).create();
        // DEFINIR CONTENIDO
        PdfDocument.Page pagina = document.startPage(info);

        Canvas canvas2 = pagina.getCanvas();
        Paint paint = new Paint();
        canvas2.drawPaint(paint);

        // REDIMENSIONAR EL BITMAP
        bitmap1 = Bitmap.createScaledBitmap(bitmap1, ancho, alto, true);
        canvas2.drawBitmap(bitmap1,0,0,null);

        // DIBUJAR LA PÁGINA DEFINIDA
        document.finishPage(pagina);
        // DEFINIR EN DONDE SE ALMACENARÁ
        String ruta = "/sdcard/reporte.pdf";
        File filepath = new File(ruta);

        try {
            document.writeTo(new FileOutputStream(filepath));
            document.close();
            Toast.makeText(getApplicationContext(), "PDF CREADO EXITOSAMENTE", Toast.LENGTH_LONG).show();
        }catch (IOException ex){
            Toast.makeText(getApplicationContext(), "Error: "+ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // MÉTODO PARA ABRIR UN PDF
    public void verPDF() {
        // OBTENEMOS LA RUTA DEL FICHERO
        String ruta =  Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
        File file = new File(ruta, "reporte.pdf");
        // LA CONVERTIMOS A URI PARA FUNCIONAR EN API 29
        Uri pdfURI = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", file);

        // SE DECLARA INTENT IMPLÍCITO
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // INDICAMOS QUE SERÁ UN PDF
        intent.setDataAndType(pdfURI,"application/pdf");
        try { // TRY CATCH POR SI LAS DUDAS XD
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "NO SE ENCONTRÓ NINGUNA APLICACIÓN\nPARA ABRIR EL FICHERO", Toast.LENGTH_LONG).show();
        }
    }

    private void generarGrafica(){
        // ENTRADA PARA LA GRÁFICA
        ArrayList<BarEntry> datos = new ArrayList<BarEntry>();
        datos.add(new BarEntry(2020, 6));
        datos.add(new BarEntry(2019, 3));
        datos.add(new BarEntry(2018, 1));
        datos.add(new BarEntry(2017, 5));
        // DEFINIMOS EL DATA SET
        BarDataSet dataset = new BarDataSet(datos, "PROYECTOS");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        dataset.setValueTextColor(Color.BLACK);
        dataset.setValueTextSize(16f);
        // DEFINIR UN BARDATA (ALIMENTACIÓN DE LA GRÁFICA)
        BarData data = new BarData(dataset);

        graficaBarras.setFitBars(true);
        graficaBarras.setData(data);
        graficaBarras.getDescription().setText("PROYECTOS");
    }

    private void comprobarPermisos(){
        List<String> permisos = new ArrayList<String>();
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            permisos.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            permisos.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permisos.size()>0)
            requestPermissions(permisos.toArray(new String[permisos.size()]), 124);
    }
}