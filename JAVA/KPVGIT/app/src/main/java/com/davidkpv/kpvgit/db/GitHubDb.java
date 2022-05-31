package com.davidkpv.kpvgit.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.davidkpv.kpvgit.model.Contributor;
import com.davidkpv.kpvgit.model.Repo;
import com.davidkpv.kpvgit.model.RepoSearchResult;
import com.davidkpv.kpvgit.model.User;

// CLASE QUE GENERA DE ACUERDO A ROOM LA BASE DE DATOS COMPLETA
@Database(entities = {User.class, Repo.class, Contributor.class, RepoSearchResult.class}, version = 1)
public abstract class GitHubDb extends RoomDatabase {

    abstract public UserDao userDao();

    abstract public RepoDao repoDao();
}
