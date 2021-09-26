package com.ashwin_sudhakar.csa_student_app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.ashwin_sudhakar.csa_student_app.R;

public class BookEventsActivity extends AppCompatActivity {

    private TextView txt_EventName;
    private TextView txt_Date;
    private TextView txt_Venue;
    private TextView txt_Details;
    private Button btn_bookEvent;
    private String[] GET_DATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_events);
        initViews();
        GET_DATA = getIntent().getStringArrayExtra("EVENT");
        iniActions();
        initClicks();

    }

    private void initClicks() {

        btn_bookEvent.setOnClickListener(v -> {

            String url = GET_DATA[4];
            startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));

        });
    }

    private void iniActions() {

        txt_EventName.setText(GET_DATA[0]);
        txt_Date.setText(GET_DATA[1]);
        txt_Venue.setText(GET_DATA[2]);
        txt_Details.setText(GET_DATA[3]);

    }

    private void initViews() {

        txt_EventName = findViewById(R.id.txt_eventName);
        txt_Date = findViewById(R.id.txt_Date);
        txt_Venue = findViewById(R.id.txt_Venue);
        txt_Details = findViewById(R.id.txt_Details);
        btn_bookEvent = findViewById(R.id.btn_book_event);

    }
}