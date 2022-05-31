package com.david.appflotantes;

import androidx.appcompat.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

    // PASO 1 SE DECLARAN LOS ELEMENTOS UTILIZADOS PARA POSTERIORMENTE ENLAZARLOS CON LA VISTA
    private TextInputLayout tilNombre, tilTelefono, tilDireccion;
    private EditText etNombre, etTelefono, etDireccion;
    private Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // PASO 2 ENLAZAR LOS CONTROLADORES DE LA VISTA CON LAS VARIABLES LOCALES
        tilNombre = (TextInputLayout) findViewById(R.id.tilNombre);
        tilTelefono = (TextInputLayout) findViewById(R.id.tilTelefono);
        tilDireccion = (TextInputLayout) findViewById(R.id.tilDireccion);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etDireccion = (EditText) findViewById(R.id.etDireccion);
        btnAceptar = (Button)findViewById(R.id.btnAceptar);

        // SE AÑADE AL EDITTEXT EL MÉTODO PARA REALIZAR LA COMPROBACIÓN EN TIEMPO REAL
        etNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // PARA LIMPIAR LOS ERRRORES AL MOMENTO DE ESCRIBIR UN CARACTER EN EL CAMPO
                tilNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilTelefono.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etDireccion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilDireccion.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // CREAR EL OYENTE DEL BOTON
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaDatos();
            }
        });

    }

    private void validaDatos(){
        // SE DECLARAN LOS ELEMENTOS Y SE LES ASIGNA EL VALOR DE LOS EDIT TEXT
        String nombre = tilNombre.getEditText().getText().toString();
        String telefono = tilTelefono.getEditText().getText().toString();
        String correo = tilDireccion.getEditText().getText().toString();

        // SE ALMACENA EL VALOR QUE REGRESAN LOS MÉTODOS PARA VERIFICAR SI SON O NO SON VÁLIDOS
        boolean n = nombreAceptado(nombre);
        boolean t = telefonoAceptado(telefono);
        boolean d = direccionAceptado(correo);

        // VERIFICA QUE TODOS SEAN VALORES VÁLIDOS
        if (n && t && d) {
            // REALIZA EL TOAST
            Toast.makeText(this, "DATOS CORRECTOS :D", Toast.LENGTH_LONG).show();
        }
    }

    private boolean nombreAceptado(String nombre){
        // SE ASEGURA QUE TENGA DATOS ESPECÍFICOS DE UN NOMBRE
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        // Y NO PASE DEL LÍMTE PERMITIDO
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            tilNombre.setError("Nombre no válido XC");
            return false;
        }else{
            tilNombre.setError(null);
        }
        return true;
    }

    private boolean telefonoAceptado(String telefono){
        // VERIFICA QUE SEA UN VERDADERO NÚMERO TELEFÓNICO Y NO PASE DEL LÍMTE PERMITIDO
        if (!Patterns.PHONE.matcher(telefono).matches() || telefono.length() > 10) {
            tilTelefono.setError("Teléfono no válido XC");
            return false;
        }else{
            tilTelefono.setError(null);
        }
        return true;
    }

    private boolean direccionAceptado(String direccion){
        // SOLO SE VERIFICA QUE NO SE PASE DEL LÍMITE PERMITIDO
        if (direccion.length() > 50) {
            tilDireccion.setError("Dirección no válida XC");
            return false;
        }else{
            tilDireccion.setError(null);
        }
        return true;
    }

}
