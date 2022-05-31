package com.david.almacenamientodearchivos;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class Activity_registro extends AppCompatActivity {
    // DECLARAMOS LOS OBJETOS A UTILIZAR
    EditText etNombre, etApellidoP, etApellidoM, etEdad, etSexo, etCorreo, etPassword;
    Button btnRegistro;
    // SE CREA LA VARIABLE CON EL NOMBRE DEL FICHERO DONDE SE ALOMACENARÁN LOS DATOS
    public static final String FILE_NAME = "archivo.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        // SE ENLAZAN LOS OBJETOS CON LA VISTA
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellidoP = (EditText) findViewById(R.id.etApellidoP);
        etApellidoM = (EditText) findViewById(R.id.etApellidoM);
        etEdad = (EditText) findViewById(R.id.etEdad);
        etSexo = (EditText) findViewById(R.id.etSexo);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);

        // SE CREA EL OYENTE DEL BOTÓN QUE REGISTRARÁ TODOS LOS DATOS OBTENIDOS
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registro();
            }
        });
    }
    private void registro(){
        // OBTENEMOS TODOS LOS DATOS DE LOS CAMPOS DE TEXTO
        String nombre = etNombre.getText().toString();
        String apellidoP = etApellidoP.getText().toString();
        String apellidoM = etApellidoM.getText().toString();
        String edad = etEdad.getText().toString();
        String sexo = etSexo.getText().toString();
        String correo = etCorreo.getText().toString();
        String password = etPassword.getText().toString();
        // VALIDAMOS QUE TODOS LOS CAMPOS ESTÉN LLENOS
        if(!nombre.isEmpty() && !apellidoP.isEmpty() && !apellidoM.isEmpty() && !edad.isEmpty() && !sexo.isEmpty()
                && !correo.isEmpty() && !password.isEmpty()){
            // SE LE ANEXA UN SALTO DE LÍNEA
            nombre += "\n";
            apellidoP += "\n";
            apellidoM += "\n";
            edad += "\n";
            sexo += "\n";
            correo += "\n";
            password += "\n";

            // SE LLAMA A LA SIGUIENTE CLASE PARA PODER ESCRIBIR SOBRE UN ARCHIVO
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE); // ABRIMOS EL ARCHIVO
                // SE GUARDAN TODOS LOS DATOS EN MANERA DE BYTES
                fileOutputStream.write(nombre.getBytes());
                fileOutputStream.write(apellidoP.getBytes());
                fileOutputStream.write(apellidoM.getBytes());
                fileOutputStream.write(edad.getBytes());
                fileOutputStream.write(sexo.getBytes());
                fileOutputStream.write(correo.getBytes());
                fileOutputStream.write(password.getBytes());
                // SE LIMPIAN TODAS LAS CAJAS DE TEXTO
                etNombre.getText().clear(); etApellidoP.getText().clear();
                etApellidoM.getText().clear(); etEdad.getText().clear();
                etSexo.getText().clear(); etCorreo.getText().clear();
                etPassword.getText().clear();
                // MOSTRAMOS UNA NOTIFICACIÓN DE TIPO TOAST EN DONDE SE OBSERVARÁ LA RUTA DEL ARCHIVO QUE CONTIENE LOS DATOS
                Toast.makeText(getApplicationContext(), "Datos Guardados en... "+getFilesDir()+" / "+FILE_NAME, Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // VERIFICAMOS SI EL ARCHIVO TIENE VALOR NULO
                if(fileOutputStream != null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "RELLENA TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
        }
    }
}