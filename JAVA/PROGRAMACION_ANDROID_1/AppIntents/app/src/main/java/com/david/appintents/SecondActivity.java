package com.david.appintents;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    //  DECLARAR EL OBJETO
    TextView tvMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvMensaje = (TextView) findViewById(R.id.tvMensaje);

        cargarMensaje();
    }

    private void cargarMensaje(){
        Intent intent2 = getIntent();
        String msj =intent2.getStringExtra("Mensaje:");
        tvMensaje.setText(msj);
    }
}
