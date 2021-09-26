package com.ashwin_sudhakar.csa_student_app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ashwin_sudhakar.csa_student_app.R;
import com.ashwin_sudhakar.csa_student_app.helpers.Database.DbHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private Spinner spinner_District;
    private EditText edt_Name;
    private EditText edt_Email;
    private EditText edt_Phone_Number;
    private EditText edt_Password;
    private EditText edt_confirmPassword;
    private Button btn_SignUp;
    private DbHelper myDb;

    private final List<String> lDistricts = new ArrayList<>();
    private final String[] sDistricts = {"Select District", "Kasaragod", "Kannur", "Wayanad", "Kozhikkode", "Malappuram", "Thrissur",
            "Palakkad", "Ernakulam", "Alappuzha", "Idukki", "Kottayam", "Pathanamthitta", "Kollam", "Thiruvananthapuram"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();
        myDb = new DbHelper(this);
        initClicks();

        lDistricts.addAll(Arrays.asList(sDistricts));
        ArrayAdapter<String> obj = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, lDistricts);
        spinner_District.setAdapter(obj);

    }

    private void initClicks() {

        btn_SignUp.setOnClickListener(v ->
        {
            if (validated()) {

                startActivity(new Intent(this, LoginActivity.class));
            }
        });
    }

    private boolean validated() {

        if (edt_Name.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter a Name", Toast.LENGTH_LONG).show();
            return false;
        }
        if (edt_Email.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter a Email", Toast.LENGTH_LONG).show();
            return false;
        }
        String EMAIL_ADDRESS = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!edt_Email.getText().toString().trim().matches(EMAIL_ADDRESS)) {
            Toast.makeText(getApplicationContext(), "Please Enter a Valid Email", Toast.LENGTH_LONG).show();
            return false;
        }
        if (edt_Phone_Number.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter a Phone Number", Toast.LENGTH_LONG).show();
            return false;
        }
        if (edt_Phone_Number.getText().toString().length() != 10) {
            Toast.makeText(getApplicationContext(), "Please Enter a Valid Phone Number", Toast.LENGTH_LONG).show();
            return false;
        }
        if (edt_Password.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter a Password", Toast.LENGTH_LONG).show();
            return false;
        }
        if (edt_Password.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), "Password Should be Minimum 6 Characters", Toast.LENGTH_LONG).show();
            return false;
        }
        if (edt_confirmPassword.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please Confirm Your Password", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!edt_Password.getText().toString().trim().equals(edt_confirmPassword.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "The Confirmation Password does not match", Toast.LENGTH_LONG).show();
            return false;
        }
        if (spinner_District.getSelectedItem().toString().equals("Select District")) {
            Toast.makeText(getApplicationContext(), "Please Select a District", Toast.LENGTH_LONG).show();
            return false;
        }
        boolean userCheckResult = myDb.checkExistingEmail(edt_Email.getText().toString().trim());

        if (userCheckResult) {
            Toast.makeText(getApplicationContext(), "User already Exists!!\n Please Login", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            boolean signupResult = myDb.insert(edt_Email.getText().toString(), edt_Password.getText().toString());
            if (signupResult) {
                Toast.makeText(getApplicationContext(), "Sign Up Success " + ("\ud83d\ude01"), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Sign Up Failed", Toast.LENGTH_SHORT).show();
            }
        }

        return true;
    }

    private void initViews() {

        edt_Name = findViewById(R.id.edt_Name);
        edt_Email = findViewById(R.id.edt_email);
        edt_Phone_Number = findViewById(R.id.edt_phone_number);
        edt_Password = findViewById(R.id.edt_password);
        edt_confirmPassword = findViewById(R.id.edt_confirm_password);
        spinner_District = findViewById(R.id.spinner_district);
        btn_SignUp = findViewById(R.id.btn_sign_up);

    }

}