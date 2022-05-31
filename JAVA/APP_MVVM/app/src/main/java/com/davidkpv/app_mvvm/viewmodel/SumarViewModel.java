package com.davidkpv.app_mvvm.viewmodel;

import androidx.lifecycle.ViewModel;

// LA CLASE VIEW MODEL PERMITE QUE NO SE ELIMINEN LOS DATOS POR CAMBIOS DE CONFIGURACIÓN
// DE UNA ACTIVITY
public class SumarViewModel extends ViewModel {

    private int resultado;

    public int getResultado(){
        return resultado;
    }

    public void setResultado(int resultado){
        this.resultado = resultado;
    }
}
