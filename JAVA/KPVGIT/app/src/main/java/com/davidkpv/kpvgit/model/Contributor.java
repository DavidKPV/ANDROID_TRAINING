package com.davidkpv.kpvgit.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.google.gson.annotations.SerializedName;

        // LLAVES PRIMARIAS
@Entity(primaryKeys = {"repoName", "repoOwner", "login"},
        // CONEXIÓN DE UNA ENTIDAD CON OTRA
        foreignKeys = @ForeignKey(entity = Repo.class,
                // ENTIDAD PADRE CON LA QUE ESTÁ RELACIONADO
                parentColumns = {"name", "owner_login"},
                // ENTIDAD HIJO CON LA QUE ESTÁ RELACIONADO
                childColumns = {"repoName", "repoOwner"},
                // PROPAGA LA ACTUALIZACIÓN EN LA CLASE PADRE Y EN CLASES HIJAS
                onUpdate = ForeignKey.CASCADE))
public class Contributor {

    @NonNull
    @SerializedName("login")
    public final String login;
    @SerializedName("contributions")
    public final int contributions;
    @SerializedName("avatar_url")
    public final String avatarUrl;

    @NonNull
    public String repoName;
    @NonNull
    public String repoOwner;

    public Contributor(String login, int contributions, String avatarUrl) {
        this.login = login;
        this.contributions = contributions;
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public int getContributions() {
        return contributions;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoOwner() {
        return repoOwner;
    }

    public void setRepoOwner(String repoOwner) {
        this.repoOwner = repoOwner;
    }
}
