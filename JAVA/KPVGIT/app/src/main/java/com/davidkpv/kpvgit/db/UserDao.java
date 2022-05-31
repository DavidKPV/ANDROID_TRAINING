package com.davidkpv.kpvgit.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.davidkpv.kpvgit.model.User;

@Dao
public interface UserDao {
    // INDICAMOS UNA INSERCIÓN Y CON ONCONFLIC INDICAMOS QUE REEMPLECE LOS DATOS SI EXISTEN YA EN LA
    // TABLA
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("SELECT * FROM user WHERE login = :login")
    LiveData<User> findByLogin(String login);
}
