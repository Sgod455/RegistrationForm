package com.example.myapplication.persistance;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.myapplication.asynctask.DeleteAsyncTask;
import com.example.myapplication.asynctask.InsertAsyncTask;
import com.example.myapplication.asynctask.QueryByMailAsyncTask;
import com.example.myapplication.asynctask.UpdateAsyncTask;
import com.example.myapplication.model.User;

import java.util.List;

public class UserRepository {

    private UsersDatabase mUsersDatabase;
    private User mUser;

    public UserRepository (Context context) { mUsersDatabase = UsersDatabase.getInstance(context);}

    public void insertUserTask(User user){
        new InsertAsyncTask(mUsersDatabase.userDao()).execute(user);
    }

    public void deleteUserTask(User user){
        new DeleteAsyncTask(mUsersDatabase.userDao()).execute(user);
    }
    public void updateUserTask(User user){
        new UpdateAsyncTask(mUsersDatabase.userDao()).execute(user);
    }

    public LiveData<List<User>> retrieveUsersTask(){
        return mUsersDatabase.userDao().getAllUsers();
    }

    public User getUserByEmailTask(String email){
//        new QueryByMailAsyncTask(mUsersDatabase.userDao()).execute(email);
        return mUsersDatabase.userDao().getUserByEmail(email);
    }
}
