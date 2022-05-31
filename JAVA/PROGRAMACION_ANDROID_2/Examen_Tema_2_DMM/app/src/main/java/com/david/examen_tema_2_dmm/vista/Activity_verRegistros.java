package com.david.examen_tema_2_dmm.vista;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.david.examen_tema_2_dmm.controlador.Cita;
import com.david.examen_tema_2_dmm.R;
import com.google.android.material.textfield.TextInputLayout;

public class Activity_verRegistros extends AppCompatActivity {
    // SE DECLARAN LOS OBJETOS UTILIZADOS
    TextInputLayout tilClave;
    Button btnBuscar;
    TextView tvResul;
    ImageView ivResul;
    Cita cita;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_registros);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_baseline_search_24);
        cita = new Cita(this);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        tvResul = (TextView) findViewById(R.id.tvResul);
        ivResul = (ImageView) findViewById(R.id.ivResul);
        tilClave = (TextInputLayout) findViewById(R.id.tilClave);

        // OYENTE DEL BOTÓN BUSCAR
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });
    }
    private void buscar(){
        // OBTENEMOS EL ID
        String definitivo;
        String id = tilClave.getEditText().getText().toString();
        if(!id.isEmpty()){
            String[] resultado = new String[1];
            resultado = cita.obtenerDatos(id);
            if(resultado[0] != null) {
                // VAMOS AMOLDANDO LA INFO OBTENIDA
                definitivo = "Nombre: " + resultado[0] + "\n";
                definitivo += "Apellido Paterno: " + resultado[1] + "\n";
                definitivo += "Apellido Materno: " + resultado[2] + "\n";
                definitivo += "Calle: " + resultado[3] + "\n";
                definitivo += "No Interior: " + resultado[4] + "\n";
                definitivo += "No Exterior: " + resultado[5] + "\n";
                definitivo += "Colonia: " + resultado[6] + "\n";
                definitivo += "Municipio: " + resultado[7] + "\n";
                definitivo += "Estado: " + resultado[8] + "\n";
                definitivo += "País: " + resultado[9] + "\n";
                definitivo += "Telefono: " + resultado[10] + "\n";
                definitivo += "E-Mail: " + resultado[11] + "\n";
                definitivo += "Sexo: " + resultado[12] + "\n";
                definitivo += "Edad: " + resultado[13] + "\n";
                definitivo += "FECHA DE CITA:\n";
                definitivo += resultado[14] + " / " + resultado[15] + " / " + resultado[16]+"\n";
                definitivo += "Peso (Kg): " + resultado[17] + "\n";
                definitivo += "Estatura (m): " + resultado[18];

                // OBTENEMOS LA IMAGEN
                byte[] imagenResul = cita.getImagen();
                Bitmap bitmap = BitmapFactory.decodeByteArray(imagenResul, 0, imagenResul.length);

                // IMPRIMIMOS LOS RESULTADOS
                tvResul.setText(definitivo);
                ivResul.setImageBitmap(bitmap);
            }
        }
        else {
            tilClave.setError("Ingresa el IDENTIFICADOR DE LA CITA");
            Toast.makeText(getApplicationContext(), "INGRESA EL IDENTIFICADOR", Toast.LENGTH_LONG).show();
        }
    }
}