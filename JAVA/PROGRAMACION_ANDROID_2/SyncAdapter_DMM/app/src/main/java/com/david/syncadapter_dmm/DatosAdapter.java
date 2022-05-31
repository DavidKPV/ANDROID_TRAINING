package com.david.syncadapter_dmm;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
public class DatosAdapter extends ArrayAdapter<Datos> {
    // ALMACENAR TODOS LOS NOMBRES Y TELÉFONOS
    private List<Datos> datos; private Context contexto;
    // CONSTRUCTOR
    public DatosAdapter(Context contexto, int recurso, List<Datos> datos){
        super(contexto, recurso, datos); this.contexto = contexto; this.datos = datos;
    }
    @NonNull @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // PARA OBTENER LOS ITEMS
        View listViewItem = inflater.inflate(R.layout.datos, null, true);
        TextView tvNombre = (TextView) listViewItem.findViewById(R.id.tvNombre);
        TextView tvTelefono = (TextView) listViewItem.findViewById(R.id.tvTelefono);
        ImageView ivStatus = (ImageView) listViewItem.findViewById(R.id.ivStatus);
        // OBTENIENDO LOS DATOS ACTUALES
        Datos dato = datos.get(position);
        // COLOCAMOS LOS DATOS EN LOS TEXT VIEW
        tvNombre.setText(dato.getNombre());
        tvTelefono.setText(dato.getTelefono());
        // ASIGNAR EL ÍCONO DEL REGISTRO DE ACUERDO A SI SE ENCUENTRA SINCERONIZADO O NO
        if(dato.getStatus() == 0)
            ivStatus.setBackgroundResource(R.drawable.stopwatch);
        else
            ivStatus.setBackgroundResource(R.drawable.success);
        return listViewItem;
    }
}
