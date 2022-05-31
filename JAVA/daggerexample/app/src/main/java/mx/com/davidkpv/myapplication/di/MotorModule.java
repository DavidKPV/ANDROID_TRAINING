package mx.com.davidkpv.myapplication.di;


import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import mx.com.davidkpv.myapplication.Coche;
import mx.com.davidkpv.myapplication.Motor;

@Module
public class MotorModule {

    // MÉTODOS PARA LA INYECCIÓN DE DEPENDENCIAS
    @Named("diesel")
    @Provides
    public Motor providesDiesel(){
        return new Motor("Diesel");
    }
    @Named("gasolina")
    @Provides
    public Motor providesGasolina(){
        return new Motor("Gasolina");
    }

    @Provides
    public Coche providesCoche(@Named("diesel") Motor motor){
        return new Coche(motor);
    }
}
