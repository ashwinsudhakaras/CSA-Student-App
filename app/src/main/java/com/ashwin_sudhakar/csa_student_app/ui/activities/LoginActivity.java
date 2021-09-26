package com.ashwin_sudhakar.csa_student_app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ashwin_sudhakar.csa_student_app.R;
import com.ashwin_sudhakar.csa_student_app.helpers.Database.DbHelper;
import com.ashwin_sudhakar.csa_student_app.helpers.sharedPref.UserSessionManager;
import com.ashwin_sudhakar.csa_student_app.models.UserSessionDataModel;
import com.ashwin_sudhakar.csa_student_app.ui.customViews.MyDialogSheet;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private EditText edt_email;
    private EditText edt_password;
    private TextView txt_signup;
    private DbHelper myDb;

    UserSessionManager userSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        initViews();
        myDb = new DbHelper(this);
        intClicks();
        handleUserSession();

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (!isConnected()) {
            MyDialogSheet dialog = new MyDialogSheet(LoginActivity.this);
            dialog.setTitle("Connection Error");
            dialog.setMessage("Your device is not connected to internet. Please try again with an active internet connection.");
            dialog.setCancelable(false);
            dialog.setPositiveButton("Go to Settings", v1 -> {
                finishAffinity();
                startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));

            });
            dialog.setNegativeButton("Cancel", v1 -> finishAffinity());
            dialog.show();
        }
    }

    private void handleUserSession() {

        userSessionManager = new UserSessionManager(getApplicationContext());
        userSessionManager.checkLogin();
    }

    public boolean isConnected() {
        boolean connected;
        try {
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return false;
    }

    private void intClicks() {

        btn_login.setOnClickListener(v ->
                {
                    if (validated()) {

                        UserSessionDataModel userSessionDataModel = new UserSessionDataModel(
                                edt_email.getText().toString());

                        userSessionManager.createUserLoginSession(userSessionDataModel);
                        userSessionManager.checkLogin();

                        startActivity(new Intent(this, DashboardActivity.class));
                    }
                }
        );


        txt_signup.setOnClickListener(v ->
                startActivity(new Intent(this, SignUpActivity.class))
        );
    }

    private boolean validated() {

        if (edt_email.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter a Email", Toast.LENGTH_LONG).show();
            return false;
        }
        if (edt_password.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter a Password", Toast.LENGTH_LONG).show();
            return false;
        }
        boolean userCheckResult = myDb.checkExistingEmailPassword(edt_email.getText().toString().trim(), edt_password.getText().toString());
        if (userCheckResult) {
            Toast.makeText(getApplicationContext(), "Login Success " + ("\ud83d\ude01"), Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void initViews() {

        btn_login = findViewById(R.id.btn_login);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        txt_signup = findViewById(R.id.txt_signup);
    }


}