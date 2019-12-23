package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginScreen extends AppCompatActivity {

    EditText name, pass;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        final EditText name = (EditText) findViewById(R.id.username);
        final EditText pass = (EditText) findViewById(R.id.loginPass);
        Button loginBtn = (Button) findViewById(R.id.loginButton);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = name.getText().toString();
                String password = pass.getText().toString();

                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                String userDetails = preferences.getString(user + password, "Username or Password is incorrect");

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("display", userDetails );
                editor.apply();

                Intent displayScreen = new Intent(LoginScreen.this, DisplayUser.class);
                startActivity(displayScreen);
            }
        });
    }
}
