package com.david.examen_tema_v_dmm.services;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.david.examen_tema_v_dmm.AdapterTareas;
import com.david.examen_tema_v_dmm.ModelTareas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Buscar {
    // OBJETOS
    Context contexto;
    Activity activity;
    RequestQueue servicio;
    JSONObject obj = null;
    JSONArray jsonTereas = null;
    ProgressDialog progressDialog;
    ListView lvTareas;

    public Buscar(Context contexto, Activity activity, ListView lvTareas) {
        this.contexto = contexto;
        this.activity = activity;
        this.servicio = Volley.newRequestQueue(contexto);
        this.progressDialog = new ProgressDialog(activity);
        this.lvTareas = lvTareas;
    }

    public void buscar() {
        progressDialog.setMessage("CARGANDO...");
        progressDialog.show();
        StringRequest respuesta = new StringRequest(
                Request.Method.POST, Uris.URI_GENERAL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                // CREAR UN OBJETO DE TIPO JSON PARA OBTENER EL ARCHIVO QUE MANDARÁ EL WEB SERVICE
                try {
                    List<ModelTareas> tareas = new ArrayList<>();
                    obj = new JSONObject(response);
                    // OBTENEMOS LAS TAREAS
                    jsonTereas = obj.getJSONArray("respuesta");
                    for (int con = 0; con < jsonTereas.length(); con++) {
                        ModelTareas modelo = new ModelTareas(
                                jsonTereas.getJSONObject(con).getInt("id_tarea"),
                                jsonTereas.getJSONObject(con).getString("titulo"),
                                jsonTereas.getJSONObject(con).getString("asunto"),
                                jsonTereas.getJSONObject(con).getString("fecha"),
                                jsonTereas.getJSONObject(con).getString("descripcion"),
                                jsonTereas.getJSONObject(con).getString("nota")
                        );
                        tareas.add(modelo);
                    }

                    lvTareas.setAdapter(new AdapterTareas(contexto, activity, tareas));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(contexto, "NO TIENES TAREAS NAVIDEÑAS :D", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(contexto, "Error de comunicación :C", Toast.LENGTH_LONG).show();
            }
        }) {
            // VALORES QUE SE ENVIARÁN
            protected Map<String, String> getParams() {
                // RETORNAR LOS VALORES
                Map<String, String> parametros = new HashMap<>();
                parametros.put("opcion", "consultar");
                return parametros;
            }
        };
        servicio.add(respuesta);
    }
}
