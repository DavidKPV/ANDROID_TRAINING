package com.davidkpv.basiccalculator.models.interactor;

import android.widget.Toast;

import com.davidkpv.basiccalculator.models.interfaces.OperationInteractor;
import com.davidkpv.basiccalculator.models.interfaces.OperationPresenter;
import com.davidkpv.basiccalculator.views.ViewOperationsActivity;

public class OperationInteractorI implements OperationInteractor {
    // DECLARAMOS LA VARIABLE EN DONDE ALMACENAREMOS LOS RESULTADOS
    private int resul;
    // INSTANCIAMOS LA CLASE DEL PRESENTADOR EN LA QUE SE MOSTRARÁ EL RESULTADO
    private OperationPresenter presenter;

    // CONSTRUCTOR
    public OperationInteractorI(OperationPresenter operationPresenter){
        this.presenter = operationPresenter;
    }

    // LLAMAMOS A LOS MÉTODOS QUE ESTÁN EN LA INTERFAZ
    @Override
    public void add(int num1, int num2) {
        resul = num1 + num2;
        presenter.showResult(Integer.toString(resul));
    }

    @Override
    public void subtract(int num1, int num2) {
        resul = num1 - num2;
        presenter.showResult(Integer.toString(resul));
    }

    @Override
    public void multiply(int num1, int num2) {
        resul = num1 * num2;
        presenter.showResult(Integer.toString(resul));
    }

    @Override
    public void divide(int num1, int num2) {
        if(num2 != 0){
            resul = num1 / num2;
            presenter.showResult(Integer.toString(resul));
        } else {
            presenter.errorOperation();
        }
    }
}
