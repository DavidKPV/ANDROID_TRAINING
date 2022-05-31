package com.davidkpv.app_mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.davidkpv.app_mvvm.util.User;

import java.util.ArrayList;
import java.util.List;

// LIVE DATA ES UN OBSERVADOR (0 COMPORTAMIENTO PERO MUCHOS DATOS)
// SIMULA UNA APLICACIÓN REACTIVA

public class LiveDataViewModel extends ViewModel {

    // PARA LISTA REACTIVA
    private MutableLiveData<List<User>> userListLiveData;

    // PARA LISTA NO REACTIVA
    private List<User> userList;

    // MÉTODO PARA LEER LOS DATOS
    public LiveData<List<User>> getUserList(){
        if(userListLiveData == null){
            userListLiveData = new MutableLiveData<>();
            userList = new ArrayList<>();
        }
        return userListLiveData;
    }

    // MÉTODO PARA ACTUALIZAR LOS DATOS
    public void addUser(User user){
        userList.add(user);
        // SET VALUE SE UTILIZA CUANDO LAS ACCIONES SE RALIZAN EN PRIMER PLANO (HILO PRINCIPAL)
        userListLiveData.setValue(userList);
    }
}
