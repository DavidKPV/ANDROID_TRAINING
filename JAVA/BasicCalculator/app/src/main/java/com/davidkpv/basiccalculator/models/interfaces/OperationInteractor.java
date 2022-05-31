package com.davidkpv.basiccalculator.models.interfaces;

public interface OperationInteractor {
    // "CONTRATOS" DE OPERACIONES DE LA CALCULADORA
    void add(int num1, int num2);
    void subtract(int num1, int num2);
    void multiply(int num1, int num2);
    void divide(int num1, int num2);
}
