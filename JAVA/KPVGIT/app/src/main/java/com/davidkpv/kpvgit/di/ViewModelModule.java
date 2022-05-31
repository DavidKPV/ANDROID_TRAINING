package com.davidkpv.kpvgit.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.davidkpv.kpvgit.ui.repo.RepoViewModel;
import com.davidkpv.kpvgit.ui.search.SearchViewModel;
import com.davidkpv.kpvgit.ui.user.UserViewModel;
import com.davidkpv.kpvgit.viewmodel.GithubViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

// LOS MÓDULOS SE ENCARGAN DE PROPORCIONARNOS LAS INSTANCIAS QUE NECESITAMOS
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindUserViewModel(UserViewModel userViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    abstract ViewModel bindSearchViewModel(SearchViewModel searchViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RepoViewModel.class)
    abstract ViewModel bindRepoViewModel(RepoViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(GithubViewModelFactory factory);
}
