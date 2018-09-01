package com.hhwit.liaisonshome;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    NavigationIconView mNavigationDiscover;
    NavigationIconView mNavigationContacts;
    NavigationIconView mNavigationTalks;
    NavigationIconView mNavigationMe;
    NavigationIconView mCurrentNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDiscover = findViewById(R.id.navigationIconView);
        mNavigationDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentNavigation.setFirstColor();
                mCurrentNavigation = mNavigationDiscover;
                mCurrentNavigation.setSecondColor();
            }
        });

        mNavigationContacts = findViewById(R.id.navigationIconView1);
        mNavigationContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentNavigation.setFirstColor();
                mCurrentNavigation = mNavigationContacts;
                mCurrentNavigation.setSecondColor();
            }
        });

        mNavigationTalks = findViewById(R.id.navigationIconView2);
        mNavigationTalks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentNavigation.setFirstColor();
                mCurrentNavigation = mNavigationTalks;
                mCurrentNavigation.setSecondColor();
            }
        });

        mNavigationMe = findViewById(R.id.navigationIconView3);
        mNavigationMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentNavigation.setFirstColor();
                mCurrentNavigation = mNavigationMe;
                mCurrentNavigation.setSecondColor();
            }
        });

        mCurrentNavigation = mNavigationDiscover;
        mCurrentNavigation.setSecondColor();
    }
}
