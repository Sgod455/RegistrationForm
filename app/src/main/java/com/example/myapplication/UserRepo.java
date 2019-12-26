package com.example.myapplication;

import android.app.Application;

public class UserRepo {

    private UserDao mUserDao;

    UserRepo (Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        mUserDao = database.userDao();

    }

}
