package com.davidkpv.kpvgit.di;

import com.davidkpv.kpvgit.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity contributeMainActivity();

}
