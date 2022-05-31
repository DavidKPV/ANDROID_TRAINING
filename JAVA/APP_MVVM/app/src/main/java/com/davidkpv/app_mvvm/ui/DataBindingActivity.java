package com.davidkpv.app_mvvm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.davidkpv.app_mvvm.R;
import com.davidkpv.app_mvvm.databinding.ActivityDataBindingBinding;
import com.davidkpv.app_mvvm.util.User;

public class DataBindingActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_data_binding);
        // EL DE ARRIBA YA NO SE UTILIZA PARA REALIZAR EL ENLACE CON LA VISTA
        // SE HACE EL ENLACE DE LA ACTIVITY CON EL XML
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        user = new User("David", "22");

        // MANDAMOS LOS DATOS A LA VISTA
        binding.setUser(user);
    }
}