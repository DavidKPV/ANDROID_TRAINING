package mx.com.davidkpv.myapplication.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

// PARA QUE LA INYECCIÓN DE DEPENDENCIAS SOLO DURE DE ACUERDO EN CICLO DE UN ACTIVITY
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {

}
