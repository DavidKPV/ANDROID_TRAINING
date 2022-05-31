package com.davidkpv.kpvgit.repository;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.davidkpv.kpvgit.AppExecutors;
import com.davidkpv.kpvgit.api.ApiResponse;
import com.davidkpv.kpvgit.api.WebServiceApi;
import com.davidkpv.kpvgit.db.GitHubDb;
import com.davidkpv.kpvgit.db.RepoDao;
import com.davidkpv.kpvgit.model.Contributor;
import com.davidkpv.kpvgit.model.Repo;
import com.davidkpv.kpvgit.model.RepoSearchResponse;
import com.davidkpv.kpvgit.model.RepoSearchResult;
import com.davidkpv.kpvgit.utils.AbsentLiveData;
import com.davidkpv.kpvgit.utils.RateLimiter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

// CLASE ENCARGADA DE ACCEDER AL WEB SERVICE Y AL REPO DAO (BASE DE DATOS LOCAL)
@Singleton
public class RepoRepository {

    private final GitHubDb db;
    private final RepoDao repoDao;
    private final WebServiceApi githubService;
    private final AppExecutors appExecutors;

    private RateLimiter<String> repoListRateLimit = new RateLimiter<>(10, TimeUnit.MINUTES);

    @Inject
    public RepoRepository(AppExecutors appExecutors, GitHubDb db, RepoDao repoDao, WebServiceApi githubService){
        this.db = db;
        this.repoDao = repoDao;
        this.githubService = githubService;
        this.appExecutors = appExecutors;
    }

    // PRIMERA PETICIÓN AL WEB SERVICE
    public LiveData<Resource<List<Repo>>> loadRepos(String owner){
        return new NetworkBoundResource<List<Repo>, List<Repo>>(appExecutors){

            // PARA DEVOLVER UNA LIVE DATA DE LOS REPOSITORIOS
            @Override
            protected LiveData<List<Repo>> loadFromDb() {
                return repoDao.loadRepositories(owner);
            }

            // PARA VERIFICAR SI SE DEBE DE LLAMAR AL CONSUMO DEL WEB SERVICE
            @Override
            protected boolean shouldFetch(List<Repo> data) {
                return data == null || data.isEmpty() || repoListRateLimit.shouldFetch(owner);
            }

            // PARA ALMACENAR LOS DATOS DE LOS REPOSITORIOS
            @Override
            protected void saveCallResult(List<Repo> item) {
                repoDao.insertRepos(item);
            }

            // PARA CREAR LA LLAMADA AL WEB SERVICE
            @Override
            protected LiveData<ApiResponse<List<Repo>>> createCall() {
                return githubService.getRepos(owner);
            }

            // TRAEMOS EL OTRO MÉTODO ABSTRACTO SOBRESCRITO QUE INDICA QUE LA CARGA DE DATOS HA FALLADO
            @Override
            protected void onFetchFailed(){
                repoListRateLimit.reset(owner);
            }

            // INDICAMOS QUE LOS DATOS LOS QUEREMOS COMO UN LIVEDATA
        }.asLiveData();
    }

    public LiveData<Resource<Repo>> loadRepo(String owner, String name){
        return new NetworkBoundResource<Repo, Repo>(appExecutors) {
            @Override
            protected LiveData<Repo> loadFromDb() {
                return repoDao.load(owner, name);
            }

            @Override
            protected boolean shouldFetch(Repo data) {
                return data == null;
            }

            @Override
            protected void saveCallResult(Repo item) {
                repoDao.insert(item);
            }

            @Override
            protected LiveData<ApiResponse<Repo>> createCall() {
                return githubService.getRepo(owner, name);
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<Contributor>>> loadContributors(String owner, String name){
        return new NetworkBoundResource<List<Contributor>, List<Contributor>>(appExecutors) {
            @Override
            protected LiveData<List<Contributor>> loadFromDb() {
                return repoDao.loadContributors(name, owner);
            }

            @Override
            protected boolean shouldFetch(List<Contributor> data) {
                return data == null || data.isEmpty();
            }

            // SALVAR LOS DATOS EN LA BASE DE DATOS
            @Override
            protected void saveCallResult(List<Contributor> contributors) {
                for(Contributor contributor: contributors){
                    contributor.setRepoName(name);
                    contributor.setRepoOwner(owner);
                }
                db.beginTransaction();
                try {
                    repoDao.createRepoIfNotExists(new Repo(Repo.UNKNOWN_ID, name, owner +
                            "/" + name, "", "0", new Repo.Owner(owner, null)));
                    // AHORA SE HACE UN INSERT DE LA LISTA DE CONTRIBUTORS
                    repoDao.insertContributors(contributors);
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
            }

            @Override
            protected LiveData<ApiResponse<List<Contributor>>> createCall() {
                return githubService.getContributors(owner, name);
            }
        }.asLiveData();
    }

    // MÉTODO PARA REALIZAR UNA BÚSQUEDA DE SIGUIENTE PÁGINA DE UN REPOSITORIO
    public LiveData<Resource<Boolean>> searchNextPage(String query){
        FetchNextSearchPageTask fetchNextSearchPageTask = new FetchNextSearchPageTask(
                query, githubService, db
        );

        // INDICAMOS QUE SE EJECUTE EN EL HILO DE LA LECTURA DE LA BASE DE DATOS
        appExecutors.networkId().execute(fetchNextSearchPageTask);
        return fetchNextSearchPageTask.getLiveData();
    }


    public LiveData<Resource<List<Repo>>> search(String query){
        return new NetworkBoundResource<List<Repo>, RepoSearchResponse>(appExecutors){

            @Override
            protected LiveData<List<Repo>> loadFromDb() {
                                                                        // LA FUNCIÓN ES UNA INTERFAZ FUNCIONAL QUE TIENE UNA ENTRADA Y UNA SALIDA
                return Transformations.switchMap(repoDao.search(query), new Function<RepoSearchResult, LiveData<List<Repo>>>() {
                    @Override
                    public LiveData<List<Repo>> apply(RepoSearchResult searchData) {
                        if(searchData == null){
                            return AbsentLiveData.create();
                        } else {
                            return repoDao.loadOrdered(searchData.repoIds);
                        }
                    }
                });
            }

            @Override
            protected boolean shouldFetch(List<Repo> data) {
                return data == null;
            }

            @Override
            protected void saveCallResult(RepoSearchResponse item) {
                List<Integer> repoIds = item.getRepoIds();
                RepoSearchResult repoSearchResult = new RepoSearchResult(
                        query, repoIds, item.getTotal(), item.getNextPage()
                );

                db.beginTransaction();
                try {
                    repoDao.insertRepos(item.getItems());
                    repoDao.insert(repoSearchResult);
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
            }

            // PARA CREAR LA PETICIÓN
            @Override
            protected LiveData<ApiResponse<RepoSearchResponse>> createCall() {
                return githubService.searchRepos(query);
            }

            @Override
            protected RepoSearchResponse processResponse(ApiResponse<RepoSearchResponse> response) {
                RepoSearchResponse body = response.body;
                if(body != null){
                    body.setNextPage(response.getNextPage());
                }
                return body;
            }
        }.asLiveData();
    }
}
