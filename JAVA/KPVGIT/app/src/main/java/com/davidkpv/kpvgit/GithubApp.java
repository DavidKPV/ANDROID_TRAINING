package com.davidkpv.kpvgit;

import android.app.Activity;
import android.app.Application;

import com.davidkpv.kpvgit.di.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class GithubApp extends Application implements HasActivityInjector {

    // NOS DEVOLVERÁ UNA ACTIVITY INYECTOR
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.init(this);
    }


    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
