package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Map;

public class DisplayUser extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        preferences = getApplicationContext().getSharedPreferences("userData", MODE_PRIVATE);

        TextView displayInfo = (TextView) findViewById(R.id.userView);
        String userEmail = getIntent().getStringExtra("userEmail");
        User thisUser = retrieveUserByEmail(userEmail);
        String thisFirstName = thisUser.getFirstName();
        displayInfo.setText("Name" + thisFirstName);

    }

    public User retrieveUserByEmail(String email){
        Gson g = new Gson();
        User user = null;

        preferences = getApplicationContext().getSharedPreferences("userData", MODE_PRIVATE);
        Map<String, ?> prefsMap = preferences.getAll();
        for (Map.Entry<String, ?> entry: prefsMap.entrySet()) {
            String userDetails = entry.getValue().toString();
            if(userDetails.contains(email)){
                user = g.fromJson(userDetails,User.class);
            }else{
                continue;
            }
        }
        return user;
    }
}
