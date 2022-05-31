package com.david.examen_tema_v_dmm;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.david.examen_tema_v_dmm.services.Actualizar;
import com.david.examen_tema_v_dmm.services.Eliminar;
import com.david.examen_tema_v_dmm.services.Guardar;
public class FieldsActivity extends AppCompatActivity {
    // OBJETOS
    private TextView tvOpcion;
    private EditText etTitulo, etAsunto, etFecha, etDescripcion, etNota;
    private Button btnGuardar, btnActualizar, btnEliminar;
    // CLASES DE CRUD
    private Guardar guardar;
    private Actualizar actualizar;
    private Eliminar eliminar;
    private String titulo, asunto, fecha, descripcion, nota;
    private int id_tarea;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fields);
        // ACTIVAMOS FLECHA DE RETORNO
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        enlaces();
        // RECIBIMOS PARÁMETRO DE OPCIÓN
        Bundle datoOpcion = getIntent().getExtras();
        // VERIFICAMOS TIPO DE ACCIÓN
        switch(datoOpcion.getInt("op")){
                // INSERTAR
            case 1:
                tvOpcion.setText(datoOpcion.getString("opcion"));
                btnActualizar.setVisibility(View.GONE);
                btnEliminar.setVisibility(View.GONE);
                btnGuardar.setVisibility(View.VISIBLE);
                break;

                // ACTUALIZAR O ELIMINAR
            case 2:
                tvOpcion.setText("TAREA - "+datoOpcion.getString("titulo"));
                // OBTENEMOS LOS VALORES Y LOS COLOCAMOS DENTRO DE LOS CAMPOS
                id_tarea = datoOpcion.getInt("id_tarea");
                titulo = datoOpcion.getString("titulo");
                asunto = datoOpcion.getString("asunto");
                fecha = datoOpcion.getString("fecha");
                descripcion = datoOpcion.getString("descripcion");
                nota = datoOpcion.getString("nota");

                etTitulo.setText(titulo);
                etAsunto.setText(asunto);
                etFecha.setText(fecha);
                etDescripcion.setText(descripcion);
                etNota.setText(nota);

                btnActualizar.setVisibility(View.VISIBLE);
                btnEliminar.setVisibility(View.VISIBLE);
                btnGuardar.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        // OYENTES DE LOS BOTONES
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualiza();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elimina();
            }
        });
    }
    private void enlaces(){
        tvOpcion = (TextView) findViewById(R.id.tvOpcion);
        etTitulo = (EditText) findViewById(R.id.etTitulo);
        etAsunto = (EditText) findViewById(R.id.etAsunto);
        etFecha = (EditText) findViewById(R.id.etFecha);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        etNota = (EditText) findViewById(R.id.etNota);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
    }
    private boolean validaCampos(){
        titulo = etTitulo.getText().toString().trim();
        asunto = etAsunto.getText().toString().trim();
        fecha = etFecha.getText().toString().trim();
        descripcion = etDescripcion.getText().toString().trim();
        nota = etNota.getText().toString().trim();
        if(!titulo.isEmpty() && !asunto.isEmpty() && !fecha.isEmpty() && !descripcion.isEmpty() && !nota.isEmpty())
            return true;
        else
            return false;
    }
    private void limpiarCampos(){
        etTitulo.getText().clear();
        etAsunto.getText().clear();
        etFecha.getText().clear();
        etDescripcion.getText().clear();
        etNota.getText().clear();
    }
    private void toast(String cadena){
        Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_LONG).show();
    }
    private void guardar(){
        if(validaCampos()){
            guardar = new Guardar(titulo,asunto,fecha,descripcion,nota, getApplicationContext(), this);
            guardar.insertar();
            limpiarCampos();
        }
        else {
            toast("LLENA TODOS LOS CAMPOS");
        }
    }

    private void actualiza(){
        if(validaCampos()){
            actualizar = new Actualizar(id_tarea,titulo,asunto,fecha,descripcion,nota, getApplicationContext(), this);
            actualizar.actualizar();
        }
        else {
            toast("LLENA TODOS LOS CAMPOS");
        }
    }

    private void elimina(){
        eliminar = new Eliminar(id_tarea, getApplicationContext(), this);
        eliminar.eliminar();
        // REGRESAMOS A LA PANTALLA PRINCIPAL
        Intent main = new Intent(this, MainActivity.class);
        main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(main);
    }
}