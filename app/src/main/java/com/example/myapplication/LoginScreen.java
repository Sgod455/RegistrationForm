package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.model.User;
import com.example.myapplication.persistance.UserRepository;

public class LoginScreen extends AppCompatActivity {

    EditText email, pass;
    Button loginBtn, registerBtn, displayUsers;

    UserRepository mUserRepo;

    User myUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        email = (EditText) findViewById(R.id.loginEmail);
        pass = (EditText) findViewById(R.id.loginPass);
        loginBtn = (Button) findViewById(R.id.loginButton);
        registerBtn = (Button) findViewById(R.id.registrationButton);
        displayUsers = (Button) findViewById(R.id.users);
        mUserRepo = new UserRepository(this);




        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//TODO: Find user by email and check password

//                String thisEmail = email.getText().toString();
//                String thisPass = pass.getText().toString();
//
//                myUser = mUserRepo.getUserByEmailTask(thisEmail);
//                String userPassword = myUser.getPassword();
//
//
//
//                if (thisPass.equals(userPassword)){
//                    Intent displayUsers = new Intent(LoginScreen.this, DisplayUser.class);
//                    startActivity(displayUsers);
//                }

                Intent displayUsers = new Intent(LoginScreen.this, DisplayUser.class);
                startActivity(displayUsers);
            }
        });



        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrationScreen = new Intent(LoginScreen.this, RegisterScreen.class);
                startActivity(registrationScreen);
            }
        });

        displayUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent displayUsers = new Intent(LoginScreen.this, DisplayUser.class);
                startActivity(displayUsers);
            }
        });



    }

}
