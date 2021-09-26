package com.ashwin_sudhakar.csa_student_app.ui.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashwin_sudhakar.csa_student_app.R;
import com.ashwin_sudhakar.csa_student_app.helpers.sharedPref.UserSessionManager;
import com.ashwin_sudhakar.csa_student_app.ui.customViews.MyDialogSheet;
import com.ashwin_sudhakar.csa_student_app.ui.fragments.EventsFragments;
import com.ashwin_sudhakar.csa_student_app.ui.fragments.TutorialsFragment;
import com.ashwin_sudhakar.csa_student_app.ui.fragments.VideosFragments;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    private TextView txt_toolbar_title;
    UserSessionManager userSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        handleUserSession();
        initToolbar();
        initBottomNavigation();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!isConnected()) {
            MyDialogSheet dialog = new MyDialogSheet(DashboardActivity.this);
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

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    private void initBottomNavigation() {

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TutorialsFragment()).commit();
        navigationView.setSelectedItemId(R.id.tutorial);
        navigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.tutorial:
                    txt_toolbar_title.setText("Tutorials");
                    fragment = new TutorialsFragment();
                    break;

                case R.id.video:
                    txt_toolbar_title.setText("Videos");
                    fragment = new VideosFragments();
                    break;
                case R.id.events:
                    txt_toolbar_title.setText("Events");
                    fragment = new EventsFragments();
                    break;
            }
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
            return true;
        });
    }

    @SuppressLint("SetTextI18n")
    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView img_logout = findViewById(R.id.img_logout);
        txt_toolbar_title = findViewById(R.id.title_toolbar);
        txt_toolbar_title.setText("Tutorials");
        img_logout.setOnClickListener(v ->
        {

            MyDialogSheet dialog = new MyDialogSheet(DashboardActivity.this);
            dialog.setTitle("Logout");
            dialog.setMessage("Are you sure logout?");
            dialog.setPositiveButton("Yes", v1 -> {

                userSessionManager.logoutUser();
                finishAffinity();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

            });
            dialog.setNegativeButton("Cancel", null);
            dialog.show();

        });

    }

}