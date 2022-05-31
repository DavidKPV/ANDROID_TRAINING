package com.david.syncadapter_dmm;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class SQLiteDataHelper extends SQLiteOpenHelper {
    // DATOS DE LA BD de SQLite
    public static final String DB_NAME = "ContactosDB";
    public static final String TABLE_NAME = "usuario";
    public static final String COLUMN_ID = "id_usuario";
    public static final String COLUMN_NAME = "nombre";
    public static final String COLUMN_PHONE = "telefono";
    public static final String COLUMN_STATUS = "status";

    private static final int DB_VERSION = 1;
    // CONSTRUCTOR
    public SQLiteDataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME +
                " VARCHAR, " + COLUMN_PHONE+
                " VARCHAR, " + COLUMN_STATUS +
                " TINYINT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS usuario";

        db.execSQL(sql);
        onCreate(db);
    }

    public boolean addDatos(String nombre, String telefono, int status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, nombre);
        contentValues.put(COLUMN_PHONE, telefono);
        contentValues.put(COLUMN_STATUS, status);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public boolean updateDatosStatus(int id, int status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STATUS, status);

        db.update(TABLE_NAME, contentValues, COLUMN_ID+"="+id, null);
        db.close();

        return true;
    }

    public Cursor getDatos(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" ORDER BY "+COLUMN_ID+" ASC";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor getUnsyncedDatos(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_STATUS+" = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
}
