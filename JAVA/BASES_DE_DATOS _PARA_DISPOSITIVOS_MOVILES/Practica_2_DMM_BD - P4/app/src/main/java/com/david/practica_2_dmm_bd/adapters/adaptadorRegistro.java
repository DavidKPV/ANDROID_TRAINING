package com.david.practica_2_dmm_bd.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.david.practica_2_dmm_bd.R;


public class adaptadorRegistro extends BaseAdapter {
    // CREAMOS LOS ATRIBUTOS QUE NECESITAMOS
    private Context context;
    private int layout, filas;
    private int ids[];
    private String nombres[];
    private String direcciones[];
    private String telefonos[];
    private String sexos[];
    private Cursor cursor;

    // CONSTRUCTOR QUE LE ASIGNAREMOS LOS VALORES
    public adaptadorRegistro(Context context, int layout, Cursor cursor){
        this.context = context;
        this.layout = layout;
        this.cursor = cursor;
    }

    private int dividirDatos(){
        // OBTENEMOS EL TAMAÑO DE LOS DATOS
        filas = cursor.getCount();
        ids = new int[filas];
        nombres = new String[filas];
        direcciones = new String[filas];
        telefonos = new String[filas];
        sexos = new String[filas];
        // NOS POSICIONAMOS EN LA PRIMER POSICIÓN DEL CURSOR
        cursor.moveToFirst();
        for(int contador = 0; contador<filas; contador++){
            ids[contador] = cursor.getInt(0);
            nombres[contador] = cursor.getString(1);
            direcciones[contador] = cursor.getString(2);
            telefonos[contador] = cursor.getString(3);
            sexos[contador] = cursor.getString(4);
            // MOVEMOS AL SIGUIENTE REGISTRO
            cursor.moveToNext();
        }

        return filas;
    }
    // ESTE MÉTODO LE DIRÁ AL ADAPTADOR CUANTAS  VECES ITERARÁ LAS IMPRESIONES
    @Override
    public int getCount() {
        // OBTENEMOS LAS VECES QUE SE ITERARÁ OBTENIENDO LA CANTIDAD DE VALORES OBTENIDOS EN EL CURSOR
        // ACTIVAMOS EL MÉTODO QUE OBTENDRÁ TODOS LOS DATOS
        return dividirDatos();
    }

    // OBTENER UN ITEM DE LA COLECCIÓN
    @Override
    public Object getItem(int position) {
        return position;
    }

    // OBTENER EL ID DEL ELEMENTO EN LA COLECCION
    @Override
    public long getItemId(int id) {
        return id;
    }

    // DIBUJA LAS ITERACIONES
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        // DECLARAMOS UN OBJETO DE VALOR VIEW HOLDER
        viewHolder viewholder;

        // CONDICIONAL PARA VER SI AÚN NO HA ENCONTRADO LA REFERENCIA
        if(convertView == null){
            // CREAMOS UN LAYOUT INFLATER PARA INFLAR LA VISTA
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            // LLAMAMOS A LA VISTA INFLADA Y LE PASAMOS LA PLANTILLA
            convertView = layoutInflater.inflate(layout, null);

            // INSTANCIAMOS EL OBJETO VIEW HOLDER
            viewholder = new viewHolder();
            // ENLAZAMOS CON LA VISTA
            viewholder.tvIdU = (TextView) convertView.findViewById(R.id.tvIdU);
            viewholder.tvNombre = (TextView) convertView.findViewById(R.id.tvNombre);
            viewholder.tvDireccion = (TextView) convertView.findViewById(R.id.tvDireccion);
            viewholder.tvTelefono = (TextView) convertView.findViewById(R.id.tvTelefono);
            viewholder.tvSexo = (TextView) convertView.findViewById(R.id.tvSexo);

            // COLOCAMOS EL HOLDER COMO TAG
            convertView.setTag(viewholder);
        }
        else {
            viewholder = (viewHolder) convertView.getTag();
        }

        // RELLENAR LA VISTA CON NUESTROS DATOS
        viewholder.tvIdU.setText(""+ids[position]);
        viewholder.tvNombre.setText(nombres[position]);
        viewholder.tvDireccion.setText(direcciones[position]);
        viewholder.tvTelefono.setText(telefonos[position]);
        viewholder.tvSexo.setText(sexos[position]);

        return convertView;
    }

    static class viewHolder {
        private TextView tvIdU;
        private TextView tvNombre;
        private TextView tvDireccion;
        private TextView tvTelefono;
        private TextView tvSexo;
    }
}
