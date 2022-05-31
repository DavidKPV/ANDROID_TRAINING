package com.davidkpv.kpvgit.di;

import android.app.Application;

import androidx.room.Room;

import com.davidkpv.kpvgit.api.WebServiceApi;
import com.davidkpv.kpvgit.db.GitHubDb;
import com.davidkpv.kpvgit.db.RepoDao;
import com.davidkpv.kpvgit.db.UserDao;
import com.davidkpv.kpvgit.utils.LiveDataCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Singleton
    @Provides
    WebServiceApi provideGithubService(){
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(WebServiceApi.class);
    }

    @Singleton
    @Provides
    GitHubDb provideDb(Application app){
        return Room.databaseBuilder(app, GitHubDb.class, "github.db").build();
    }

    @Singleton
    @Provides
    UserDao provideUserDao(GitHubDb db){
        return db.userDao();
    }

    @Singleton
    @Provides
    RepoDao provideRepoDao(GitHubDb db){
        return db.repoDao();
    }

}
