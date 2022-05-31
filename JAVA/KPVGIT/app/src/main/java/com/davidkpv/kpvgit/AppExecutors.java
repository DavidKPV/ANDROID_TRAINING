package com.davidkpv.kpvgit;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

// MODIFICACIÓN DE HILOS EN BACKGROUND PARA AGRUPACIÓN DE TAREAS
// QUE SOLO VAMOS A TENER UNA SOLA INSTANCIA DE ESTA CLASE EN TODO EL CÓDIGO DE LA APP
@Singleton
public class AppExecutors {

    // PARA CUANDO QUERAMOS ACCEDER A LA BASE DE DATOS LOCAL EN UN HILO EN SEGUNDO PLANO
    private final Executor diskIO;
    // PARA CUANDO SE REALICEN PETICIONES WEB EN SEGUNDO PLANO
    private final Executor networkId;
    // PARA EJECUTAR EN EL HILO PRINCIPAL
    private final Executor mainThread;

    public AppExecutors(Executor diskIO, Executor networkId, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkId = networkId;
        this.mainThread = mainThread;
    }

    // PARA PODER INYECTAR LAS DEPENDENCIAS
    @Inject
    public AppExecutors(){
        // PARA ENVIAR MULTIPLES TAREAS Y QUE SE EJECUTEN UNA TRAS OTRA
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3),
                new MainThreadExecutor());
    }

    public Executor diskIO(){
        return diskIO;
    }

    public Executor networkId(){
        return networkId;
    }

    public Executor mainThread(){
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor{
        private Handler mainThreadHandler = new Handler(Looper.myLooper());

        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);
        }
    }

}
