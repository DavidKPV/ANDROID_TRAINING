package com.davidkpv.kpvgit.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.davidkpv.kpvgit.GithubApp;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

public class AppInjector {

    private AppInjector(){}

    public static void init(GithubApp githubApp){
        // ESTA CLASE LA GENERA DAGGER DE MANERA AUTOMÁTICA CUANDO SE COMPILA LA APLICACIÓN
        // PERO ES IMPORTANTE TENER EN CUENTA QUE SE DEBE LLAMAR TAL CUAL COMO LA INTERFAZ DEL
        // COMPONENTE INICIANDO CON LA PALABRA "Dagger"
        DaggerAppComponent.builder().application(githubApp).build().inject(githubApp);

        githubApp.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                handleActivity(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

    private static void handleActivity(Activity activity){
        if(activity instanceof HasSupportFragmentInjector){
            AndroidInjection.inject(activity);
        }
        if(activity instanceof FragmentActivity){
            ((FragmentActivity) activity).getSupportFragmentManager()
                    .registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                        @Override
                        public void onFragmentCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
                            if(f instanceof Injectable){
                                AndroidSupportInjection.inject(f);
                            }
                        }
                    }, true);
        }
    }
}
