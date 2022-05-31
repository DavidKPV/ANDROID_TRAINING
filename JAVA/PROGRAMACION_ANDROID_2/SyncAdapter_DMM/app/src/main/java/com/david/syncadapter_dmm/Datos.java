package com.david.syncadapter_dmm;

public class Datos {
    private String nombre;
    private String telefono;
    private int status;

    public Datos(String nombre, String telefono, int status) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.status = status;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getStatus() {
        return status;
    }
}
