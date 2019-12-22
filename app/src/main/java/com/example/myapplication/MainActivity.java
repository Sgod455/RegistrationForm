package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    DatePickerDialog pickerDialog;
    EditText getFirstName, getLastName, getEmail, getBirthday, getPassword, getConfirmPassword;
    RadioButton chosenGender;
    RadioGroup genderChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get Birthday field and make it open date picker dialog
        getBirthday = (EditText) findViewById(R.id.birthDate);
        getBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                pickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        getBirthday.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                }, year, month, day);
                pickerDialog.show();
            }
        }); //end of datepicker

    }

    //printing data in console with Register button clicked
    public void printData(View view) {
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

        String getGender = chosenGender.getText().toString();

        if (thisFirstName.isEmpty()){
            Toast.makeText(MainActivity.this, "Enter your first name", Toast.LENGTH_SHORT).show();
        } else

        if (thisLastName.isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter your last name", Toast.LENGTH_SHORT).show();
        } else

        if (thisEmail.isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter your email", Toast.LENGTH_SHORT).show();
        } else if (!isValidEmail(thisEmail)) {
            Toast.makeText(MainActivity.this, "Enter a valid e-mail", Toast.LENGTH_SHORT).show();
        } else

        if (myBirthday.isEmpty()){
            Toast.makeText(MainActivity.this, "Pick your birthday", Toast.LENGTH_SHORT).show();
        } else

        if (thisPassword.isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter your a password", Toast.LENGTH_SHORT).show();
        } else

        if (thisConfirmPassword.isEmpty()) {
            Toast.makeText(MainActivity.this, "Confirm your password", Toast.LENGTH_SHORT).show();
        } else if (!thisPassword.equals(thisConfirmPassword)) {
            Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }

        System.out.println( thisFirstName + "\n" + thisLastName + "\n" + thisEmail + "\n" + myBirthday + "\n" + getGender);
    }//end of println


    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

}
