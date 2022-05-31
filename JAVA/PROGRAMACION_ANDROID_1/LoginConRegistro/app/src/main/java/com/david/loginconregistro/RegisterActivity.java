package com.david.loginconregistro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    // SE DECLARAN LOS ELEMENTOS UTILIZADOS
    TextInputLayout tilNUser, tilNPass, tilNPass2;
    Button BtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // SE ENLAZAN LOS ELEMENTOS CON LA VISTA
        tilNUser = (TextInputLayout) findViewById(R.id.tilNUser);
        tilNPass = (TextInputLayout) findViewById(R.id.tilNPass);
        tilNPass2 = (TextInputLayout) findViewById(R.id.tilNPass2);
        BtnRegister = (Button) findViewById(R.id.BtnRegister);

        // ACTIVAR EL BOTÓN DE RETORNO (BACK / HOME)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // SE ACTIVA EL OYENTE DEL BOTÓN
        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    // METODO PARA DARLE FUNCIONALIDAD AL BOTÓN DE RETORNO
    @Override
    public boolean onSupportNavigateUp() {
        // SE EJECUTE Y REGRESE A LA PANTALLA ANTERIOR
        onBackPressed();
        return true;
    }

    // MÉTODO QUE REALIZA EL REGISTRO
    public void registrar(){
        // OBTENEMOS LAS VARIABLES PARA SOBREESCRIBIR LOS VALORES
        String u = tilNUser.getEditText().getText().toString();
        String p = tilNPass.getEditText().getText().toString();
        String p2 = tilNPass2.getEditText().getText().toString();

        // INSTANCIAMOS EL SHARED
        SharedPreferences sp1 = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        // SE ACTIVA LA EDICION DEL ARCHIVO SHARED
        SharedPreferences.Editor editor = sp1.edit();

        editor.putBoolean("usuario", false);
        editor.commit();
    }
}
