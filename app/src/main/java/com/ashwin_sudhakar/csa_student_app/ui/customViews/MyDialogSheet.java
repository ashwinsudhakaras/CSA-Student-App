package com.ashwin_sudhakar.csa_student_app.ui.customViews;

import android.app.Activity;
import android.graphics.Typeface;

import com.ashwin_sudhakar.csa_student_app.R;
import com.marcoscg.dialogsheet.DialogSheet;


public class MyDialogSheet extends DialogSheet {
    Activity activity;

    public MyDialogSheet(Activity activity) {
        super(activity);
        this.activity = activity;
        setTitleTypeface(Typeface.createFromAsset(activity.getAssets(), "fonts/roboto_bold.ttf"));
        setMessageTypeface(Typeface.createFromAsset(activity.getAssets(), "fonts/roboto_regular.ttf"));
        setButtonsColorRes(R.color.light_green);
        setRoundedCorners(true);
        setColoredNavigationBar(true);
    }
}
