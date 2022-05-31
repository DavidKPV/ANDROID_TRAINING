package com.david.examen_tema_v_dmm;

public class ModelTareas {
    private int id_tarea;
    private String titulo;
    private String asunto;
    private String fecha;
    private String descripcion;
    private String nota;
    public ModelTareas(int id_tarea, String titulo, String asunto, String fecha, String descripcion, String nota) {
        this.id_tarea = id_tarea;
        this.titulo = titulo;
        this.asunto = asunto;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.nota = nota;
    }
    public int getId_tarea() {
        return id_tarea;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNota() {
        return nota;
    }
}
