package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


public class DisplayUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);


        TextView displayInfo = (TextView) findViewById(R.id.userView);
        String userEmail = getIntent().getStringExtra("userEmail");

        displayInfo.setText("Name" + "");

    }


}
