package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import java.util.Optional;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("SELECT * FROM users WHERE uid LIKE :uid LIMIT 1")
    User loadUserById(int uid);

    @Query("SELECT * FROM users WHERE email LIKE :email LIMIT 1")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT * FROM users")
    List<User> getAll();

    @Delete
    void delete (User user);
}
