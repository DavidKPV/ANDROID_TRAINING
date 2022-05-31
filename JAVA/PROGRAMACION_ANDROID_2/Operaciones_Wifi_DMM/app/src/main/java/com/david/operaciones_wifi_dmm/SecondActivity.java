package com.david.operaciones_wifi_dmm;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ListView lvResult = (ListView) findViewById(R.id.lvResult);
        TextView tvResult = (TextView) findViewById(R.id.tvResult);
        Sqlite sql = new Sqlite(getApplicationContext(), "wifi", null, 1);
        SQLiteDatabase db = sql.getWritableDatabase();
        ArrayList<Red> reds = new ArrayList<Red>();
        String[] columns = {"idnetwork", "ssid", "bssid"};
        Cursor c = db.query("Red",
                columns,
                null,
                null,
                null,
                null, null);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            reds.add(new Red(Integer.valueOf(c.getString(0)),c.getString(1),
                    c.getString(2)));
        }
        int total = reds.size();
        tvResult.setText("Total de registros: "+total);
        ArrayAdapterRed adapterRed = new ArrayAdapterRed(this, R.layout.plantilla_wifi, reds);
        lvResult.setAdapter(adapterRed);
    }
}