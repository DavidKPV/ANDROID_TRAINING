package com.david.crud_con_sqlite_dmm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteHelper extends SQLiteOpenHelper {
    // SE CREA EL CONSTRUCTOR DE LLAMANDO A LOS PARÁMETROS DE SQLIteOpenHelper
    public MySQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // MÉTODO PARA INICIAR UNA ACTIVIDAD
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SE MANDA A LLAMAR A LA VARIABLE QUE QUE TRAE LA CREACIÓN DE LA BASE DE DATOS
        db.execSQL(utilidades.CREAR_TABLA_USUARIO);
    }

    // MÉTODO QUE AYUDARÁ A ELIMINAR LA BASE DE DATOS SI SE ACTUALIZA LA VERSIÓN DE ESTA
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(db);
    }
}
