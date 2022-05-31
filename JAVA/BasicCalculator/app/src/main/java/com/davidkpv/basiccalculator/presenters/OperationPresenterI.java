package com.davidkpv.basiccalculator.presenters;

import com.davidkpv.basiccalculator.models.interactor.OperationInteractorI;
import com.davidkpv.basiccalculator.models.interfaces.OperationInteractor;
import com.davidkpv.basiccalculator.models.interfaces.OperationPresenter;
import com.davidkpv.basiccalculator.models.interfaces.OperationView;

public class OperationPresenterI implements OperationPresenter {
    // DECLARAMOS LA VISTA (interfaz) Y EL INTERACTOR CON EL QUE TRABAJAREMOS (interfaz)
    private OperationInteractor interactor;
    private OperationView view;

    // EN EL CONSTRUCTOR RECIBIREMOS LA VISTA CON LA QUE SE TRABAJARÁ Y ASIGNAREMOS LA CLASE DEL INTERACTOR
    public OperationPresenterI(OperationView operationView){
        this.view = operationView;
        this.interactor = new OperationInteractorI(this);
    }

    @Override
    public void showResult(String result) {
        if(view != null){
            view.showResult(result);
        }
    }

    @Override
    public void errorOperation() {
        if(view != null){
            view.errorOperation();
        }
    }

    @Override
    public void add(int num1, int num2) {
        if(view != null){
            interactor.add(num1, num2);
        }
    }

    @Override
    public void subtract(int num1, int num2) {
        if(view != null){
            interactor.subtract(num1, num2);
        }
    }

    @Override
    public void multiply(int num1, int num2) {
        if(view != null){
            interactor.multiply(num1, num2);
        }
    }

    @Override
    public void divide(int num1, int num2) {
        if(view != null){
            interactor.divide(num1, num2);
        }
    }
}
