package com.davidkpv.app_mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.davidkpv.app_mvvm.util.User;


public class DBLDViewModel extends ViewModel {

    private MutableLiveData<User> user;
    private MutableLiveData<Boolean> visible;
    private MutableLiveData<Float> size;

    public DBLDViewModel(){
        user = new MutableLiveData<>();
        visible = new MutableLiveData<>();
        size = new MutableLiveData<>();
        visible.setValue(true);
        size.setValue(14f);
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user.setValue(user);
    }

    public void updateUser(){
        User user1 = new User("Ivonne", "22");
        this.user.setValue(user1);
    }

    public LiveData<Boolean> getVisible(){
        return visible;
    }

    public void setVisible(Boolean visible){
        this.visible.setValue(visible);
    }

    public void changeVisibility(){
        if(visible.getValue().booleanValue() == true){
            visible.setValue(false);
        } else {
            visible.setValue(true);
        }
        size.setValue(size.getValue().floatValue() + 5l);
    }

    public LiveData<Float> getSize(){
        return size;
    }
}
