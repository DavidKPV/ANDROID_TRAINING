package com.david.mapsdavid;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
public class UbicacionActivity extends AppCompatActivity {
    TextView vRegistros;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);
        // ENLACES
        vRegistros = (TextView) findViewById(R.id.tvUbicacion);
        Sqllocalizacion lugar = new Sqllocalizacion(this, "ubicaciones", null, 1);
        SQLiteDatabase db = lugar.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM ubicaciones", null);
        if(cursor.moveToFirst()){
            do{ Integer id = cursor.getInt(0);
                String calle = cursor.getString(1);
                Double latitud = cursor.getDouble(2);
                Double longitud = cursor.getDouble(3);
                vRegistros.append(
                        "ID: "+id+"\n"+
                                "Direccion: "+calle+"\n"+
                                "Latitud: "+latitud+"\n"+
                                "Longitud: "+longitud+"\n\n\n"
                );
            }while (cursor.moveToNext());
            db.close();
        }
    }
}