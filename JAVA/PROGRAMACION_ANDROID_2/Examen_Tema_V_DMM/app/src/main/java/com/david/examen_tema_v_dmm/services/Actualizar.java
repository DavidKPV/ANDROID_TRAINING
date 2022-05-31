package com.david.examen_tema_v_dmm.services;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
public class Actualizar {
    // OBJETOS
    Context contexto;
    RequestQueue servicio;
    JSONObject obj = null;
    ProgressDialog progressDialog;
    String titulo, asunto, fecha, descripcion, nota;
    int id_tarea;
    public Actualizar(int id_tarea, String titulo, String asunto, String fecha, String descripcion,
                      String nota, Context contexto, Activity activity) {
        this.id_tarea = id_tarea; this.titulo = titulo;
        this.asunto = asunto; this.fecha = fecha;
        this.descripcion = descripcion; this.nota = nota;
        this.contexto = contexto; this.servicio = Volley.newRequestQueue(contexto);
        this.progressDialog = new ProgressDialog(activity);
    }
    public void actualizar() {
        progressDialog.setMessage("CARGANDO...");
        progressDialog.show();
        StringRequest respuesta = new StringRequest(
                Request.Method.POST, Uris.URI_GENERAL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss(); // CREAR UN OBJETO DE TIPO JSON PARA OBTENER EL ARCHIVO QUE MANDARÁ EL WEB SERVICE
                try {
                    obj = new JSONObject(response);
                    // INTERPRETAR EL VALOR DEL JSON OBTENIDO DEL WEB SERVICE
                    String mensaje = obj.getString("mensaje");
                    // INTERPRETAR LOS VALORES
                    Toast.makeText(contexto, mensaje, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(contexto, "Error de comunicación :C", Toast.LENGTH_LONG).show();
            }
        }) {// VALORES QUE SE ENVIARÁN
            protected Map<String, String> getParams() {
                // RETORNAR LOS VALORES
                Map<String, String> parametros = new HashMap<>();
                parametros.put("id", String.valueOf(id_tarea));
                parametros.put("titulo", titulo);
                parametros.put("asunto", asunto);
                parametros.put("fecha", fecha);
                parametros.put("descripcion", descripcion);
                parametros.put("nota", nota);
                parametros.put("opcion", "actualizar");
                return parametros;
            }
        };servicio.add(respuesta);
    }
}