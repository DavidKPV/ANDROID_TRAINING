package com.davidkpv.kpvgit.db;

import android.util.SparseArray;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.davidkpv.kpvgit.model.Contributor;
import com.davidkpv.kpvgit.model.Repo;
import com.davidkpv.kpvgit.model.RepoSearchResult;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Dao
public abstract class RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(Repo... repos);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertContributors(List<Contributor> contributors);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertRepos(List<Repo> repositories);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long createRepoIfNotExists(Repo repo);

    @Query("SELECT * FROM repo WHERE owner_login = :login AND name = :name")
    public abstract LiveData<Repo> load(String login, String name);

    @Query("SELECT login, avatarUrl, repoName, repoOwner, contributions FROM contributor WHERE repoName = :name AND repoOwner = :owner ORDER BY contributions DESC")
    public abstract LiveData<List<Contributor>> loadContributors(String name, String owner);

    @Query("SELECT * FROM Repo WHERE owner_login = :owner ORDER BY stars DESC")
    public abstract LiveData<List<Repo>> loadRepositories(String owner);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(RepoSearchResult result);

    public LiveData<List<Repo>> loadOrdered(List<Integer> repoIds){
        // LA CLASE SparseInArray es un método mucho mejor optimizado que HashMap para ordenar listas
        SparseArray order = new SparseArray();
        int index = 0;
        for(Integer repoId: repoIds){
            order.put(repoId, index++);
        }
        return Transformations.map(loadById(repoIds), new Function<List<Repo>, List<Repo>>() {
            @Override
            public List<Repo> apply(List<Repo> repositories) {
                Collections.sort(repositories, new Comparator<Repo>() {
                    @Override
                    public int compare(Repo o1, Repo o2) {
                        int pos1 = (int) order.get(o1.id);
                        int pos2 = (int) order.get(o2.id);
                        return pos1 - pos2;
                    }
                });
                return repositories;
            }
        });
    }

    @Query("SELECT * FROM RepoSearchResult WHERE query = :query")
    public abstract LiveData<RepoSearchResult> search(String query);

    @Query("SELECT * FROM Repo WHERE id in(:repoIds)")
    protected abstract LiveData<List<Repo>> loadById(List<Integer> repoIds);

    @Query("SELECT * FROM RepoSearchResult WHERE query = :query")
    public abstract RepoSearchResult findSearchResult(String query);

}
