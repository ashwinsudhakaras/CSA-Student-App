package com.ashwin_sudhakar.csa_student_app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashwin_sudhakar.csa_student_app.R;
import com.ashwin_sudhakar.csa_student_app.helpers.sharedPref.UserSessionManager;
import com.ashwin_sudhakar.csa_student_app.ui.customViews.MyDialogSheet;

public class WebViewActivity extends AppCompatActivity {

    private TextView txt_toolbar_title;
    private ImageView img_back;
    private WebView webView;
    private WebSettings webSettings;
    private String link;
    private String title;
    UserSessionManager userSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        link = getIntent().getStringExtra("VIEW");
        title = getIntent().getStringExtra("HEAD");
        handleUserSession();
        initToolbar();
        initWebView();

    }

    private void handleUserSession() {

        userSessionManager = new UserSessionManager(getApplicationContext());
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {

        webView = findViewById(R.id.webView);
        webView.loadUrl(link);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }

    @SuppressLint("SetTextI18n")
    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView img_logout = findViewById(R.id.img_logout);
        img_back = findViewById(R.id.back_icon);

        img_back.setOnClickListener(v ->
                onBackPressed()
        );
        txt_toolbar_title = findViewById(R.id.title_toolbar);
        txt_toolbar_title.setText(title);
        img_logout.setOnClickListener(v ->
        {

            MyDialogSheet dialog = new MyDialogSheet(WebViewActivity.this);
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