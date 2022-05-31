package com.david.practica_2_dmm_bd.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// CLASE QUE NOS AYUDA A CONECTARNOS A LA BD Y/O CREARLA EN CASO DE QUE NO EXISTA
public class SQLiteHelper extends SQLiteOpenHelper {

    // CONSTRUCTOR
    public SQLiteHelper(Context context, String nameDB, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nameDB, factory, version);
    }

    // ENCARGADO DE CREAR LAS TABLAS Y EN CASO DE QUE YA EXISTAN NO LAS CREARÁ
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario (" +
                "idU INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "direccion TEXT," +
                "telefono TEXT);");
    }

    // ESTE MÉTODO ACTUALIZARÁ EN CASO DE QUE LA BASE DE DATOS HAYA CAMBIADO DE VERSIÓN
    // ELIMINARÁ LAS TABLAS Y LAS VOLVERÁ A CREAR CON LA MISMA ESTRUCTURA Y DATOS
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario;");
        onCreate(db);
    }
}
