package com.david.contenprovider_dmm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentProviderOperation;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class Activity_registro extends AppCompatActivity {
    // DECLARAMOS LOS OBJETOS
    Button btnSave;
    EditText etNombre, etTelefono;
    // EL MÉTODO DE BACK PARA LA FLECHA
    @Override
    public boolean onSupportNavigateUp() {
        // PARA REGRESAR A LA ACTIVITY ANTERIOR
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        // ACTIVVAMOS LA FLECHA
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // ENLAZAMOS LOS OBJETOS CON LA VISTA
        btnSave = (Button) findViewById(R.id.btnSave);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etTelefono = (EditText) findViewById(R.id.etTelefono);

        // SE CREA EL OYENTE DEL BOTÓN
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                almacenarContacto();
            }
        });
    }
    private void almacenarContacto(){
        // OBTENEMOS LOS DATOS INGRESADOS
        String nombre = etNombre.getText().toString();
        String telefono = etTelefono.getText().toString();
        // VERIFICAMOS QUE LOS DATOS NO ESTÉN VACÍOS Y QUE EL TELÉFONO TENGA LAS CARACTERÍSTICAS NECESARIAS
        boolean vTelefono = validaTel(telefono);
        // VERIFICAMOS QUE TODO ESTÉ CORRECTO
        if(!nombre.isEmpty()){
            if(vTelefono){
                try {
                    // AGREGAMOS EL CONTACTO AL PROVEEDOR DE CONTENIDOS
                    // DECLARAMOS UN ARRAY LIST DE TIPO "CONTENT PROVIDER OPERATION"
                    ArrayList<ContentProviderOperation> operacion = new ArrayList<ContentProviderOperation>();
                    operacion.add(ContentProviderOperation.newInsert(
                            ContactsContract.RawContacts.CONTENT_URI)
                            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                            .build());

                    // AÑADIMOS EL VALOR DEL NOMBRE
                    operacion.add(ContentProviderOperation.newInsert(
                            ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, nombre)
                            .build());
                    // AÑADIMOS EL VALOR DEL TELÉFONO
                    operacion.add(ContentProviderOperation.newInsert(
                            ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, telefono)
                            .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                            .build());
                    // SE LE SOLICITA AL PROVEEDOR DE CONTENIDO QUE CREE UN NUEVO CONTACTO
                    getContentResolver().applyBatch(ContactsContract.AUTHORITY, operacion);

                    // SE LIMPIAN LAS CAJAS DE TEXTO
                    etNombre.setText(null);
                    etTelefono.setText(null);

                    Toast.makeText(getApplicationContext(),"Contacto Almacenado EXITOSAMENTE", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Ha ocurrido un Error Inesperado", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"EL TELÉFONO NO CUMPLE CON LAS CARACTERÍSTICAS DE 10 DÍGITOS", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"NO DEBES DEJAR VACÍO EL NOMBRE", Toast.LENGTH_LONG).show();
        }
    }

    // MÉTODO QUE VALIDA EL TELÉFONO
    private boolean validaTel(String telefono){
        if(!telefono.isEmpty() && telefono.length() > 9 && telefono.length() < 11){
            return true;
        }
        else{
            return false;
        }
    }

}