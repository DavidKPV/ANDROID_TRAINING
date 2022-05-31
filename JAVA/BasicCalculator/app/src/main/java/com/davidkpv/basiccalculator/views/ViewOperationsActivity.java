package com.davidkpv.basiccalculator.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.davidkpv.basiccalculator.R;
import com.davidkpv.basiccalculator.models.interfaces.OperationView;
import com.davidkpv.basiccalculator.presenters.OperationPresenterI;

public class ViewOperationsActivity extends AppCompatActivity implements OperationView {
    // DECLARAMOS LOS OBJETOS DE LA VISTA
    EditText etNumber1, etNumber2;
    Button buttonAdd, buttonSubtract, buttonMultiply, buttonDivide;
    TextView tvResult;
    // EL OBJETO DEL PRESENTADOR ES INDISPENSABLE
    OperationPresenterI presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);

        // ENLACE DE LOS OBJETOS CON LA VISTA
        etNumber1 = (EditText) findViewById(R.id.etNumber1);
        etNumber2 = (EditText) findViewById(R.id.etNumber2);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        tvResult = (TextView) findViewById(R.id.tvResult);

        // INSTANCIA DEL PRESENTADOR
        presenter = new OperationPresenterI(this);

        // OYENTES DE LOS BOTONES
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int num1 = Integer.parseInt(etNumber1.getText().toString());
                    int num2 = Integer.parseInt(etNumber2.getText().toString());

                    presenter.add(num1, num2);
                } catch (NumberFormatException e){
                    errorOperation();
                }
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int num1 = Integer.parseInt(etNumber1.getText().toString());
                    int num2 = Integer.parseInt(etNumber2.getText().toString());

                    presenter.subtract(num1, num2);
                } catch (NumberFormatException e){
                    errorOperation();
                }
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int num1 = Integer.parseInt(etNumber1.getText().toString());
                    int num2 = Integer.parseInt(etNumber2.getText().toString());

                    presenter.multiply(num1, num2);
                } catch (NumberFormatException e){
                    errorOperation();
                }
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int num1 = Integer.parseInt(etNumber1.getText().toString());
                    int num2 = Integer.parseInt(etNumber2.getText().toString());

                    presenter.divide(num1, num2);
                } catch (NumberFormatException e){
                    errorOperation();
                }
            }
        });
    }

    @Override
    public void showResult(String result) {
        tvResult.setText("EL RESULTADO ES \n" + result);
    }

    @Override
    public void errorOperation() {
        Toast.makeText(this, "Sintaxis Error", Toast.LENGTH_LONG).show();
    }
}