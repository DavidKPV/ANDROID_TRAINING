package com.david.examen_tema_2_dmm.controlador;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.david.examen_tema_2_dmm.modelo.DiccionarioDB;
import com.david.examen_tema_2_dmm.modelo.MySQLiteHelper;

public class Cita {
    private byte[] IMAGEN;
    private MySQLiteHelper conexion;
    private SQLiteDatabase sql1, sql2;
    private Context contexto;

    public  Cita(Context contexto){
        this.contexto = contexto;
        conexion = new MySQLiteHelper(contexto,"agenda_citas.db", null, 1);
    }

    public byte[] getImagen() {
        return IMAGEN;
    }

    public void insertarDatos(
            String nombre, String apellidop, String apellidom, String calle, String colonia, String municipio, String estado, String pais,
            String telefono, String correo, String sexo, int noInterior, int noExterior, int edad, int dia, int mes, int year, float peso,
            Float estatura, byte[] imagen
    ){
        sql1 = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(DiccionarioDB.CAMPO_NOMBRE, nombre);
        valores.put(DiccionarioDB.CAMPO_APELLIDOP, apellidop);
        valores.put(DiccionarioDB.CAMPO_APELLIDOM, apellidom);
        valores.put(DiccionarioDB.CAMPO_CALLE, calle);
        valores.put(DiccionarioDB.CAMPO_NoINTE, noInterior);
        valores.put(DiccionarioDB.CAMPO_NoEXTE, noExterior);
        valores.put(DiccionarioDB.CAMPO_COLONIA, colonia);
        valores.put(DiccionarioDB.CAMPO_MUNICIPIO, municipio);
        valores.put(DiccionarioDB.CAMPO_ESTADO, estado);
        valores.put(DiccionarioDB.CAMPO_PAIS, pais);
        valores.put(DiccionarioDB.CAMPO_TELEFONO, telefono);
        valores.put(DiccionarioDB.CAMPO_EMAIL, correo);
        valores.put(DiccionarioDB.CAMPO_SEXO, sexo);
        valores.put(DiccionarioDB.CAMPO_EDAD, edad);
        valores.put(DiccionarioDB.CAMPO_DIA, dia);
        valores.put(DiccionarioDB.CAMPO_MES, mes);
        valores.put(DiccionarioDB.CAMPO_YEAR, year);
        valores.put(DiccionarioDB.CAMPO_ESTATURA, estatura);
        valores.put(DiccionarioDB.CAMPO_PESO, peso);
        valores.put(DiccionarioDB.CAMPO_IMAGEN, imagen);

        long id = sql1.insert(DiccionarioDB.NOMBRE_TABLA, DiccionarioDB.CAMPO_NOMBRE,valores);

        Toast.makeText(contexto, "CITA CON EL INDENTIFICADOR: "+id+"\nREGISTRADA CON ÉXITO", Toast.LENGTH_LONG).show();
        sql1.close();
    }

    public String[] obtenerDatos(String clave){
        String[] resultados = new String[19];
        String[] vclave = new String[]{clave};
        try {
            sql2 = conexion.getReadableDatabase();
            Cursor obtener = sql2.query(
                    DiccionarioDB.NOMBRE_TABLA,
                    null,
                    DiccionarioDB.CAMPO_ID+"=?",
                    vclave,
                    null,
                    null,
                    null,
                    null
            );
            obtener.moveToFirst();
            resultados[0] = obtener.getString(1);
            resultados[1] = obtener.getString(2);
            resultados[2] = obtener.getString(3);
            resultados[3] = obtener.getString(4);
            resultados[4] = String.valueOf(obtener.getInt(5));
            resultados[5] = String.valueOf(obtener.getInt(6));
            resultados[6] = obtener.getString(7);
            resultados[7] = obtener.getString(8);
            resultados[8] = obtener.getString(9);
            resultados[9] = obtener.getString(10);
            resultados[10] = obtener.getString(11);
            resultados[11] = obtener.getString(12);
            resultados[12] = obtener.getString(13);
            resultados[13] = String.valueOf(obtener.getInt(14));
            resultados[14] = String.valueOf(obtener.getInt(15));
            resultados[15] = String.valueOf(obtener.getInt(16));
            resultados[16] = String.valueOf(obtener.getInt(17));
            resultados[17] = String.valueOf(obtener.getFloat(18));
            resultados[18] = String.valueOf(obtener.getFloat(19));
            IMAGEN = obtener.getBlob(20);
            sql2.close();
            return  resultados;
        }catch (Exception e){
            Toast.makeText(contexto, "NO SE ENCONTRÓ NINGUNA CITA\nCON ESTE IDENTIFICADOR", Toast.LENGTH_LONG).show();
            return resultados;
        }
    }
}
