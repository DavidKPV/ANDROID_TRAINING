package com.davidkpv.dummyapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.davidkpv.libtemp.BazCardPayFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Sleep the App a little bit
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Log.i("TAG1", "this -> " +e);
            e.printStackTrace();
        }

        // Put Again the original theme
        setTheme(R.style.Theme_DummyApplication);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}