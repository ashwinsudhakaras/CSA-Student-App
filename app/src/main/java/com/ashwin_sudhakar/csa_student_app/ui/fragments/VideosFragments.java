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

public class VideosFragments extends Fragment {

    private CardView card_Html;
    private CardView card_Css;
    private CardView card_Js;
    private CardView card_Python;

    public VideosFragments() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos, container, false);

        initViews(view);
        initClicks();
        return view;
    }

    private void initClicks() {

        card_Html.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), WebViewActivity.class)
                        .putExtra("HEAD", "HTML")
                        .putExtra("VIEW", "https://youtu.be/88PXJAA6szs")));

        card_Css.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), WebViewActivity.class)
                        .putExtra("HEAD", "CSS")
                        .putExtra("VIEW", "https://youtu.be/3_9znKVNe5g")));

        card_Js.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), WebViewActivity.class)
                        .putExtra("HEAD", "JAVASCRIPT")
                        .putExtra("VIEW", "https://youtu.be/o1IaduQICO0")));

        card_Python.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), WebViewActivity.class)
                        .putExtra("HEAD", "PYTHON")
                        .putExtra("VIEW", "https://youtu.be/WGJJIrtnfpk")));
    }

    private void initViews(View view) {

        card_Html = view.findViewById(R.id.card_html);
        card_Css = view.findViewById(R.id.card_css);
        card_Js = view.findViewById(R.id.card_js);
        card_Python = view.findViewById(R.id.card_python);
    }
}