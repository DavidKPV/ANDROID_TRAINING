package com.davidkpv.kpvgit.utils;

import android.os.SystemClock;
import android.util.ArrayMap;

import java.util.concurrent.TimeUnit;

// ESTA CLASE PERMITE DECIDIR GRACIAS A UN INTERVALO DE TIEMPO SI ES NECESARIO PEDIR LOS  DATOS
// ACTUALIZADOS DESDE EL WEB SERVICE O SIMPLEMENTE MOSTRAR LOS DATOS DESDE LA BD LOCAL
public class RateLimiter<KEY> {

    private ArrayMap<KEY, Long> timestamps = new ArrayMap<>();
    private final long timeout;

    public RateLimiter(int timeout, TimeUnit timeUnit){
        this.timeout = timeUnit.toMillis(timeout);
    }

    // MÉTODO DONDE OBTENEMOS EL TIEMPO QUE HA TRANSCURRIDO DESDE QUE REALIZAMOS LA ÚLTIMA PETICIÓN
    // AL SERVIDOR
    public synchronized boolean shouldFetch(KEY key){
        Long lastFetched = timestamps.get(key);
        long now = now();

        // VERIFICAMOS QUE LA ÚLTIMA VEZ DE SINCRONOZACIÓN NO SEA NULO
        if(lastFetched == null){
            timestamps.put(key, now);
            // REALIZAMOS LA PETICIÓN AL WEB SERVICE
            return true;
        }

        // VALIDAMOS TIEMPO TRANSSCURRIDO
        if(now - lastFetched > timeout){
            timestamps.put(key, now);
            // REALIZAMOS LA PETICIÓN AL WEB SERVICE
            return true;
        }

        // NO REALIZAMOS LA PETICIÓN AL WEB SERVICE Y EN SU LUGAR UTILIZAMOS LOS DATOS DE LA BD LOCAL
        return false;
    }

    // MÉTODO DONDE OBTENEMOS EL TIEMPO ACTUAL
    private long now(){
        return SystemClock.uptimeMillis();
    }

    // MÉTODO RESET PARA QUE SOLO SE ACCEDA UNA VEZ
    public synchronized void reset(KEY key){
        timestamps.remove(key);
    }
}