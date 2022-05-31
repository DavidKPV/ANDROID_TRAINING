package com.davidkpv.app_mvvm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.davidkpv.app_mvvm.R;
import com.davidkpv.app_mvvm.util.User;
import com.davidkpv.app_mvvm.viewmodel.LiveDataViewModel;

import java.util.List;

public class LiveDataActivity extends AppCompatActivity {

    private TextView tvLiveData;
    private Button btSave;

    private int numero = 0;

    private LiveDataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);

        setUpView();
    }

    private void setUpView(){
        // INSTANCIAMOS EL VIEW MODEL
        viewModel = new ViewModelProvider(this).get(LiveDataViewModel.class);

        tvLiveData = findViewById(R.id.tvLiveData);
        btSave = findViewById(R.id.btSave);

        // OYENTE DEL BOTÓN
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numero == 0){
                    User user = new User();
                    user.setNombre("David");
                    user.setEdad("30");
                    Log.d("TAG1", "número0");
                    viewModel.addUser(user);
                }
                if(numero == 1){
                    User user = new User();
                    user.setNombre("Marcos");
                    user.setEdad("40");
                    viewModel.addUser(user);
                }
                if(numero == 2){
                    User user = new User();
                    user.setNombre("Rodrigo");
                    user.setEdad("50");
                    viewModel.addUser(user);
                }
                numero++;
            }
        });

        // SE INSTANCIA EL OBSERVADOR DEL LIVEDATA
        final Observer<List<User>> listObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                String texto = "";
                for(int i=0; i<users.size(); i++){
                    texto += users.get(i).getNombre() + " " + users.get(i).getEdad() + "\n";
                }
                tvLiveData.setText(texto);
            }
        };

        // SUSCRIBIMOS EL OBSERVER AL OBSERVABLE PARA QUE FUNCIONE REALMENTE NUESTRO OBSERVER
        viewModel.getUserList().observe(this, listObserver);
    }
}