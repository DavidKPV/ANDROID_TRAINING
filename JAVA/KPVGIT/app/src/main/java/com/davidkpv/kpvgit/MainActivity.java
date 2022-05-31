package com.davidkpv.kpvgit;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.davidkpv.kpvgit.ui.common.NavigationController;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    NavigationController navigationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SI SE INICIA LA ACTIVITY POR PRIMERA VEZ INICIAMOS LA BÚSQUEDA
        if(savedInstanceState == null){
            navigationController.navigateToSearch();
        }
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector(){
        return dispatchingAndroidInjector;
    }
}