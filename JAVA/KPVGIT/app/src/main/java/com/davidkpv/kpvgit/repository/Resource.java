package com.davidkpv.kpvgit.repository;

// MANTENDRÁ EL VALOR DE CUANDO SE ESTÉN CARGANDO LOS DATOS
public class Resource<T> {
    public final Status status;
    public final String message;
    public final T data;

    public Resource(Status status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // MÉTODOS QUE PERMITIRÁN SABER LOS ESTADOS (LOADING, SUCCESS O ERROR)
    public static <T> Resource<T> success(T data){
        return new Resource<>(Status.SUCCESS, null, data);
    }

    public static <T> Resource<T> error(String msg, T data){
        return new Resource<>(Status.ERROR, msg, data);
    }

    public static <T> Resource<T> loading(T data){
        return new Resource<>(Status.LOADING, null, data);
    }

}
