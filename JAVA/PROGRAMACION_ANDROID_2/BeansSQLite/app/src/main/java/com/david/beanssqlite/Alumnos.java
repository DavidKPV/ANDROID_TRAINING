package com.david.beanssqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class Alumnos {
    // INSTANCIAMOS LA BASE DE DATOS PARA TRABAJAR CON ELLA (INVOCARLA O CREARLA)
    Context contexto;
    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sql1, sql2, sql3, sql4;

    public Alumnos(Context context) {
        this.contexto = context;
    }

    // MÉTODOS
    public void registro(String nombre, String apellidoP, String apellidoM , int edad, float promedio){
        if(!nombre.isEmpty() && !apellidoP.isEmpty() && !apellidoM.isEmpty() && edad>0 && promedio>0) {
            // HACER UN CONTENEDOR DE VALORES PARA INSERTAR EL RESGISTRO
            ContentValues contendor = new ContentValues();
            contendor.put("nombre", nombre);
            contendor.put("apellidop", apellidoP);
            contendor.put("apellidom", apellidoM);
            contendor.put("edad", edad);
            contendor.put("promedio", promedio);

            sqLiteHelper = new SQLiteHelper(contexto, "escuela", null, 1);
            sql1 = sqLiteHelper.getWritableDatabase();
            sql1.insert("estudiantes", null, contendor);
            contendor.clear();

            // MENSAJE DE QUE TODO FUE UN ÉXITO
            Toast.makeText(contexto, "REGISTRO EXITOSO", Toast.LENGTH_LONG).show();
            sql1.close();
        }
        else
            Toast.makeText(contexto, "DEBES DE INGRESAR TODOS LOS CAMPO, EL ID NO SE TOMARÁ EN CUENTA", Toast.LENGTH_LONG).show();
    }

    public void actualizacion(int id, String nombre){
        // HACER UN CONTENEDOR DE VALORES PARA INSERTAR EL RESGISTRO
        ContentValues contendor = new ContentValues();
        contendor.put("nombre", nombre);

        String[] valor = new String[]{""+id};
        sqLiteHelper = new SQLiteHelper(contexto,"escuela",null,1);
        sql2 = sqLiteHelper.getWritableDatabase();
        sql2.update("estudiantes",contendor,"id=?",valor);

        // MENSAJE DE QUE TODO FUE UN ÉXITO
        Toast.makeText(contexto, "ACTUALIZACIÓN EXITOSA", Toast.LENGTH_LONG).show();
        sql2.close();
    }

    public void eliminar(int id){
        String[] valor = new String[]{""+id};
        sqLiteHelper = new SQLiteHelper(contexto,"escuela",null,1);
        sql3 = sqLiteHelper.getWritableDatabase();
        sql3.delete("estudiantes","id=?",valor);

        // MENSAJE DE QUE TODO FUE UN ÉXITO
        Toast.makeText(contexto, "ELIMINACIÓN EXITOSA", Toast.LENGTH_LONG).show();
        sql3.close();
    }

    public String[] buscar(int id){
        String[] valor = new String[]{""+id};
        String[] resultado = new String[]{""};

        try{
            sqLiteHelper = new SQLiteHelper(contexto,"escuela",null,1);
            sql4 = sqLiteHelper.getReadableDatabase();
            Cursor dato = sql4.query("estudiantes",null,
                    "id=?",
                    valor,
                    null,
                    null,
                    null,
                    null);

            dato.moveToFirst();
            for(int con=0; con<1; con++){
                resultado[con] = ""+dato.getInt(0);
                resultado[con] += "\n"+dato.getString(1);
                resultado[con] += "\n"+dato.getString(2);
                resultado[con] += "\n"+dato.getString(3);
                resultado[con] += "\n"+dato.getInt(4);
                resultado[con] += "\n"+dato.getFloat(5);
            }
            return resultado;
        }catch (Exception e){
            return resultado;
        }
    }
}
