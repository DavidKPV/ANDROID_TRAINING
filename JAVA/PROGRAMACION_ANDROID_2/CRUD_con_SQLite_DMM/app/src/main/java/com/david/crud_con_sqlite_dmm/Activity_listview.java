package com.david.crud_con_sqlite_dmm;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class Activity_listview extends AppCompatActivity {
    // DECLARAMOS LOS OBJETOS A UTILIZAR
    ListView lvUsuarios;
    ArrayList<String> listaInformacion;
    ArrayList<usuario> listaUsuario;
    MySQLiteHelper conexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        // ENLAZAMOS LO OBJETOS CON LA VISTA
        lvUsuarios = (ListView) findViewById(R.id.lvUsuarios);

        // REALIZAMOS LA CONEXIÓN CON LA BASE DE DATOS
        conexion = new MySQLiteHelper(this, "bd_usuario", null, 1);
        // SE INVICA AL MÉTODO QUE CONSULTARÁ TODOS LOS REGISTROS
        consultarRegistros();
        obtenerLista();

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaInformacion);
        lvUsuarios.setAdapter(adaptador);

        // DENTRO DE UN TOAST SE VERÁN LOS DATOS SELECCIONADOS POR EL USUARIO AL DARLE CLIC A CUALQUIER REGISTRO
        lvUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion = "ID: "+listaUsuario.get(position).getId()+"\n";
                informacion += "NOMBRE: "+listaUsuario.get(position).getNombre()+"\n";
                informacion += "DOMICILIO: "+listaUsuario.get(position).getDomicilio()+"\n";
                informacion += "EMAIL: "+listaUsuario.get(position).getEmail()+"\n";
                informacion += "CONTRASEÑA: "+listaUsuario.get(position).getPassword();
            }
        });
    }

    private void consultarRegistros(){
        SQLiteDatabase sql = conexion.getReadableDatabase();
        usuario usuarioObjeto= null;
        listaUsuario = new ArrayList<usuario>();

        // SE REALIZA EL SELECT * FROM PARA OBTENER TODOS LOS REGISTROS
        Cursor datos = sql.rawQuery("SELECT * FROM "+utilidades.TABLA_USUARIO,null,null);

        // SE GENERA UN BUCLE PARA LEER CAMPO POR CAMPO Y AGREGARLOS AL RESULTADO DEL TOAST ANETRIORMENTE MENCIONADO
        while(datos.moveToNext()){
            usuarioObjeto = new usuario();
            usuarioObjeto.setId(datos.getInt(0));
            usuarioObjeto.setNombre(datos.getString(1));
            usuarioObjeto.setDomicilio(datos.getString(2));
            usuarioObjeto.setEmail(datos.getString(3));
            usuarioObjeto.setPassword(datos.getString(4));
            listaUsuario.add(usuarioObjeto);
        }
    }

    // SE OBTIENEN LOS DATOS
    private void obtenerLista(){
        listaInformacion = new ArrayList<String>();
        // SE AÑADEN SOLO EL ID Y EL NOMBRE DE CADA DATO
        for(int con=0; con<listaUsuario.size(); con++){
            listaInformacion.add(listaUsuario.get(con).getId()+" - "+listaUsuario.get(con).getNombre());
        }
    }
}