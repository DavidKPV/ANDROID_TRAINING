package com.davidkpv.kpvgit.model;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.davidkpv.kpvgit.db.GitHubTypeConverters;

import java.util.List;

@Entity(primaryKeys = {"query"})
@TypeConverters(GitHubTypeConverters.class)
public class RepoSearchResult {
    @NonNull
    public final String query;
    public final List<Integer> repoIds;
    public final int totalCount;
    public final Integer next;

    public RepoSearchResult(@NonNull String query, List<Integer> repoIds, int totalCount, Integer next) {
        this.query = query;
        this.repoIds = repoIds;
        this.totalCount = totalCount;
        this.next = next;
    }
}
