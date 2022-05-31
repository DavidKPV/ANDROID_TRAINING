package mx.com.davidkpv.myapplication.di;

import android.app.Application;

public class MotorApplication extends Application {

    private MotorComponent motorComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // ENLAZAR CON DAGGER
        motorComponent = DaggerMotorComponent.builder().motorModule(new MotorModule()).build();
    }

    // MPETODO QUE DEVUELVE EL OBJETO CREADO
    public MotorComponent getMotorComponent(){
        return motorComponent;
    }
}
