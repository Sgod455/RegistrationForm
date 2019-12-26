package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

public class RegisterScreen extends AppCompatActivity {

    DatePickerDialog pickerDialog;
    EditText getFirstName, getLastName, getEmail, getBirthday, getPassword, getConfirmPassword;
    RadioButton chosenGender;
    RadioGroup genderChoice;
    Button btnToLogin;
    User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //get Birthday field and make it open date picker dialog
        getBirthday = (EditText) findViewById(R.id.birthDate);
        getBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                pickerDialog = new DatePickerDialog(RegisterScreen.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        getBirthday.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                }, year, month, day);
                pickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                pickerDialog.show();
            }
        });
        //end of datepicker

        btnToLogin = (Button) findViewById(R.id.buttonToLogin);
        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginScreen = new Intent(RegisterScreen.this, LoginScreen.class);
                startActivity(loginScreen);
            }
        });
    }



    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }


}
