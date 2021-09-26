package com.ashwin_sudhakar.csa_student_app.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.ashwin_sudhakar.csa_student_app.R;
import com.ashwin_sudhakar.csa_student_app.ui.activities.BookEventsActivity;

public class EventsFragments extends Fragment {

    private CardView card_Event1;
    private CardView card_Event2;
    private CardView card_Event3;
    private CardView card_Event4;
    private CardView card_Event5;
    private CardView card_Event6;

    public EventsFragments() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        initViews(view);
        initClicks();
        return view;
    }

    private void initClicks() {

        card_Event1.setOnClickListener(view -> {

            String[] events = new String[5];
            events[0] = "GDG Kozhikode";
            events[1] = "Saturday, June 23, 2018";
            events[2] = "UL CyberPark";
            events[3] = "Hangout with local developers and experience Google I/O together" +
                    "Google I/O Extended events help developers from around the world take part in the Google I/O experience.";
            events[4] = "https://www.meetup.com/GDG-Kozhikode/events/248914711/";

            startActivity(new Intent(getActivity(), BookEventsActivity.class)
                    .putExtra("EVENT", events));
        });

        card_Event2.setOnClickListener(v ->
        {
            String[] events = new String[5];
            events[0] = "TechMeet360";
            events[1] = "Saturday, July 7, 2018";
            events[2] = "Hubfly\n" +
                    "Veejay Centre Race Course Road · Coimbatore";
            events[3] = "TechMeet360 is a monthly technology meetup event in Coimbatore for technology enthusiasts who are keen to make a mark in the technology arena. With TechMeet360, there are endless possibilities to explore different technologies and learn from the industry experts. Join us for these events and build your technology expertise.";
            events[4] = "https://www.meetup.com/TechMeet360-A-BizTalk360-Community-Initiative/";
            startActivity(new Intent(getActivity(), BookEventsActivity.class)
                    .putExtra("EVENT", events));
        });

        card_Event3.setOnClickListener(v ->
        {
            String[] events = new String[5];
            events[0] = "Geek night Coimbatore";
            events[1] = "Thursday, June 21, 2018";
            events[2] = "ThoughtWorks Technologies\n" +
                    "KCT Tech Park, First Floor Thudiyalur Rd, Saravanampatty · Coimbatore";
            events[3] = "Geek Night Coimbatore is a monthly event to promote sharing of technical knowledge and increase collaboration between geeks in Coimbatore. It is organized by a passionate group of programmers and sponsored by ThoughtWorks.";
            events[4] = "https://www.meetup.com/Geeknight-Coimbatore/";
            startActivity(new Intent(getActivity(), BookEventsActivity.class)
                    .putExtra("EVENT", events));
        });

        card_Event4.setOnClickListener(v ->
        {
            String[] events = new String[5];
            events[0] = "Python - Cochin";
            events[1] = "Saturday, July 14, 2018\n";
            events[2] = "Kerala Startup Mission, Kerala Technology Innovation Zone (KTIZ)\n" +
                    "Kalamasseri, near Kakkanad · Cochin\n";
            events[3] = "KochiPython is where Pythonistas of Kochi come together and share what we love the most, experiences.";
            events[4] = "https://www.meetup.com/KochiPython/events/xjqtrpyxkbsb/";
            startActivity(new Intent(getActivity(), BookEventsActivity.class)
                    .putExtra("EVENT", events));
        });

        card_Event5.setOnClickListener(v ->
        {
            String[] events = new String[5];
            events[0] = "Kerala Microsoft Technology Meetup";
            events[1] = "Saturday, June 23, 2018";
            events[2] = "ORION INDIA SYSTEMS PVT. LTD" +
                    "2nd FLOOR, LULU CYBER TOWER - 1 · INFOPARK, KOCHI";
            events[3] = "Ionic, React Native, Azure";
            events[4] = "https://www.meetup.com/KMUG-MEETUP/events/251745794/";
            startActivity(new Intent(getActivity(), BookEventsActivity.class)
                    .putExtra("EVENT", events));
        });

        card_Event6.setOnClickListener(v ->
        {
            String[] events = new String[5];
            events[0] = "Introduction To Software Testing";
            events[1] = "Saturday, June 23, 2018";
            events[2] = "Mobile 10x, Calicut \n";
            events[3] = "Software testing is an emerging field.In this meet-up We will discuss What is Software Testing, Types of Testing, Test case preparation etc. Please contact to the below number for more details.";
            events[4] = "https://www.meetup.com/Data-Science-Workshops/events/251548290/";
            startActivity(new Intent(getActivity(), BookEventsActivity.class)
                    .putExtra("EVENT", events));
        });
    }

    private void initViews(View view) {

        card_Event1 = view.findViewById(R.id.card_event1);
        card_Event2 = view.findViewById(R.id.card_event2);
        card_Event3 = view.findViewById(R.id.card_event3);
        card_Event4 = view.findViewById(R.id.card_event4);
        card_Event5 = view.findViewById(R.id.card_event5);
        card_Event6 = view.findViewById(R.id.card_event6);

    }
}