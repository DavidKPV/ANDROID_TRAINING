package com.david.loginconsharedpreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        // ACTIVAR EL BOTÓN DE RETORNO (BACK / HOME)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // DAR VALOR DE SESION INICIADA
        sesion_inicio();
    }

    // METODO PARA DARLE FUNCIONALIDAD AL BOTÓN DE RETORNO
    @Override
    public boolean onSupportNavigateUp() {
        // SE EJECUTE Y REGRESE A LA PANTALLA ANTERIOR
        onBackPressed();
        return true;
    }

    // METODO PARA QUE DETECTE LAS OPCIONES DEL MENU Y LO ENLACE
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // SE INSTANCIA UN MENÚ INFLATER PARA TRAER EL MENÚ
        MenuInflater inflater = getMenuInflater();

        // PARA ENLAZAR EL MENÚ A ESTA ACTIVIDAD SE UTILIZA
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }

    // PARA DARLE EL COMPORTAMIENTO A CADA ITEM QUE SE TIENE EN EL MENÚ
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // HAY QUE HACER UN SWITCH PARA LOS ITEMS
        switch(item.getItemId()){
            case R.id.itemSalir:
                // ESTA FUNCIÓN HACE QUE SALGAS DE LA APP (SE FINALICE SIN CERRAR SESION);
                finishAffinity();
                break;
            case R.id.itemCerrarS:
                // ESTA FUNCIÓN HACE QUE SALGAS DE LA APP (SE FINALICE CERRANDO SESION);
                cerrarsesion();
                finishAffinity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // METODO PARA CERRAR SESION
    private void cerrarsesion(){
        // INSTANCIAMOS EL SHARED
        SharedPreferences sp1 = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        // SE ACTIVA LA EDICION DEL ARCHIVO SHARED
        SharedPreferences.Editor editor = sp1.edit();

        editor.putBoolean("ingreso", false);
        editor.commit();
    }

    // METODO PARA APLICAR QUE SE INICIA LA SESIÓN
    private void sesion_inicio(){
        // SE INSTANCIA EL SHARED                   Nombre del archivo,   forma en como se abrirá
        SharedPreferences prefer1 = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        // SE ACTIVA LA EDICIÓN DEL ARCHIVO
        SharedPreferences.Editor editor = prefer1.edit();
        editor.putBoolean("ingreso", true);
        editor.commit();
    }
}
