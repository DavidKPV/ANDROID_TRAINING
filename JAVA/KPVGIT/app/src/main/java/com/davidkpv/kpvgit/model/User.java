package com.davidkpv.kpvgit.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

/*
 LA ETIQUETA ENTITY CREA UNA TABLA DENTRO DE LA BASE DE DATOS GRACIAS A LA LIBRERÍA DE ROOM CON LA
 AYUDA DE LA CLASE EN LA QUE SE IMPPLEMENTA. LE ASIGNAMOS A LA NUEVA TABLA UNA LLAVE PRIMARIA
 LA CUAL SERÁ EL CAMPO LOGIN
*/

@Entity(primaryKeys = "login")
public class User {
    @SerializedName("login")
    @NonNull
    public final String login;
    @SerializedName("avatar_url")
    public final String avatarUrl;
    @SerializedName("name")
    public final String name;
    @SerializedName("company")
    public final String company;
    @SerializedName("repos_url")
    public final String resposUrl;
    @SerializedName("blog")
    public final String blog;

    public User(String login, String avatarUrl, String name, String company, String resposUrl, String blog) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.company = company;
        this.resposUrl = resposUrl;
        this.blog = blog;
    }

    // GETTERS ************************

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getResposUrl() {
        return resposUrl;
    }

    public String getBlog() {
        return blog;
    }

}
