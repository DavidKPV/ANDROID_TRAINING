package com.david.examen_tema_2_dmm.vista;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.david.examen_tema_2_dmm.controlador.Cita;
import com.david.examen_tema_2_dmm.R;
import com.google.android.material.textfield.TextInputLayout;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Activity_registro extends AppCompatActivity {
    // SE DECLARA LOS OBJETOS A UTILIZAR
    Button btnElegirImagen, btnGuardar;
    LinearLayout llVerRegistros;
    TextInputLayout tilNombre, tilApellidoP, tilApellidoM, tilCalle, tilNoInterior, tilNoExterior, tilColonia, tilMunicipio, tilEstado, tilPais;
    TextInputLayout tilTelefono, tilCorreo, tilEdad, tilDia, tilMes, tilYear, tilEstatura, tilPeso;
    Spinner spSexo;
    ImageView ivImagen;
    Cita cita;
    final static int CODE_GALLERY = 888;
    // MÉTODO QUE CIERRA LA APLICACIÓN EN CASO DE DE PRESIONAR LA FLECHA DE RETORNO
    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_baseline_import_contacts_24);
        // SE ENLAZAN LOS OBJETOS CON LA VISTA
        tilNombre = (TextInputLayout) findViewById(R.id.tilNombre);
        tilApellidoP = (TextInputLayout) findViewById(R.id.tilApellidoP);
        tilApellidoM = (TextInputLayout) findViewById(R.id.tilApellidoM);
        tilCalle = (TextInputLayout) findViewById(R.id.tilCalle);
        tilNoInterior = (TextInputLayout) findViewById(R.id.tilNoInterior);
        tilNoExterior = (TextInputLayout) findViewById(R.id.tilNoExterior);
        tilColonia = (TextInputLayout) findViewById(R.id.tilColonia);
        tilMunicipio = (TextInputLayout) findViewById(R.id.tilMunicipio);
        tilEstado = (TextInputLayout) findViewById(R.id.tilEstado);
        tilPais = (TextInputLayout) findViewById(R.id.tilPais);
        tilTelefono = (TextInputLayout) findViewById(R.id.tilTelefono);
        tilCorreo = (TextInputLayout) findViewById(R.id.tilCorreo);
        tilEdad = (TextInputLayout) findViewById(R.id.tilEdad);
        tilDia = (TextInputLayout) findViewById(R.id.tilDia);
        tilMes = (TextInputLayout) findViewById(R.id.tilMes);
        tilYear = (TextInputLayout) findViewById(R.id.tilYear);
        tilEstatura = (TextInputLayout) findViewById(R.id.tilEstatura);
        tilPeso = (TextInputLayout) findViewById(R.id.tilPeso);
        spSexo = (Spinner) findViewById(R.id.spSexo);
        btnElegirImagen = (Button) findViewById(R.id.btnElegirImagen);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        ivImagen = (ImageView) findViewById(R.id.ivImagen);
        llVerRegistros = (LinearLayout) findViewById(R.id.llVerRegistros);

        // RELLENAMOS EL SPINNER
        // CREAR UN ADAPTER  PARA EL SPINNER                                       contexto         values          clase
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.opciones_sexo,
                android.R.layout.simple_spinner_item);
        spSexo.setAdapter(adaptador);

        // OYENTE DEL BOTÓN ELEGIR IMAGEN
        btnElegirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        Activity_registro.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALLERY
                );
            }
        });
        // OYENTE DE GUARDAR
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });
        // OYENTE PARA VER REGISTROS
        llVerRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CAMBIAMOS A LA ACTIVIDAD QUE MOSTRARÁ LOS REGISTROS
                Intent cambio = new Intent(getApplicationContext(), Activity_verRegistros.class);
                startActivity(cambio);
            }
        });
    }

    private void guardar(){
        // OBTENEMOS TODOS LOS DATOS DEL FORMULARIO
        String nombre = tilNombre.getEditText().getText().toString();
        String apellidop = tilApellidoP.getEditText().getText().toString();
        String apellidom = tilApellidoM.getEditText().getText().toString();
        String calle = tilCalle.getEditText().getText().toString();
        String noInterior = tilNoInterior.getEditText().getText().toString();
        String noExterior = tilNoExterior.getEditText().getText().toString();
        String colonia = tilColonia.getEditText().getText().toString();
        String municipio = tilMunicipio.getEditText().getText().toString();
        String estado = tilEstado.getEditText().getText().toString();
        String pais = tilPais.getEditText().getText().toString();
        String telefono = tilTelefono.getEditText().getText().toString();
        String email = tilCorreo.getEditText().getText().toString();
        String sexo = spSexo.getSelectedItem().toString();
        String edad = tilEdad.getEditText().getText().toString();
        String dia = tilDia.getEditText().getText().toString();
        String mes = tilMes.getEditText().getText().toString();
        String year = tilYear.getEditText().getText().toString();
        String estatura = tilEstatura.getEditText().getText().toString();
        String peso = tilPeso.getEditText().getText().toString();
        // TRANSFORMAMOS LA IMAGEN A UN ARREGLO DE BYTES
        byte[] imagen = transformarImagenAByte(ivImagen);

        // VALIDAMOS QUE TODOS LOS CAMPOS ESTÉN LLENOS
        if(!nombre.isEmpty() && !apellidop.isEmpty() && !apellidom.isEmpty() && !calle.isEmpty() && !noInterior.isEmpty()
        && !noExterior.isEmpty() && !colonia.isEmpty() && !municipio.isEmpty() && !estado.isEmpty() && !pais.isEmpty()
                && !telefono.isEmpty() && !email.isEmpty() && !sexo.equals("Selecciona...") && !edad.isEmpty() && !dia.isEmpty()
                && !mes.isEmpty() && !year.isEmpty() && !estatura.isEmpty() && !peso.isEmpty() && imagen != null){
            // INSTANCIAMOS EL OBJETO CITA
            cita = new Cita(getApplicationContext());
            cita.insertarDatos(
                    nombre,
                    apellidop,
                    apellidom,
                    calle,
                    colonia,
                    municipio,
                    estado,
                    pais,
                    telefono,
                    email,
                    sexo,
                    Integer.parseInt(noInterior),
                    Integer.parseInt(noExterior),
                    Integer.parseInt(edad),
                    Integer.parseInt(dia),
                    Integer.parseInt(mes),
                    Integer.parseInt(year),
                    Float.parseFloat(estatura),
                    Float.parseFloat(peso),
                    imagen
            );
            limpiarTodo();
        }
        else{
            Toast.makeText(getApplicationContext(), "NO DEJES NIGÚN CAMPO VACÍO", Toast.LENGTH_LONG).show();
        }
    }
    private byte[] transformarImagenAByte(ImageView imagen){
        byte[] byteArray = null;
        if(imagen.getDrawable() != null) {
            // TRANSFORMAMOS LA IMAGEN COMO BITMAP
            Bitmap bitmap = ((BitmapDrawable) imagen.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();
        }
        return byteArray;
    }

    private void limpiarTodo(){
        tilNombre.getEditText().getText().clear();
        tilApellidoP.getEditText().getText().clear();
        tilApellidoM.getEditText().getText().clear();
        tilCalle.getEditText().getText().clear();
        tilNoInterior.getEditText().getText().clear();
        tilNoExterior.getEditText().getText().clear();
        tilColonia.getEditText().getText().clear();
        tilMunicipio.getEditText().getText().clear();
        tilEstado.getEditText().getText().clear();
        tilPais.getEditText().getText().clear();
        tilTelefono.getEditText().getText().clear();
        tilCorreo.getEditText().getText().clear();
        tilEdad.getEditText().getText().clear();
        tilDia.getEditText().getText().clear();
        tilMes.getEditText().getText().clear();
        tilYear.getEditText().getText().clear();
        tilEstatura.getEditText().getText().clear();
        tilPeso.getEditText().getText().clear();
        ivImagen.setImageResource(R.drawable.corazon);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // LANZAMOS EL ITENT IMPLÍCITO DE LA GALERÍA
                Intent galeria = new Intent(Intent.ACTION_PICK);
                galeria.setType("image/*");
                startActivityForResult(galeria, CODE_GALLERY);
            }
            else{
                Toast.makeText(getApplicationContext(), "DEBES CONCEDER EL PERMISO PARA ACCEDER A LA GALERÍA",
                        Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream entrada = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(entrada);
                ivImagen.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}