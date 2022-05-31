package mx.com.davidkpv.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import javax.inject.Inject;
import javax.inject.Named;

import mx.com.davidkpv.myapplication.di.MotorApplication;

public class MainActivity extends AppCompatActivity {

    @Named("gasolina")
    @Inject
    Motor motor;

    @Inject
    Coche coche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MotorApplication)getApplication()).getMotorComponent().inject(this);

        Toast.makeText(this, motor.getTipoMotor(), Toast.LENGTH_SHORT).show();

        Toast.makeText(this, coche.getMotor(), Toast.LENGTH_SHORT).show();

    }
}