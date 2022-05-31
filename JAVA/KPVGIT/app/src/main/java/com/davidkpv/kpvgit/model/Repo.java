package com.davidkpv.kpvgit.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;

import com.google.gson.annotations.SerializedName;

/*
  LOS ÍNDICES PERMITEN MEJORAR LA CONSULTA DE LAS QUERY "SELECT * FROM" PERO RELENTIZAN LAS
  INSERCIONES Y ACTUALIZACIONES. GRACIAS A LA CLASE POJO Y A ROOM SE PODRÁN CREAR LAS TABLAS EN
  LA BASE DE DATOS LOCAL PARA MOSTRAR DATOS EN CASO DE NO TENER INTERNET
 */
@Entity(indices = {@Index("id"), @Index("owner_login")},
    primaryKeys = {"name", "owner_login"})
public class Repo {
    public static final int UNKNOWN_ID = -1;

    public final int id;
    @SerializedName("name")
    @NonNull
    public final String name;
    @SerializedName("full_name")
    public final String fullname;
    @SerializedName("description")
    public final String description;
    @SerializedName("stargazers_count")
    public final String stars;


    @SerializedName("owner")
    @Embedded(prefix = "owner_")
    @NonNull
    public final Owner owner;

    public Repo(int id, String name, String fullname, String description, String stars, Owner owner) {
        this.id = id;
        this.name = name;
        this.fullname = fullname;
        this.description = description;
        this.stars = stars;
        this.owner = owner;
    }

    public static class Owner{
        @NonNull
        @SerializedName("login")
        public final String login;

        @SerializedName("url")
        public final String url;

        public Owner(String login, String url) {
            this.login = login;
            this.url = url;
        }
    }

}
