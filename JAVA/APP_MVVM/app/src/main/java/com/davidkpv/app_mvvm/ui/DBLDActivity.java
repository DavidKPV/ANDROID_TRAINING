package com.davidkpv.app_mvvm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.davidkpv.app_mvvm.R;
import com.davidkpv.app_mvvm.databinding.ActivityDbldBinding;
import com.davidkpv.app_mvvm.util.User;
import com.davidkpv.app_mvvm.viewmodel.DBLDViewModel;

public class DBLDActivity extends AppCompatActivity {

    private DBLDViewModel dbldViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDbldBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dbld);

        binding.setLifecycleOwner(this);

        dbldViewModel = new ViewModelProvider(this).get(DBLDViewModel.class);

        binding.setViewModel(dbldViewModel);

        User user = new User("David", "22");
        dbldViewModel.setUser(user);

    }
}