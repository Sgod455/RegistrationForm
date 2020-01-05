package com.example.myapplication.asynctask;

import android.os.AsyncTask;

import com.example.myapplication.model.User;
import com.example.myapplication.persistance.UserDao;

public class QueryByMailAsyncTask extends AsyncTask<String, Void, User> {

    private UserDao mUserDao;
    private User mUser;

    public QueryByMailAsyncTask(UserDao userDao){
        this.mUserDao = userDao;
    }

    @Override
    protected User doInBackground(String... strings) {

//        mUser = mUserDao.getUserByEmail(strings);

        return mUser;
        //        return mUser;
    }

    @Override
    protected void onPostExecute(User user) {
        mUser = user;
    }
}
