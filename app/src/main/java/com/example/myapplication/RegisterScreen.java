package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.User;
import com.example.myapplication.persistance.UserRepository;

import java.util.Calendar;
import java.util.regex.Pattern;


public class RegisterScreen extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterScreen";

    //ui
    EditText mFirstName, mLastName, mEmail, mBirthdate, mPassword, mConfirmPassword;
    TextView mBtnToLogin;
    RadioGroup mGenderChoice;
    RadioButton mChosenGender;
    Button mBtnToRegister;

    //vars
    UserRepository mUserRepository;
    DatePickerDialog mDatePickerDialog;
    String mThisFirstName, mThisLastName, mThisEmail, mThisBirhdate, mThisGender, mThisPassword;
    User mUser;
    User mExistingUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirstName = findViewById(R.id.firstName);
        mLastName = findViewById(R.id.lastName);
        mEmail = findViewById(R.id.email);
        mBirthdate = findViewById(R.id.birthDate);
        mGenderChoice = findViewById(R.id.gender);
        mBtnToRegister = findViewById(R.id.buttonToRegister);
        mBtnToLogin = findViewById(R.id.buttonToLogin);
        mPassword = findViewById(R.id.registerPassword);
        mConfirmPassword = findViewById(R.id.confirmPassword);
        mUserRepository = new UserRepository(this);

        setOnClickListeners();

    }

    private void setOnClickListeners() {
        mBtnToLogin.setOnClickListener(this);
        mBtnToRegister.setOnClickListener(this);
        mBirthdate.setOnClickListener(this);
    }

    private void saveUser() {
        if (mFirstName.length() == 0) {
            Toast.makeText(this, "Enter first name please", Toast.LENGTH_SHORT).show();
        } else if (mLastName.length() == 0) {
            Toast.makeText(this, "Enter last name please", Toast.LENGTH_SHORT).show();
        } else if (mEmail.length() == 0) {
            Toast.makeText(this, "Enter an email address please", Toast.LENGTH_SHORT).show();
        } else if (!isValidEmail(mEmail)) {
            Toast.makeText(this, "Enter a valid email please", Toast.LENGTH_SHORT).show();
        } else if (mBirthdate.length() == 0) {
            Toast.makeText(this, "Please choose your birthday", Toast.LENGTH_SHORT).show();
        } else if (mPassword.length() < 6) {
            Toast.makeText(this, "Enter a password with at least 6 characters", Toast.LENGTH_SHORT).show();
        } else if (mConfirmPassword.length() == 0){
            Toast.makeText(this, "Confirm your password please", Toast.LENGTH_SHORT).show();
        } else if (!mPassword.getText().toString().equals(mConfirmPassword.getText().toString())) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        } else
            //TODO: verify user exists
//            if (userExists(mThisEmail)) {
//            mThisEmail = mEmail.getText().toString();
//            Toast.makeText(this, "User Exists", Toast.LENGTH_SHORT).show();
//        } else
            {

            mThisFirstName = mFirstName.getText().toString();
            mThisLastName = mLastName.getText().toString();
            mThisBirhdate = mBirthdate.getText().toString();
            mChosenGender = findViewById(mGenderChoice.getCheckedRadioButtonId());
            mThisGender = mChosenGender.getText().toString();
            mThisPassword = mPassword.getText().toString();
            mThisEmail = mEmail.getText().toString();
            mUser = new User();
            mUser.setFirstName(mThisFirstName);
            mUser.setLastName(mThisLastName);
            mUser.setEmail(mThisEmail);
            mUser.setBirthday(mThisBirhdate);
            mUser.setGender(mThisGender);
            mUser.setPassword(mThisPassword);

//TODO: CHECK IF THIS IS GOOD
                mUserRepository.insertUserTask(mUser);

                String myUser = mUser.toString();
                Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show();
                Intent registerUser = new Intent(this, LoginScreen.class);
                startActivity(registerUser);

                Log.d(TAG, "saveUser: : " + myUser);

        }
    }

    private boolean isValidEmail(EditText email) {
        String myEmail = email.getText().toString();
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(myEmail).matches();
    }

//    private boolean userExists (String email){
//
//        Log.d(TAG, "userExists:  THREAD: " + Thread.currentThread().getName());
//
//        try {
//            mExistingUser = mUserRepository.getUserByEmailTask(email);
//            return false;
//        } catch (NullPointerException e){
//            e.getMessage();
//            return true;
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.birthDate: {

                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mBirthdate.setText(dayOfMonth + "/" + month+1 + "/" + year);
                    }
                }, year, month, day);

                mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                mDatePickerDialog.show();
                break;
            }

            case R.id.buttonToLogin: {
                Intent login = new Intent(this, LoginScreen.class);
                startActivity(login);
                break;
            }

            case R.id.buttonToRegister: {
                saveUser();
                break;
            }

            case R.id.users: {
                Intent displayUsers = new Intent(this, DisplayUser.class);
                startActivity(displayUsers);
                break;
            }
        }
    }
}