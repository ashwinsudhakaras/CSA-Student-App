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
import com.ashwin_sudhakar.csa_student_app.ui.activities.WebViewActivity;

public class TutorialsFragment extends Fragment {

    private CardView card_Html;
    private CardView card_Css;
    private CardView card_Js;
    private CardView card_Python;

    public TutorialsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_tutorials, container, false);

        initViews(view);
        initClicks();
        return view;
    }

    private void initClicks() {

        card_Html.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), WebViewActivity.class)
                        .putExtra("HEAD","HTML")
                        .putExtra("VIEW","https://www.w3schools.com/html/default.asp")));

        card_Css.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), WebViewActivity.class)
                        .putExtra("HEAD","CSS")
                        .putExtra("VIEW","https://www.w3schools.com/css/default.asp")));

        card_Js.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), WebViewActivity.class)
                        .putExtra("HEAD","JAVASCRIPT")
                        .putExtra("VIEW","https://www.w3schools.com/js/default.asp")));

        card_Python.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), WebViewActivity.class)
                        .putExtra("HEAD","PYTHON")
                        .putExtra("VIEW","https://www.w3schools.com/python/default.asp")));
    }

    private void initViews(View view) {

        card_Html = view.findViewById(R.id.card_html);
        card_Css = view.findViewById(R.id.card_css);
        card_Js = view.findViewById(R.id.card_js);
        card_Python = view.findViewById(R.id.card_python);
    }
}
