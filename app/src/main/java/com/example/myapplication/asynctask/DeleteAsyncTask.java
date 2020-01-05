package com.example.myapplication.asynctask;

import android.os.AsyncTask;

import com.example.myapplication.model.User;
import com.example.myapplication.persistance.UserDao;

public class DeleteAsyncTask extends AsyncTask<User, Void, Void> {

    private UserDao mUserDao;

    public DeleteAsyncTask(UserDao userDao){
        mUserDao = userDao;
    }

    @Override
    protected Void doInBackground(User... users) {
        mUserDao.delete(users);
        return null;
    }
}
