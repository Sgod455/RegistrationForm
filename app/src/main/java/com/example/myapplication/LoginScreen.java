package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Map;

public class LoginScreen extends AppCompatActivity {

    EditText email, pass;
    Button loginBtn, registerBtn;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        email = (EditText) findViewById(R.id.loginEmail);
        pass = (EditText) findViewById(R.id.loginPass);
        loginBtn = (Button) findViewById(R.id.loginButton);
        registerBtn = (Button) findViewById(R.id.registrationButton);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUserCredentials(email.getText().toString(), pass.getText().toString())) {
                    Toast.makeText(LoginScreen.this, "Invalid name or password", Toast.LENGTH_SHORT).show();
                } else {
                    Intent displayInfo = new Intent(LoginScreen.this, DisplayUser.class);
                    displayInfo.putExtra("userEmail", email.getText().toString());
                    startActivity(displayInfo);
                }
            }
        });

//        //shared key = user+pass
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String user = name.getText().toString();
//                String password = pass.getText().toString();
//
//                SharedPreferences preferences = getSharedPreferences("userData", MODE_PRIVATE);
//                String userDetails = preferences.getString(user + password, "Username or Password is incorrect");
//
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("display", userDetails );
//                editor.apply();
//
//                Intent displayScreen = new Intent(LoginScreen.this, DisplayUser.class);
//                startActivity(displayScreen);
//            }
//        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrationScreen = new Intent(LoginScreen.this, RegisterScreen.class);
                startActivity(registrationScreen);
            }
        });

    }

    private boolean validateUserCredentials(String thisEmail, String password){
        preferences = getApplicationContext().getSharedPreferences("userData", MODE_PRIVATE);
        Map<String, ?> stringMap = preferences.getAll();
        User user = null;
        for (Map.Entry<String, ?> entry : stringMap.entrySet()){
            String userDetails = entry.getValue().toString();
            Gson g = new Gson();
            user = g.fromJson(userDetails, User.class);

            if (user.getEmail().equals(thisEmail) && user.getPassword().equals(password)){
                return true;
            }
        }

        return false;
    }
}
