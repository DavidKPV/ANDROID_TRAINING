package com.david.examen_tema_v_dmm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.david.examen_tema_v_dmm.services.Buscar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // OBJETOS
    ListView lvTareas;
    Buscar buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ENLACE
        lvTareas = (ListView) findViewById(R.id.lvTareas);
        buscar = new Buscar(this, this, lvTareas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // VALIDAMOS OPCIONES
        switch(item.getItemId()){
            case R.id.insertar:
                Intent newtarea = new Intent(getApplicationContext(), FieldsActivity.class);
                newtarea.putExtra("opcion", "NUEVA TAREA");
                newtarea.putExtra("op", 1);
                startActivity(newtarea);
                break;
            case R.id.exit:
                finishAffinity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        buscar.buscar();
    }
}