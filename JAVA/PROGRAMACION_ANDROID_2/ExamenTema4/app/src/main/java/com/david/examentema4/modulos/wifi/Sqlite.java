package com.david.examentema4.modulos.wifi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Sqlite extends SQLiteOpenHelper {
    String TABLE = "CREATE TABLE IF NOT EXISTS Red(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idnetwork TEXT," +
            "ssid TEXT," +
            "bssid TEXT)";
    // CONSTRUCTOR
    public Sqlite(@Nullable Context context, @Nullable String name,
                  @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // SE CREA LA TABLA
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE);
    }

    // POR SI SE ACTUALIZA LA VERSIÓN
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Red");
        onCreate(db);
    }
}
