package com.example.myapplication.asynctask;

import android.os.AsyncTask;

import com.example.myapplication.model.User;
import com.example.myapplication.persistance.UserDao;

public class InsertAsyncTask extends AsyncTask<User, Void, Void> {

    private UserDao mUserDao;

    public InsertAsyncTask(UserDao userDao){
        mUserDao = userDao;
    }

    @Override
    protected Void doInBackground(User... users) {

        mUserDao.insert(users);
        return null;
    }
}
