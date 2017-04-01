package com.android.testapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.testapp.R;
import com.android.testapp.view.CustomSwipeableLayout;


public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        CustomSwipeableLayout customSwipeableLayout = (CustomSwipeableLayout) findViewById(R.id.customSwipaebale);
        customSwipeableLayout.setViewGroups(R.layout.first_layout, R.layout.second_layout);
    }
}
