package com.davidkpv.kpvgit.di;

import com.davidkpv.kpvgit.ui.repo.RepoFragment;
import com.davidkpv.kpvgit.ui.search.SearchFragment;
import com.davidkpv.kpvgit.ui.user.UserFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    // PARA GENERAR NUESTROS POTENCIALES CLIENTES DE DAGGER
    @ContributesAndroidInjector
    abstract RepoFragment contributeRepoFragment();

    @ContributesAndroidInjector
    abstract UserFragment contributeUserFragment();

    @ContributesAndroidInjector
    abstract SearchFragment contributeSearchFragment();

}
