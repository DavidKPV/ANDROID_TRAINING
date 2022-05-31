package com.david.beanssqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE estudiantes ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT,"+
                "apellidop TEXT,"+
                "apellidom TEXT,"+
                "edad INTEGER,"+
                "promedio REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS estudiantes;");
        onCreate(db);
    }
}
