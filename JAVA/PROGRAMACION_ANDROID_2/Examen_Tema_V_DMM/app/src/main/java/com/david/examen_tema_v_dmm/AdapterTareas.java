package com.david.examen_tema_v_dmm;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
public class AdapterTareas extends BaseAdapter {
    private static LayoutInflater layoutInflater = null;
    private Context context;
    private Activity activity;
    private List<ModelTareas> tareas;
    // OBJETOS DE LA VISTA
    TextView tvTitulo, tvAsunto;
    LinearLayout llTarea;
    public AdapterTareas(Context context, Activity activity, List<ModelTareas> tareas){
        this.context = context;
        this.activity = activity;
        this.tareas = tareas;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return tareas.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = layoutInflater.inflate(R.layout.item_tareas,null);
        conectar(view);
        tvTitulo.setText(tareas.get(position).getTitulo());
        tvAsunto.setText(tareas.get(position).getAsunto());
        // OYENTE DE CADA TARJETA
        llTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // PASAMOS TODOS LOS PARÁMTEROS
                cambio(position);
            }
        });
        return view;
    }
    private void conectar(View v){
        tvTitulo = (TextView) v.findViewById(R.id.tvTitulo);
        tvAsunto = (TextView) v.findViewById(R.id.tvAsunto);
        llTarea = (LinearLayout) v.findViewById(R.id.llTarea);
    }
    private void cambio(int position){
        Intent actualizarOEliminar = new Intent(activity, FieldsActivity.class);
        actualizarOEliminar.putExtra("op", 2);
        actualizarOEliminar.putExtra("id_tarea", tareas.get(position).getId_tarea());
        actualizarOEliminar.putExtra("titulo", tareas.get(position).getTitulo());
        actualizarOEliminar.putExtra("asunto", tareas.get(position).getAsunto());
        actualizarOEliminar.putExtra("fecha", tareas.get(position).getFecha());
        actualizarOEliminar.putExtra("descripcion", tareas.get(position).getDescripcion());
        actualizarOEliminar.putExtra("nota", tareas.get(position).getNota());
        context.startActivity(actualizarOEliminar);
    }
}