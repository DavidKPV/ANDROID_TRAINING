package com.david.beanssqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // DECLARAMOS LOS OBJETOS A UTLIZAR
    EditText etNombre, etApellidoP, etApellidoM, etEdad, etPromedio, etId;
    Button btnRegistrar, btnActualizar, btnEliminar, btnBuscar;
    Alumnos alumnos;
    String nombre, apellidoP, apellidoM, edad, promedio, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ENLAZAMOS LA VISTA CON LOS OBJETOS
        etId = (EditText) findViewById(R.id.etId);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellidoP = (EditText) findViewById(R.id.etApellidoP);
        etApellidoM = (EditText) findViewById(R.id.etApellidoM);
        etEdad = (EditText) findViewById(R.id.etEdad);
        etPromedio = (EditText) findViewById(R.id.etPromedio);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);

        alumnos = new Alumnos(this);

        // SE CREAN LOS OUENTES DE LOS BOTONES
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarDatos();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarDatos();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarDatos();
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarDatos();
            }
        });
    }

    // MÉTOD QUE OBTIENE LOS DATOS DE LAS CAJAS DE TEXTO Y REALIZA LAS VALIDACIONES
    private boolean checkDatos(){
        id = etId.getText().toString();
        nombre = etNombre.getText().toString();
        apellidoP = etApellidoP.getText().toString();
        apellidoM = etApellidoM.getText().toString();
        edad = etEdad.getText().toString();
        promedio = etPromedio.getText().toString();

        return true;
    }

    private void limpiarCajas(){
        etId.getText().clear();
        etNombre.getText().clear();
        etApellidoP.getText().clear();
        etApellidoM.getText().clear();
        etEdad.getText().clear();
        etPromedio.getText().clear();
    }

    private void registrarDatos(){
        if(checkDatos())
            alumnos.registro(nombre,apellidoP,apellidoM,Integer.parseInt(edad),Float.parseFloat(promedio));
        limpiarCajas();
    }

    private void actualizarDatos(){
        if(checkDatos())
            alumnos.actualizacion(Integer.parseInt(id),nombre);
        limpiarCajas();
    }

    private void eliminarDatos(){
        if(checkDatos())
            alumnos.eliminar(Integer.parseInt(id));
        limpiarCajas();
    }

    private void buscarDatos(){
        if(checkDatos()) {
            String[][] datosEncontrados = new String[][]{alumnos.buscar(Integer.parseInt(id))};
            if(datosEncontrados[0][0] != "")
                Toast.makeText(getApplicationContext(), "DATOS ENCONTRADOS:\n"+datosEncontrados[0][0], Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "NO SE ENCONTRÓ REGISTRO CON ESE ID", Toast.LENGTH_LONG).show();
        }
        limpiarCajas();
    }
}