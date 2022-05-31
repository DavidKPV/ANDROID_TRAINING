package com.david.contenprovider_dmm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // DECLARAR LOS OBJETOS
    Button btnAgregarContacto, btnVerContactos;
    TextView tvNombre, tvTelefono;

    static final int PICK_CONTACT = 1;
    final List<String> listaPermisos = new ArrayList<String>();
    // RECORRER PERMISOS
    final private int REQUEST_MULTIPLE_PERMISSIONS = 124;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ENLAZAMOS LOS OBJETOS CON LA VISTA
        btnAgregarContacto = (Button) findViewById(R.id.btnAregarContacto);
        btnVerContactos = (Button) findViewById(R.id.btnVerContactos);
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);

        // LLAMADA A FUNCIONES DE PERMISOS
        verificarPermisos();

        // CREAR EL OYENTE DEL BOTÓN
        btnAgregarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CAMBIAMOS A LA SEGUNDA ACTIVIDAD
                Intent cambio = new Intent(getApplicationContext(), Activity_registro.class);
                startActivity(cambio);
            }
        });

        btnVerContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ACCEDEMOS A LOS CONTACTOS DEL DISPOSITIVO
                Intent intent1 = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent1, PICK_CONTACT);
            }
        });
    }

    private void verificarPermisos(){
        List<String> listaPermisosNec = new ArrayList<String>();
        // COMPROBAR QUE PERMISOS FALTAN DE PERMITIR
        if(comprobarPermisos(Manifest.permission.READ_CONTACTS) == false)
            listaPermisosNec.add("Lectura de Contactos");
        if(comprobarPermisos(Manifest.permission.WRITE_CONTACTS) == false)
            listaPermisosNec.add("Escritura de Contactos");
        if(comprobarPermisos(Manifest.permission.INTERNET) == false)
            listaPermisosNec.add("Internet");
        if(comprobarPermisos(Manifest.permission.READ_EXTERNAL_STORAGE) == false)
            listaPermisosNec.add("Lectura de Almacenamiento");
        if(comprobarPermisos(Manifest.permission.WRITE_EXTERNAL_STORAGE) == false)
            listaPermisosNec.add("Escritura de Almacenamiento");
        // EN BASE A LOS PERMISOS QUE NO ESTÁN CONCEDIDOS SE LE MUESTRAN AL USUARIO Y LE PIDO LOS OTORGUE
        if(listaPermisosNec.size() > 0){
            String mensaje = "Necesitas Otorgar los Permisos de ";
            for(int contador=0; contador<listaPermisosNec.size(); contador++){
                mensaje = mensaje +"  "+ listaPermisosNec.get(contador);
            }
            mostrarDialogo(mensaje, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    requestPermissions(listaPermisos.toArray(new String[listaPermisos.size()]), REQUEST_MULTIPLE_PERMISSIONS);
                }
            });
        }
    }

    private boolean comprobarPermisos(String permiso){
        if(checkSelfPermission(permiso) != PackageManager.PERMISSION_GRANTED){
            listaPermisos.add(permiso);
            return false;
        }
        else{
            return true;
        }
    }
    private void mostrarDialogo(String mensaje, DialogInterface.OnClickListener okOyente){
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(mensaje)
                .setPositiveButton("ACEPTAR", okOyente)
                .setNegativeButton("CANCELAR", null)
                .create().show();
    }
    // ESTE MÉTODO ES FORZOSO LLAMARSE ASÍ
    public void onActivityResult(int reqCode, int resultCode, Intent datos){
        super.onActivityResult(reqCode, resultCode, datos);
        if(reqCode == PICK_CONTACT){
            if(resultCode == Activity.RESULT_OK){
                Uri contactos = datos.getData(); // OBTENEMOS LA URI DE LOS CONTACTOS
                // OBTENEMOS TODA LA INFORMACIÓN DENTRO DEL CURSOR
                Cursor cursorContactos = managedQuery(contactos, null, null, null, null);
                if(cursorContactos.moveToFirst()){
                    // OBTENEMOS EL NOMBRE DEL TELÉFONO Y SU ID
                    String nombre = cursorContactos.getString(cursorContactos.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String id_contacto = cursorContactos.getString(cursorContactos.getColumnIndex(ContactsContract.Contacts._ID));

                    // COLOCAMOS EL NOMBRE Y EL ID DEL CONTACTO
                    tvNombre.setText("ID: "+id_contacto+" Nombre: "+nombre);

                    // OBTENEMOS MEDIANTE EL ID EL NÚMERO DEL TELÉFONO
                    Cursor cursorTel = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +"="+id_contacto,
                            null,
                            null);
                    // OBTENEMOS EL NÚMERO DE FILAS DENTRO DEL CURSOR DEL TELÉFONO
                    int filas = cursorTel.getCount();

                    // POSICIONAMOS EL CURSOR EN EL PRIMER REGISTRO
                    cursorTel.moveToFirst();
                    // DECLARAMOS LA VARIABLE DE DONDE OBTENDREMOS EL TELÉFONO
                    String telefonos = "";
                    // CON EL CICLO FOR OBTENEMOS EL VALOR DEL O LOS TELÉFONOS ENCONTRADOS DE ACUERDO AL ID DEL USUARIO
                    for(int conta=0; conta<filas; conta++){
                        telefonos = telefonos+" "+cursorTel.getString(cursorTel.getColumnIndex("data1"));
                    }

                    // IMPRIMIMOS EN LA VARIABLE CON LOS TELÉFONOS ENCONTRADOS
                    tvTelefono.setText("Teléfono(s): "+telefonos);
                }
            }
        }
    }
}