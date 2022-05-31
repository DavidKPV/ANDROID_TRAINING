package com.david.almacenamientodearchivos;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import static com.david.almacenamientodearchivos.Activity_registro.FILE_NAME;

public class Activity_inicio extends AppCompatActivity {
    // SE DECLARAN LOS OBJETOS A UTILIZAR
    Button btnMostrar;
    TextView tvDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        // ENLAZAMOS LOS CONTROLADORES CON LA VISTA
        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        tvDatos = (TextView) findViewById(R.id.tvDatos);
        // CREAMOS EL OYENTE DEL BOTÓN
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verDatos();
            }
        });
    }

    private void verDatos(){
        // SE DECLARA EL OBJETO QUE AYUDARÁ A MANIPULAR EL FICHERO DE LOS DATOS
        FileInputStream fileInputStream = null;
        // OBTENEMOS EL ARCHIVO PARA RECUPERAR DATOS
        try {
            fileInputStream = openFileInput(FILE_NAME);
            // ABRIMOS EL ARCHIVO PARA LEERLO
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            // INSTANCIAMOS UN BUFFEREDREADER PARA LEER EL ARCHIVO ABIERTO
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            // DECLARAMOS UNA VARIABLE QUE ES EN DONDE SE ALMACENARÁ LOS DATOS GUARDADOS
            String texto;
            // SE COMENZARÁ A LEER EL ARCHIVO LÍNEA POR LÍNEA
            while((texto = bufferedReader.readLine()) != null){
                stringBuilder.append(texto).append("\n");

                // SE MUESTRA LA INFORMACIÓN REGISTRADA DENTRO DEL EDITTEXT
                tvDatos.setText(stringBuilder.toString()+"\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}