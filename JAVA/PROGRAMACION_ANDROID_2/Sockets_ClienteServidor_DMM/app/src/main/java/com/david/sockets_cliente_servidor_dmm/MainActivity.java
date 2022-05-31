package com.david.sockets_cliente_servidor_dmm;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    // OBJETOS
    EditText etIp, etPuerto, etMensaje;
    Button btnEnviar;
    Socket cliente;
    PrintWriter salida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ENLACES CON LA VISTA
        etIp = (EditText) findViewById(R.id.etIp);
        etPuerto = (EditText) findViewById(R.id.etPuerto);
        etMensaje = (EditText) findViewById(R.id.etMensaje);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensaje();
            }
        });
    }

    private void enviarMensaje(){
        // OBTENEMOS LOS DATOS
        String direccionIp = etIp.getText().toString();
        String puerto = etPuerto.getText().toString();
        String mensaje = etMensaje.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cliente = new Socket(direccionIp, Integer.parseInt(puerto));
                    salida = new PrintWriter(cliente.getOutputStream());
                    salida.write(mensaje);
                    salida.flush();
                    salida.close();
                    cliente.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}