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

    // DECLARAMOS UN CONTENEDOR DE VALORES
    ContentValues contenedor = new ContentValues();

    // CREAMOS EL CONSTRUCTOR PARA ASIGNARLE LOS VALORES
    public Usuario(Context context, SQLiteDatabase db){
        this.db = db;
        this.context = context;
    }

    // MÉTODO DONDE COLOCAREMOS TODOS LOS DATOS DEL USUARIO
    public void Nuevo(String nombre, String telefono, String direccion){
        // LE ASIGNAMOS LOS VALORES
        contenedor.put("nombre", nombre);
        contenedor.put("direccion", direccion);
        contenedor.put("telefono", telefono);
        // INSERTAMOS EN LA BASE DE DATOS EL NUEVO REGISTRO QUE TRAE LOS DATOS EL CONTENEDOR
        db.insert(NombreTabla, null, contenedor);
        contenedor.clear();
        // COLOCAMOS UNA NOTIFICACIÓN TOAST DE QUE SE INGRESÓ CORRECTAMENTE EL USUARIO
        Toast.makeText(context,"Registro Exitoso", Toast.LENGTH_LONG).show();
    }

    // CREAMOS UN CURSOR PARA EL PROCESO DE CONSULTA
    public Cursor Consulta(){
        // OBTENEMOS LOS DATOS DE LA DB DENTRO DE UN CURSOR
        String[] columnas = new String[]{"idU","nombre", "direccion", "telefono"};
        String donde="direccion=?";
        String[] valorDonde = new String[]{"Chalco"};
        String grupoPor = "direccion";
        String teniendo = "idU > -10";
        String ordenadoPor = "idU ASC";
        String limite = "3";
        Cursor datosCursor = db.query(
                NombreTabla,    // NOMBRE DE LA TABLA
                null,       // SE ESPECIFICAN LAS COLUMNAS DE LA TABLA A OBTENER
                null,          // CLAUSULA WHERE
                null,     // VALOR DE LA CLAUSULA WHERE
                null,       // GROUP BY
                null,       // HAVING
                null,    // ORDER BY
                null);        // LIMITE
        // DEVOLVEMOS EL CURSOR YA CON LOS DATOS OBTENIDOS DE LA CONSULTA
        return datosCursor;
    }
}