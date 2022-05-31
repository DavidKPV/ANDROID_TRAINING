package com.david.mapsdavid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Sqllocalizacion extends SQLiteOpenHelper {
    // CONSTRUCTOR
    public Sqllocalizacion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory,
                           int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ubicaciones (id INTEGER PRIMARY KEY AUTOINCREMENT, calle TEXT, latitud REAL, longitud REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ubicaciones");
        onCreate(db);
    }
}
