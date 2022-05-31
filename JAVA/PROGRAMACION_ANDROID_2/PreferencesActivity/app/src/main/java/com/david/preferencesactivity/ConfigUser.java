package com.david.preferencesactivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ConfigUser extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ANADIMOS EL RECURSO DE LAS PREFERENCIAS
        addPreferencesFromResource(R.xml.configuraciones);
    }
}
