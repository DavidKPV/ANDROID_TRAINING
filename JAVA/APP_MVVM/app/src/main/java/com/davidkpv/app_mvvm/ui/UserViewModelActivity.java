package com.davidkpv.app_mvvm.ui;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.davidkpv.app_mvvm.R;
import com.davidkpv.app_mvvm.util.User;
import com.davidkpv.app_mvvm.viewmodel.UserViewModel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserViewModelActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etEdad;
    private Button btSalvar;
    private Button btVer;
    private TextView tvUser;
    private TextView tvUserViewModel;

    private List<User> userList;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_model);

        setUpView();
    }

    private void setUpView(){
        // INSTANCIAMOS LA LISTA PARA EVITAR UN NULL POINTER EXCEPTION
        userList = new ArrayList<>();

        // INSTANCIAMOS EL VIEW MODEL PARA UserViewModel
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // ENLACES
        etNombre = findViewById(R.id.etNombre);
        etEdad = findViewById(R.id.etEdad);
        tvUser = findViewById(R.id.tvUser);
        tvUserViewModel = findViewById(R.id.tvUserViewModel);
        btSalvar = findViewById(R.id.btSalvar);
        btVer = findViewById(R.id.btVer);

        // OYENTES DE LOS BOTONES
        btVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = "";
                for(int i=0; i<userList.size(); i++){
                    texto += userList.get(i).getNombre() + " " + userList.get(i).getEdad() + "\n";
                }

                tvUser.setText(texto);

                // USANDO VIEW / MODEL
                texto = "";
                for(User user: userViewModel.getUserList()){
                   texto += user.getNombre() + " " + user.getEdad() + "\n";
                }
                tvUserViewModel.setText(texto);
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setEdad(etEdad.getText().toString());
                user.setNombre(etNombre.getText().toString());

                userList.add(user);
                userViewModel.addUser(user);
            }
        });

    }
}