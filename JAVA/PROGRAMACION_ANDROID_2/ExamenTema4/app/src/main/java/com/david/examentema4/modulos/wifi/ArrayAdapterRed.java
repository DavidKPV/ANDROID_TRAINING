package com.david.examentema4.modulos.wifi;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.david.examentema4.R;

import java.util.ArrayList;
public class ArrayAdapterRed extends ArrayAdapter<Red> {
    Context contexto;
    int layoutResource;
    ArrayList<Red> lista;
    public ArrayAdapterRed(Context contexto, int layoutResource, ArrayList<Red> lista) {
        super(contexto, layoutResource, lista);
        this.contexto = contexto;
        this.layoutResource = layoutResource;this.lista = lista;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        HolderRed holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) contexto).getLayoutInflater();
            row = inflater.inflate(layoutResource, parent, false);
            holder = new HolderRed();
            holder.ssid = (TextView) row.findViewById(R.id.tvSsid);holder.bssid = (TextView) row.findViewById(R.id.tvBssid);
            row.setTag(holder);
        } else { holder = (HolderRed) row.getTag(); }
        Red red = lista.get(position);
        holder.ssid.setText(red.getSSID());
        holder.bssid.setText(red.getBSSID()); return row;
    }
    static class HolderRed {
        TextView ssid;
        TextView bssid;
    }
}
