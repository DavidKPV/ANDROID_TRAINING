package com.david.practica_2_dmm_bd.activitys;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import com.david.practica_2_dmm_bd.R;
import com.david.practica_2_dmm_bd.SQLiteOpenHelper.SQLiteHelper;
import com.david.practica_2_dmm_bd.modelosTablas.Usuario;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;

public class Grafica_Activity extends AppCompatActivity {
    // SE DECLARAN LOS OBJETOS A UTILIZAR PARA REALIZAR LA CONEXIÓN CON LA BD
    SQLiteDatabase db = null;
    SQLiteHelper sqLiteHelper = null;
    // MÉTODO QUE HABILITA LA FLECHA DE RETORNO
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    // SE HABILITA PARA RECONOCER LOS COLORES DEL FICHERO XML COLORS
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica);
        // HABILITAMOS LA FLECHA DE RETORNO
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // SE ENLAZA EL OBJETO PIECHART CON LA VISTA DEL XML
        PieChart pieChart = (PieChart) findViewById(R.id.pieChart);
        // ACCEDEMOS A LA BASE DE DATOS
        sqLiteHelper = new SQLiteHelper(this, "agenda.db", null, 1);
        // INDICAMOS QUE SE LEERÁN LOS DATOS
        db = sqLiteHelper.getReadableDatabase();
        Usuario datos = new Usuario(this, db);
        // OBTENEMOS LOS VALORES DE CUANTOS SON HOMBRES Y MUEJERES
        int hombres = datos.ConsultaHombres();
        int mujeres = datos.ConsultaMujeres();
        Cursor cursor = datos.Consulta();
        int todos = cursor.getCount();
        // OBTENEMOS PORCENTAJES
        float porHombres = (hombres * 100) / todos ;
        float porMujeres = (mujeres * 100) / todos ;
        // ASIGNAMOS LOS VALORES DENTRO DE UN ARRAYLIST
        ArrayList<PieEntry> sexos = new ArrayList<>();
        sexos.add(new PieEntry(hombres, "HOMBRES "+porHombres+" %"));
        sexos.add(new PieEntry(mujeres, "MUJERES "+porMujeres+" %"));
        // ASIGNAMOS LOS DATOS A LA GRPAFICA ASÍ COMO TAMBIÉN LOS COLORES Y OTRAS CARACTERÍSTICAS
        PieDataSet pieDataSet = new PieDataSet(sexos, "");
        pieDataSet.setColors(getColor(R.color.colorLightAzul), getColor(R.color.colorLightRose));
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(35);
        // CONTINUAMOS AGREGANDO MÁS CARACTERÍSTICAS
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("DE TUS CONTACTOS, ESTOS SON LOS PORCENTAJES DE HOMBRES Y MUEJRES");
        pieChart.animate();
        // SE CIERRA LA CONEXIÓN CON LA BD
        db.close();
    }
}