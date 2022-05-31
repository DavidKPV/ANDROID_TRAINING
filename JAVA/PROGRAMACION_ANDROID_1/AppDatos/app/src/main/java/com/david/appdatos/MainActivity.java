package com.david.appdatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // DECLARAR EL TIPO DE ELEMENTO -------------
    Spinner spanios;
    EditText etNombre;
    RadioGroup rgSexo;
    RadioButton rbHombre, rbMujer;
    Button btnEnviar;
    String Opcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SE INSTANCIA EL SPINNER Y SE ENCUENTRAN LOS ELEMENTOS POR EL ID
        etNombre = (EditText) findViewById(R.id.etNombre);
        spanios = (Spinner) findViewById(R.id.slista1);
        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);
        rbHombre = (RadioButton) findViewById(R.id.rbHombre);
        rbMujer = (RadioButton) findViewById(R.id.rbMujer);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        // CREAR UN ADAPTER  PARA EL SPINNER                                       contexto         values          clase
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.anios, android.R.layout.simple_spinner_item);
        spanios.setAdapter(adaptador);

        // SE CREA EL OYENTE PARA EL BOTON
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SE COLOCAN TODAS LAS INTRUCCIONES DEL OYENTE PARA RECIBIR LOS MENSAJES
                EnviarDatos();
            }
        });

        // OBTENER CUAL RADIO BUTTON DENTRO DEL RADIOGROUP FUE SELECCIONADO
        rgSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbHombre) {
                    Opcion="Hombre";
                } else if (checkedId == R.id.rbMujer) {
                    Opcion="Mujer";
                }
            }
        });
    }

    private void EnviarDatos(){
        String nombre = etNombre.getText().toString();
        String opcion = Opcion;
        if(nombre.isEmpty()){
            Toast.makeText(this, "INGRESA EL NOMBRE", Toast.LENGTH_LONG).show();
        }
        else{
            if(opcion == null) {
                Toast.makeText(this, "SELECCIONA UNA OPCIÓN DE GENERO", Toast.LENGTH_LONG).show();
            }
            else{
                try {
                    Integer edad = Integer.parseInt(spanios.getSelectedItem().toString());
                    Integer edadf = (2020-edad);

                    String edadfinal = String.valueOf(edadf);

                    // SE INSTANCIA EL INTENT
                    Intent inten1 = new Intent(this, SecondActivity.class);
                    inten1.putExtra("nombre", nombre);
                    inten1.putExtra("edadfinal", edadfinal);
                    inten1.putExtra("sexo", opcion);
                    // INICIAR LA ACTIVIDAD
                    startActivity(inten1);
                }
                catch (Exception e){
                    Toast.makeText(this, "INGRESA EL AÑO DE NACIMIENTO", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
