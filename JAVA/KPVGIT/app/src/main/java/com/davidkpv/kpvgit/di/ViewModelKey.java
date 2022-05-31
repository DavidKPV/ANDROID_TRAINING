package com.davidkpv.kpvgit.di;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

// NOS VA AYUDAR ASIGNARV LAS CLASES DE LOS VIEW MODEL PARA PODER INYECTAR CORRECTAMENTE
// LA SIGUIENTE ETIQUETA NOS AYUDARÁ PARA LA GENERACIÓN DE LA DOCUMENTACIÓN DE LA APLICACIÓN
@Documented
// LA SIGUIENTE ETIQUETA (TARGET) NOS INDICA LOS LUGARES EN DONDE SE PUEDE APLICAR ESTA ANOTACIÓN
@Target({ElementType.METHOD})
// LA SIGUIENTE ETIQUETA INDICA QUE SE CHEQUE EN TIEMPO DE EJECUCIÓN
@Retention(RetentionPolicy.RUNTIME)
// EL QUE NOS OFRECE LA CLAVE ESPECÍFICA DENTRO DE LA ANOTACIÓN
@MapKey
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}
