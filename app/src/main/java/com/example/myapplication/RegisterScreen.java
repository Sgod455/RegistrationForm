package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Optional;
import java.util.regex.Pattern;

public class RegisterScreen extends AppCompatActivity {

    DatePickerDialog pickerDialog;
    EditText getFirstName, getLastName, getEmail, getBirthday, getPassword, getConfirmPassword;
    RadioButton chosenGender;
    RadioGroup genderChoice;
    Button btnToLogin;
    User myUser;
    UserDao myUserDao;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    RegisterScreen (Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        myUserDao = database.userDao();
    }

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
        }); //end of datepicker

        btnToLogin = (Button) findViewById(R.id.buttonToLogin);
        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginScreen = new Intent(RegisterScreen.this, LoginScreen.class);
                startActivity(loginScreen);
            }
        });
    }

    public void saveUser(View v){

        getFirstName = (EditText) findViewById(R.id.firstName);
        String thisFirstName = getFirstName.getText().toString();


        getLastName = (EditText) findViewById(R.id.lastName);
        String thisLastName = getLastName.getText().toString();


        getEmail = (EditText) findViewById(R.id.email);
        String thisEmail = getEmail.getText().toString();


        getPassword = (EditText) findViewById(R.id.password);
        String thisPassword = getPassword.getText().toString();


        getConfirmPassword = (EditText) findViewById(R.id.confirmPassword);
        String thisConfirmPassword = getConfirmPassword.getText().toString();

        String myBirthday = getBirthday.getText().toString();

        genderChoice = (RadioGroup) findViewById(R.id.gender);

        chosenGender = (RadioButton) findViewById(genderChoice.getCheckedRadioButtonId());

        String userGender = chosenGender.getText().toString();

        if (thisFirstName.isEmpty()){
            Toast.makeText(RegisterScreen.this, "Enter your first name", Toast.LENGTH_SHORT).show();
        } else

        if (thisLastName.isEmpty()) {
            Toast.makeText(RegisterScreen.this, "Enter your last name", Toast.LENGTH_SHORT).show();
        } else

        if (thisEmail.isEmpty()) {
            Toast.makeText(RegisterScreen.this, "Enter your email", Toast.LENGTH_SHORT).show();
        } else if (!isValidEmail(thisEmail)) {
            Toast.makeText(RegisterScreen.this, "Enter a valid e-mail", Toast.LENGTH_SHORT).show();
        } else

        if (myBirthday.isEmpty()){
            Toast.makeText(RegisterScreen.this, "Pick your birthday", Toast.LENGTH_SHORT).show();
        } else

        if (thisPassword.isEmpty()) {
            Toast.makeText(RegisterScreen.this, "Enter your a password", Toast.LENGTH_SHORT).show();
        } else

        if (thisConfirmPassword.isEmpty()) {
            Toast.makeText(RegisterScreen.this, "Confirm your password", Toast.LENGTH_SHORT).show();
        } else if (!thisPassword.equals(thisConfirmPassword)) {
            Toast.makeText(RegisterScreen.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        } else {

            User myUser = new User();
            myUser.setFirstName(thisFirstName);
            myUser.setLastName(thisLastName);
            myUser.setEmail(thisEmail);
            myUser.setBirthDay(myBirthday);
            myUser.setGender(userGender);
            myUser.setPassword(thisPassword);

            myUserDao.insert(myUser);

//            if (emailExists(thisEmail)){
//                Toast.makeText(RegisterScreen.this, "E-mail Exists", Toast.LENGTH_LONG).show();
//            } else {
//
//                userRegisterSharedPref(myUser);
//
//            }
        }


    }


//    //register user to shared prefs
//    private void userRegisterSharedPref(User user){
//        preferences = getApplicationContext().getSharedPreferences("userData", MODE_PRIVATE);
//        editor = preferences.edit();
//
//        Gson gson = new Gson();
//        String json = gson.toJson(user);
//        String uniqueID = UUID.randomUUID().toString();
//        editor.putString(uniqueID, json);
//
//        boolean isRegistered = editor.commit();
//
//        if (isRegistered){
//            Toast.makeText(RegisterScreen.this, "User Registered", Toast.LENGTH_LONG).show();
//            Intent displayLogin = new Intent(RegisterScreen.this, LoginScreen.class);
//            startActivity(displayLogin);
//        } else {
//            Toast.makeText(RegisterScreen.this, "Couldn't register", Toast.LENGTH_LONG).show();
//        }
//    }


//    //check if email exists in shared prefs
//    private boolean emailExists(String email){
//        preferences = getApplicationContext().getSharedPreferences("userData", MODE_PRIVATE);
//        Map<String, ?> stringMap = preferences.getAll();
//        for (Map.Entry<String, ?> entry : stringMap.entrySet()){
//            String userDetails = entry.getValue().toString();
//            if (userDetails.contains(email)){
//                return true;
//            }
//        }
//
//        return false;
//    }

////    printing data in console with Register button clicked
//    public void printData(View view) {
//        getFirstName = (EditText) findViewById(R.id.firstName);
//        String thisFirstName = getFirstName.getText().toString();
//
//
//        getLastName = (EditText) findViewById(R.id.lastName);
//        String thisLastName = getLastName.getText().toString();
//
//
//        getEmail = (EditText) findViewById(R.id.email);
//        String thisEmail = getEmail.getText().toString();
//
//
//        getPassword = (EditText) findViewById(R.id.password);
//        String thisPassword = getPassword.getText().toString();
//
//
//        getConfirmPassword = (EditText) findViewById(R.id.confirmPassword);
//        String thisConfirmPassword = getConfirmPassword.getText().toString();
//
//        String myBirthday = getBirthday.getText().toString();
//
//        genderChoice = (RadioGroup) findViewById(R.id.gender);
//
//        chosenGender = (RadioButton) findViewById(genderChoice.getCheckedRadioButtonId());
//
//        String getGender = chosenGender.getText().toString();
//
//        if (thisFirstName.isEmpty()){
//            Toast.makeText(RegisterScreen.this, "Enter your first name", Toast.LENGTH_SHORT).show();
//        } else
//
//        if (thisLastName.isEmpty()) {
//            Toast.makeText(RegisterScreen.this, "Enter your last name", Toast.LENGTH_SHORT).show();
//        } else
//
//        if (thisEmail.isEmpty()) {
//            Toast.makeText(RegisterScreen.this, "Enter your email", Toast.LENGTH_SHORT).show();
//        } else if (!isValidEmail(thisEmail)) {
//            Toast.makeText(RegisterScreen.this, "Enter a valid e-mail", Toast.LENGTH_SHORT).show();
//        } else
//
//        if (myBirthday.isEmpty()){
//            Toast.makeText(RegisterScreen.this, "Pick your birthday", Toast.LENGTH_SHORT).show();
//        } else
//
//        if (thisPassword.isEmpty()) {
//            Toast.makeText(RegisterScreen.this, "Enter your a password", Toast.LENGTH_SHORT).show();
//        } else
//
//        if (thisConfirmPassword.isEmpty()) {
//            Toast.makeText(RegisterScreen.this, "Confirm your password", Toast.LENGTH_SHORT).show();
//        } else if (!thisPassword.equals(thisConfirmPassword)) {
//            Toast.makeText(RegisterScreen.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
//        }
//
//        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = preferences.edit();
//
//
//
////        User+pass key method
//        editor.putString(thisFirstName + thisPassword, thisFirstName + "\n" + thisLastName + "\n" + thisEmail + "\n" + getGender + "\n" + myBirthday);
//
//
//
//        if (!thisFirstName.isEmpty() && !thisLastName.isEmpty() && !thisEmail.isEmpty() && !getGender.isEmpty() && !myBirthday.isEmpty() && !thisPassword.isEmpty() && !thisConfirmPassword.isEmpty()) {
//
//            if (preferences.contains(thisFirstName + thisPassword)){
//                Toast.makeText(RegisterScreen.this, "User exists", Toast.LENGTH_LONG).show();
//            } else {
//                Intent loginScreen = new Intent(RegisterScreen.this, LoginScreen.class);
//                startActivity(loginScreen);
//
//                editor.apply();
//                System.out.println(thisFirstName + "\n" + thisLastName + "\n" + thisEmail + "\n" + myBirthday + "\n" + getGender);
//            }
//        }
//        Toast.makeText(RegisterScreen.this, "User Registered", Toast.LENGTH_LONG).show();
//    }
    //end of println



    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }


}
