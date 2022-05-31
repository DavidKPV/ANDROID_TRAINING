package com.david.crud_con_sqlite_dmm;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
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
public class Activity_insertar extends AppCompatActivity {
    // DECLARAMOS LOS OBJETOS A UTILIZAR
    Button btnGuardar;
    TextInputLayout tilNombre, tilDomicilio, tilEmail, tilPassword;
    EditText etNombre, etDomicilio, etEmail, etPassword;
    MySQLiteHelper mySQLiteHelper;
    SQLiteDatabase sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        // ENLAZAMOS LOS OBJETOS CON LA VISTA
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        tilNombre = (TextInputLayout) findViewById(R.id.tilNombre);
        tilDomicilio = (TextInputLayout) findViewById(R.id.tilDomicilio);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etDomicilio = (EditText) findViewById(R.id.etDomicilio);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        // VALIDACIONES DENTRO DE LOS CAMPOS
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
        // OYENTE DEL BOTÓN GUARDAR
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });

    }
    // MÉTODO QUE ALMACENARÁ LOS DATOS
    private void guardarDatos(){
        // OBTENEMOS LOS VALORES DE LOS CAMPOS DE TEXTO
        String nombre = tilNombre.getEditText().getText().toString();
        String domicilio = tilDomicilio.getEditText().getText().toString();
        String email = tilEmail.getEditText().getText().toString();
        String pass = tilPassword.getEditText().getText().toString();
        // GUARDAMOS LOS VALORES DE LAS LLAMADAS DE LOS MÉTODOS
        boolean nom = esNombreValido(nombre);
        boolean domi = esDireccionValido(domicilio);
        boolean correo = esCorreoValido(email);

        if(nom && domi && correo && !pass.isEmpty()){
            // LLAMAMOS A LA BASE DE DATOS
            mySQLiteHelper = new MySQLiteHelper(getApplicationContext(),"bd_usuario",null,1);
            sql = mySQLiteHelper.getWritableDatabase();
            // AGREGAMOS LOS CAMPOS EN LA BASE DE DATOS
            ContentValues contenedor = new ContentValues();
            contenedor.put(utilidades.CAMPO_NOMBRE, nombre);
            contenedor.put(utilidades.CAMPO_DOMICILIO, domicilio);
            contenedor.put(utilidades.CAMPO_EMAIL, email);
            contenedor.put(utilidades.CAMPO_PASSWORD, pass);
            Long idresul = sql.insert(utilidades.TABLA_USUARIO, utilidades.CAMPO_NOMBRE, contenedor);

            limpiarCajas();
            // AL FINALIZAR LA INSERCIÓN SE MOSTRARÁ EL ID CON EL QUE FUE ASIGNADO
            Toast.makeText(this, "Número de Registro: "+idresul, Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "DATOS INVÁLIDOS", Toast.LENGTH_LONG).show();
        }
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
        else{
            tilNombre.setError(null); return true;
        }
    }
    private boolean esDireccionValido(String direccion){
        if(direccion.equals("") || direccion.length() >= 30 ){
            tilDomicilio.setError("Dirección inválida"); return  false;
        }
        else{
            tilDomicilio.setError(null); return true;
        }
    }
    private boolean esCorreoValido(String correo){
        Pattern patron = Patterns.EMAIL_ADDRESS;
        if(correo.equals("") || !patron.matcher(correo).matches()){
            tilEmail.setError("Correo electrónico inválido"); return  false;
        }
        else{
            tilEmail.setError(null); return true;
        }
    }
}