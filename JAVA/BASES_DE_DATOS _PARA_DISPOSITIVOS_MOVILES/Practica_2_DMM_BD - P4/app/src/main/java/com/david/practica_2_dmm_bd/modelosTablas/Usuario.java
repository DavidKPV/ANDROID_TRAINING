package com.david.practica_2_dmm_bd.modelosTablas;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
public class Usuario {
    // DECLARAMOS LOS OBJETOS A UTILIZAR
    SQLiteDatabase db = null;
    Context context = null;
    String NombreTabla = "usuario";
    int cantidad = 0;
    // DECLARAMOS UN CONTENEDOR DE VALORES
    ContentValues contenedor = new ContentValues();
    // CREAMOS EL CONSTRUCTOR PARA ASIGNARLE LOS VALORES
    public Usuario(Context context, SQLiteDatabase db){
        this.db = db;
        this.context = context;
    }
    // MÉTODO DONDE COLOCAREMOS TODOS LOS DATOS DEL USUARIO
    public void Nuevo(String nombre, String telefono, String direccion, String sexo){
        // LE ASIGNAMOS LOS VALORES
        contenedor.put("nombre", nombre);
        contenedor.put("direccion", direccion);
        contenedor.put("telefono", telefono);
        contenedor.put("sexo", sexo);
        // INSERTAMOS EN LA BASE DE DATOS EL NUEVO REGISTRO QUE TRAE LOS DATOS EL CONTENEDOR
        db.insert(NombreTabla, null, contenedor);
        contenedor.clear();
        // COLOCAMOS UNA NOTIFICACIÓN TOAST DE QUE SE INGRESÓ CORRECTAMENTE EL USUARIO
        Toast.makeText(context,"Registro Exitoso", Toast.LENGTH_LONG).show();
    }
    // CREAMOS UN CURSOR PARA EL PROCESO DE CONSULTA
    public Cursor Consulta(){
        // OBTENEMOS LOS DATOS DE LA DB DENTRO DE UN CURSOR
        Cursor datosCursor = db.query(
                NombreTabla,
                null,
                null,
                null,
                null,
                null,
                null);
        // DEVOLVEMOS EL CURSOR YA CON LOS DATOS OBTENIDOS DE LA CONSULTA
        return datosCursor;
    }
    // CONSULTA QUE OBTIENE EL NÚMERO DE HOMBRES
    public int ConsultaHombres(){
        String[] columnas = new String[]{"nombre"};
        String[] valorWhere = new String[]{"MASCULINO"};
        Cursor datosHombre = db.query(
                NombreTabla,
                columnas,
                "sexo=?",
                valorWhere,
                null,
                null,
                null,
                null// Un array de strings representando las columnas que queremos recuperar.
        );
        return cantidad = datosHombre.getCount();
    }
    // CONSULTA QUE OBTIENE EL NÚMERO DE MUJERES
    public int ConsultaMujeres(){
        String[] columnas = new String[]{"nombre"};
        String[] valorWhere = new String[]{"FEMENINO"};
        Cursor datosHombre = db.query(
                NombreTabla,
                columnas,
                "sexo=?",
                valorWhere,
                null,
                null,
                null,
                null// Un array de strings representando las columnas que queremos recuperar.
        );
        return cantidad = datosHombre.getCount();
    }
}
