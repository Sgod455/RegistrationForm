package com.example.myapplication.asynctask;

import android.os.AsyncTask;

import com.example.myapplication.model.User;
import com.example.myapplication.persistance.UserDao;

public class UpdateAsyncTask extends AsyncTask<User, Void, Void> {

    private UserDao mUserDao;

    public UpdateAsyncTask(UserDao userDao){
        mUserDao = userDao;
    }

    @Override
    protected Void doInBackground(User... users) {
        mUserDao.update(users);
        return null;
    }
}
