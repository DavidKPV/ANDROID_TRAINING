package com.davidkpv.basiccalculator.models.interfaces;

public interface OperationView {
    // "CONTRATOS" DE MOSTRAR RESULTADO Y DE OPERACIÓN INVÁLIDA
    void showResult (String result);
    void errorOperation ();
}
