package com.davidkpv.kpvgit.viewmodel;

import android.app.assist.AssistStructure;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

// AYUDA A CREAR DE FORMA DINÁMICA LOS VIEW MODEL CON LA INYECCIÓN DE DEPENDENCIAS
@Singleton
public class GithubViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public GithubViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators){
        this.creators = creators;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<? extends ViewModel> creator = creators.get(modelClass);
        if(creator == null){
            for(Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()){
                if(modelClass.isAssignableFrom(entry.getKey())){
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if(creator == null){
            throw new IllegalArgumentException("Clase Modelo Desconocido " + modelClass);
        }
        try {
            return (T) creator.get();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
