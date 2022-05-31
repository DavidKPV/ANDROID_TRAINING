package com.david.bcr_internet_dmm;

import android.app.Application;

public class MyApplication extends Application {
    // VARIABLE QUE CHECARÁ EL ESTADO DE LA ACTIVIDAD (EJECUTÁNDOSE O NO)
    public static boolean activityVisible;

    public static boolean isActivityVisible() {
        return activityVisible; // REGRESA VERDADERO O FALSO
    }

    public static void activityResumed() {
        activityVisible = true; // REGRESARÁ VERDADERO EN CASO DE QUE LA ACTIVIDAD SE ENCUENTRE VISIBLE
    }

    public static void activityPaused() {
        activityVisible = false; // REGRESARÁ FALSO SI LA ACTIVIDAD SE ENCUENTRA PAUSADA (NO VISIBLE)
    }
}
