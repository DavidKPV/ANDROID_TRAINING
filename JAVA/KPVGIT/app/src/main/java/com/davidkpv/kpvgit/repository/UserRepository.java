package com.davidkpv.kpvgit.repository;

import androidx.lifecycle.LiveData;

import com.davidkpv.kpvgit.AppExecutors;
import com.davidkpv.kpvgit.api.ApiResponse;
import com.davidkpv.kpvgit.api.WebServiceApi;
import com.davidkpv.kpvgit.db.UserDao;
import com.davidkpv.kpvgit.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {

    private final UserDao userDao;
    private final WebServiceApi gitHubService;
    private final AppExecutors appExecutors;

    @Inject
    UserRepository(AppExecutors appExecutors, UserDao userDao, WebServiceApi webServiceApi){
        this.userDao = userDao;
        this.gitHubService = webServiceApi;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<User>> loadUser(String login){
        return new NetworkBoundResource<User, User>(appExecutors){

            @Override
            protected LiveData<User> loadFromDb() {
                return userDao.findByLogin(login);
            }

            @Override
            protected boolean shouldFetch(User data) {
                return data == null;
            }

            @Override
            protected void saveCallResult(User item) {
                userDao.insert(item);
            }

            @Override
            protected LiveData<ApiResponse<User>> createCall() {
                return gitHubService.getUser(login);
            }
        }.asLiveData();
    }
}
