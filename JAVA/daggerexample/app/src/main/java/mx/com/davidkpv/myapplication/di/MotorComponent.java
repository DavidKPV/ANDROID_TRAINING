package mx.com.davidkpv.myapplication.di;

import dagger.Component;
import mx.com.davidkpv.myapplication.MainActivity;

@PerActivity
@Component(modules = MotorModule.class)
public interface MotorComponent {

    // PUENTE ENTRE LOS MÓDULOS Y EL CÓDIGO EN DONDE SE PPRESENTARÁ LA INYECCIÓN
    void inject(MainActivity mainActivity);

}
