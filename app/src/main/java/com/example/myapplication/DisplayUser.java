package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.model.User;
import com.example.myapplication.persistance.UserRepository;

import java.util.ArrayList;
import java.util.List;


public class DisplayUser extends AppCompatActivity implements UserAdapter.OnUserListener, View.OnClickListener {



    private ArrayList<User> mUsers =new ArrayList<>();
    private RecyclerView mRecyclerView;
    private UserAdapter mUserAdapter;
    UserRepository mUserRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        mRecyclerView = findViewById(R.id.recycler_view);
        mUserRepo = new UserRepository(this);

        initRecyclerView();
        returnUsers();

//        TextView displayInfo = (TextView) findViewById(R.id.userView);
//        String userEmail = getIntent().getStringExtra("userEmail");

//        displayInfo.setText("Name" + "");


    }


    private void returnUsers(){
        mUserRepo.retrieveUsersTask().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (mUsers.size()>0){
                    mUsers.clear();
                }
                if (mUsers != null){
                    mUsers.addAll(users);
                }
                mUserAdapter.notifyDataSetChanged();
            }
        });

    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mUserAdapter = new UserAdapter(mUsers, this);
        mRecyclerView.setAdapter(mUserAdapter);
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserClick(int position) {
        Toast.makeText(this, "User Clicked", Toast.LENGTH_SHORT).show();
    }
}
