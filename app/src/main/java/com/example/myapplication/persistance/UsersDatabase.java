package com.example.myapplication.persistance;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.model.User;

@Database(entities = {User.class}, version = 2)
public abstract class UsersDatabase extends RoomDatabase {


    public static final String DATABASE_NAME = "notes_db";

    public static UsersDatabase instance;

    public abstract UserDao userDao();

    static UsersDatabase getInstance(final Context context){
        if (instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    UsersDatabase.class, DATABASE_NAME)
                    .build();
        }
        return instance;
    }

}
