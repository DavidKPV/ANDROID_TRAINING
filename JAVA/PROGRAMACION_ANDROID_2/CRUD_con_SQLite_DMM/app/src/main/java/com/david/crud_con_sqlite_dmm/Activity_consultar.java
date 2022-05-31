package com.david.crud_con_sqlite_dmm;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import java.util.regex.Pattern;

public class Activity_consultar extends AppCompatActivity {
    // DECLARAMOS LOS OBJETOS UTILIZADOS
    EditText etId, etNombre, etDomicilio, etEmail, etPassword;
    TextInputLayout tilId, tilNombre, tilDomicilio, tilEmail, tilPassword;
    Button btnBuscar, btnActualizar, btnEliminar;
    MySQLiteHelper conexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        // ENLAZAMOS LOS OBJETOS CON LA VISTA
        etId = (EditText) findViewById(R.id.etId);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etDomicilio = (EditText) findViewById(R.id.etDomicilio);
        etEmail = (EditText) findViewById(R.id.etEmail);
        tilId = (TextInputLayout) findViewById(R.id.tilId);
        tilNombre = (TextInputLayout) findViewById(R.id.tilNombre);
        tilDomicilio = (TextInputLayout) findViewById(R.id.tilDomicilio);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        // SE LLAMA A LA BASE DE DATOS
        conexion = new MySQLiteHelper(this, "bd_usuario", null, 1);
        // CREAR LOS OYENTES DE LOS TEXT INPUT LAYOUT
        etId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilId.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // PARA LIMPIAR LOS ERRORES DENTRO DEL PROCESO DE LLENADO DEL CAMPO
                tilNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etDomicilio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilDomicilio.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // CREAMOS LOS OYENTES DE LOS BOTONES
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarRegistro();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarRegistro();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarRegistro();
            }
        });
    }
    private void buscarRegistro(){
        // DECLARAREMOS QUE LEEREMOS DE LA BD
        SQLiteDatabase sql1 = conexion.getReadableDatabase();
        String[] valorId = new String[]{tilId.getEditText().getText().toString()};
        String[] campos = new String[]{
                utilidades.CAMPO_NOMBRE,
                utilidades.CAMPO_DOMICILIO,
                utilidades.CAMPO_EMAIL,
                utilidades.CAMPO_PASSWORD};
        // INTENTAR BUSCAR EL ID EN LA TABLA USUARIO Y LOS MUESTRA EN LOS CAMPOS CORRESPONDIENTES
        try {
            Cursor datos = sql1.query(
                    utilidades.TABLA_USUARIO,
                    campos,
                    utilidades.CAMPO_ID+"=?",
                    valorId,
                    null, null, null
            );
            datos.moveToFirst();
            etNombre.setText(datos.getString(0));
            etDomicilio.setText(datos.getString(1));
            etEmail.setText(datos.getString(2));
            etPassword.setText(datos.getString(3));
            sql1.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "EL ID NO EXISTE", Toast.LENGTH_LONG).show();
            limpiarCajas();
            sql1.close();
        }
    }

    private void actualizarRegistro(){
        // DECLARAMOS QUE ESCRIBIREMOS SOBRE LA BASE DE DATOS
        SQLiteDatabase sql2 = conexion.getWritableDatabase();
        String[] valorId = new String[]{tilId.getEditText().getText().toString()};
        ContentValues contenedor = new ContentValues();
        contenedor.put(utilidades.CAMPO_NOMBRE, tilNombre.getEditText().getText().toString());
        contenedor.put(utilidades.CAMPO_DOMICILIO, tilDomicilio.getEditText().getText().toString());
        contenedor.put(utilidades.CAMPO_EMAIL, tilEmail.getEditText().getText().toString());
        contenedor.put(utilidades.CAMPO_PASSWORD, tilPassword.getEditText().getText().toString());
        // SE ACTUALIZA Y EN CASO DE SER EXITOSO MOSTRARÁ UN MENSAJE INDICÁNDOLO
        sql2.update(utilidades.TABLA_USUARIO, contenedor, utilidades.CAMPO_ID+"=?",valorId);
        Toast.makeText(getApplicationContext(), "DATOS ACTUALIZADOS", Toast.LENGTH_LONG).show();
        limpiarCajas();
        sql2.close();
    }
    private void eliminarRegistro(){
        SQLiteDatabase sql3 = conexion.getWritableDatabase();
        // OBTENEMOS EL ID
        String[] valorId = new String[]{tilId.getEditText().getText().toString()};
        // SE ELIMINA EL REGISTRO CON EL ID SELECCIONADO
        sql3.delete(utilidades.TABLA_USUARIO, utilidades.CAMPO_ID+"=?", valorId);
        Toast.makeText(getApplicationContext(), "USUARIO ELIMINADO", Toast.LENGTH_LONG).show();
        // LIMPIAMOS EL TEXTO
        tilId.getEditText().getText().clear();
        limpiarCajas();
        sql3.close();
    }

    private void limpiarCajas(){
        etNombre.getText().clear();
        etDomicilio.getText().clear();
        etEmail.getText().clear();
        etPassword.getText().clear();
    }
    // MÉTODOS DE VALIDACIÓN
    private boolean esNombreValido(String nombre){
        if(nombre.equals("") || nombre.length() >= 20){
            tilNombre.setError("Nombre inválido"); return  false;
        }
        else
            tilNombre.setError(null); return true;
    }
    private boolean esDireccionValido(String direccion){
        if(direccion.equals("") || direccion.length() >= 30 ){
            tilDomicilio.setError("Dirección inválida"); return false;
        }
        else
            tilDomicilio.setError(null); return true;
    }
    private boolean esCorreoValido(String correo){
        Pattern patron = Patterns.EMAIL_ADDRESS;
        if(correo.equals("") || !patron.matcher(correo).matches()){
            tilEmail.setError("Correo electrónico inválido");
            return  false;
        }
        else{
            tilEmail.setError(null);
            return true;
        }
    }
}