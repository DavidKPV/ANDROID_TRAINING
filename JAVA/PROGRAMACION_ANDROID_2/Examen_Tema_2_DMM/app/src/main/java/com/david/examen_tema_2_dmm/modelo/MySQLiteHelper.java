package com.david.examen_tema_2_dmm.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class MySQLiteHelper extends SQLiteOpenHelper {
    // CONSTRUCTOR
    public MySQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREAMOS LA TABLA DE LA BASE DE DATOS
        db.execSQL(DiccionarioDB.CREAR_TABLA_CITAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // SI SE ACTUALIZA LA VERSIÓN DE LA BASE DE DATOS SE VOLVREÁ A CREAR LA BASE DE DATOS
        db.execSQL("DROP TABLE IF EXISTS "+ DiccionarioDB.NOMBRE_TABLA);
        onCreate(db);
    }
}
