package com.david.calculadoratresopciones;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // PASO 1 Declarar los edittext
    EditText etnumero1, etnumero2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //PASO 2 Encontrar los controladores de la vista con las variables locales
        etnumero1 = findViewById(R.id.etnumero1);
        etnumero2 = findViewById(R.id.etnumero2);
    }

    public void suma (View v) {
        if (etnumero1.getText().toString().isEmpty() || etnumero2.getText().toString().isEmpty()) {
            Toast.makeText(this, "NO DEJES NINGÚN CAMPO VACÍO", Toast.LENGTH_LONG).show();
        } else {

            if(siNumero(etnumero1.getText().toString()) && siNumero(etnumero2.getText().toString())){
                Double num1 = Double.parseDouble(etnumero1.getText().toString());
                Double num2 = Double.parseDouble(etnumero2.getText().toString());
                Double suma = num1 + num2;
                Toast.makeText(this, "EL RESULTADO DE LA SUMA ES: " + suma, Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "POR FAVOR, INGRESA SOLO NÚMEROS", Toast.LENGTH_LONG).show();
            }

        }
    }

    public void multiplica (View v) {
        if (etnumero1.getText().toString().isEmpty() || etnumero2.getText().toString().isEmpty()) {
            Toast.makeText(this, "NO DEJES NINGÚN CAMPO VACÍO", Toast.LENGTH_LONG).show();
        } else {

            if(siNumero(etnumero1.getText().toString()) && siNumero(etnumero2.getText().toString())){
                Double num1 = Double.parseDouble(etnumero1.getText().toString());
                Double num2 = Double.parseDouble(etnumero2.getText().toString());
                Double suma = num1 * num2;
                Toast.makeText(this, "EL RESULTADO DE LA MULTIPLICACIÓN ES: " + suma, Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "POR FAVOR, INGRESA SOLO NÚMEROS", Toast.LENGTH_LONG).show();
            }

        }
    }

    public void divide (View v) {
        if (etnumero1.getText().toString().isEmpty() || etnumero2.getText().toString().isEmpty()) {
            Toast.makeText(this, "NO DEJES NINGÚN CAMPO VACÍO", Toast.LENGTH_LONG).show();
        } else {

            if(siNumero(etnumero1.getText().toString()) && siNumero(etnumero2.getText().toString())){
                Double num1 = Double.parseDouble(etnumero1.getText().toString());
                Double num2 = Double.parseDouble(etnumero2.getText().toString());
                if(num2==0){
                    Toast.makeText(this, "EL SEGUNDO NÚMERO NO DEBE SER CERO", Toast.LENGTH_LONG).show();
                }
                else{
                    Double suma = num1 / num2;
                    Toast.makeText(this, "EL RESULTADO DE LA DIVISIÓN ES: " + suma, Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(this, "POR FAVOR, INGRESA SOLO NÚMEROS", Toast.LENGTH_LONG).show();
            }

        }
    }

    private static boolean siNumero(String numero){
        try {
            Double.parseDouble(numero);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
