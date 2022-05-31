package com.david.practica_2_dmm_bd.activitys;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.david.practica_2_dmm_bd.R;
import com.david.practica_2_dmm_bd.SQLiteOpenHelper.SQLiteHelper;
import com.david.practica_2_dmm_bd.adapters.adaptadorRegistro;
import com.david.practica_2_dmm_bd.modelosTablas.Usuario;

import java.lang.reflect.Array;
public class MainActivity extends AppCompatActivity {
    // DECLARAMOS LOS OBJETOS PARA ENLAZARLOS CON LA VISTA
    Button btnSave, btnQuery, btnVerGrafica;
    Spinner spSexo;
    EditText etNombre, etTelefono, etDireccion;
    ListView lvResul;
    SQLiteHelper sqlh = null;
    SQLiteDatabase db = null;
    Usuario usuario = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ENLAZAMOS LOS OBJETOS CON LA VISTA
        btnSave = (Button) findViewById(R.id.btnSave);
        btnQuery = (Button) findViewById(R.id.btnQuery);
        btnVerGrafica = (Button) findViewById(R.id.btnVerGrafica);
        lvResul = (ListView) findViewById(R.id.lvResul);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etDireccion = (EditText) findViewById(R.id.etDireccion);
        spSexo = (Spinner) findViewById(R.id.spSexo);
        // CREAR UN ADAPTER  PARA EL SPINNER                                       contexto         values          clase
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.sexos, android.R.layout.simple_spinner_item);
        spSexo.setAdapter(adaptador);
        // INSTANCIAMOS LA CLASE SQLiteHelper PARA CREAR O ACCEDER A LA BD
        sqlh = new SQLiteHelper(this, "agenda.db", null, 1);

        // CREAMOS LOS OYENTES DE LOS BOTONES
        // BOTÓN DE GUARDAR
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // LLAMAMOS AL MÉTOD QUE ALMACENARÁ LOS DATOS
                guardarDatos();
            }
        });
        // BOTÓN DE CONSULTAR
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarDatos();
            }
        });
        // BOTÓN VER GRÁFICA
        btnVerGrafica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent grafica = new Intent(getApplicationContext(), Grafica_Activity.class);
                startActivity(grafica);
            }
        });
    }
    // MÉTODO QUE GUARDA LOS DATOS EN LA BASE DE DATOS
    private void guardarDatos(){
        String nombre = etNombre.getText().toString(); // OBTENEMOS LOS DATOS DE LOS EDIT TEXT
        String direccion = etDireccion.getText().toString();
        String telefono = etTelefono.getText().toString();
        String sexo = spSexo.getSelectedItem().toString();
        // CON LA AYUDA DE SQLiteHelper INDICAMOS QUE ESCRIBIREMOS SOBRE LA BASE DE DATOS
        db = sqlh.getWritableDatabase();
        // HACEMOS UNA VALIDACIÓN DE QUE LOS CAMPOS NO ESTEN VACÍOS
        if(!nombre.isEmpty() && !direccion.isEmpty() && !telefono.isEmpty() && !sexo.equals("...")){
            // INSTANCIAMOS LA CLASE USUARIO PARA PASARLE LOS PARÁMETROS
            usuario = new Usuario(getApplicationContext(), db);
            // LLAMAMOS AL MÉTODO QUE ALMACENA LOS DATOS CON LA AYUDA DEL CONTENEDOR
            usuario.Nuevo(nombre, telefono, direccion, sexo);

            // REGRESAMOS EN BLANCO LOS CAMPOS PARA INGRESAR DATOS NUEVAMENTE
            etNombre.getText().clear();
            etTelefono.getText().clear();
            etDireccion.getText().clear();
            // CERRAMOS LA CONEXIÓN CON LA BD
            db.close();
        }
        else{
            Toast.makeText(getApplicationContext(),"ESTAS OLVIDANDO COLOCAR ALGÚN DATO", Toast.LENGTH_LONG).show();
        }
    }

    private void consultarDatos(){
        // CON LA AYUDA DE SQLiteHelper INDICAMOS QUE ABRIREMOS CONEXIÓN CON LA DB
        db = sqlh.getWritableDatabase();
        // INSTANCIAMOS LA CLASE USUARIO PARA PASARLE LOS PARÁMETROS
        usuario = new Usuario(getApplicationContext(), db);
        // DECLARANMOS UN CURSOR DONDE RECIBIREMOS EL CIRSOR DE LA CLASE USUARIO
        Cursor resulCursor = usuario.Consulta();
        // INSTANCIAMOS EL ADAPTADOR
        adaptadorRegistro adaptador = new adaptadorRegistro(getApplicationContext(), R.layout.plantilla_registros, resulCursor);
        // PASAMOS EL ADAPTADOR AL LIST VIEW
        lvResul.setAdapter(adaptador);
        db.close();

    }
}