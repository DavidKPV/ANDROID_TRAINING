package com.davidkpv.basiccalculator.models.interfaces;

public interface OperationPresenter {
    // "CONTRATOS" DEL PRESENTADOR QUE ES EL INTERMEDIARIO ENTRE LA VISTA Y EL INTERACTOR
    // POR ELLO CONLLEVA TODOS LOS MÉTODOS DE LAS INTERFACES DE LA VISTA Y EL INTERACTOR
    void showResult (String result);
    void errorOperation ();
    void add(int num1, int num2);
    void subtract(int num1, int num2);
    void multiply(int num1, int num2);
    void divide(int num1, int num2);
}
