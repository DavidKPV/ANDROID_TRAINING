package com.david.practica_tema_v_dmm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
public class MainActivity extends AppCompatActivity {
    // OBJETOS
    EditText etUsuario, etPassword;
    Button btnVerificar;
    TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ENLACES CON LA VISTA
        enlaces();

        // OYENTE DEL BOTÓN
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaCampos();
            }
        });
    }
    
    private void enlaces(){
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnVerificar = (Button) findViewById(R.id.btnVerificar);
        tvResponse = (TextView) findViewById(R.id.tvResponse);
    }

    private void validaCampos(){
        final String user = etUsuario.getText().toString().trim();
        final String pass = etPassword.getText().toString().trim();

        String url = "http://192.168.43.200:8080/SERVICE_BASIC/service.php?u="+user+"&p="+pass;

        RequestQueue servicio = Volley.newRequestQueue(this);

        StringRequest respuesta = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                tvResponse.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error de comunicación :C", Toast.LENGTH_LONG).show();
            }
        });
        servicio.add(respuesta);
    }
}