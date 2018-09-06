package com.hhwit.liaisonshome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class MainActivity extends FragmentActivity {
    NavigationIconView mNavigationDiscover;
    NavigationIconView mNavigationContacts;
    NavigationIconView mNavigationTalks;
    NavigationIconView mNavigationMe;
    NavigationIconView mCurrentNavigation;

    DiscoverFragment mDiscoverFragment;
    ContactsFragment mContactsFragment;
    TalksFragment mTalksFragment;
    MeFragment mMeFragment;
    Fragment mCurrentFragment;

    private void showNavigationIcon(NavigationIconView v) {
        mCurrentNavigation.setFirstColor();
        mCurrentNavigation = v;
        mCurrentNavigation.setSecondColor();
    }

    private void showFragment(Fragment f) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(mCurrentFragment);
        mCurrentFragment = f;
        transaction.show(mCurrentFragment).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        mDiscoverFragment = new DiscoverFragment();
        transaction.add(R.id.main_fragment, mDiscoverFragment);
        transaction.show(mDiscoverFragment);

        mContactsFragment = new ContactsFragment();
        transaction.add(R.id.main_fragment, mContactsFragment);
        transaction.hide(mContactsFragment);

        mTalksFragment = new TalksFragment();
        transaction.add(R.id.main_fragment, mTalksFragment);
        transaction.hide(mTalksFragment);

        mMeFragment = new MeFragment();
        transaction.add(R.id.main_fragment, mMeFragment);
        transaction.hide(mMeFragment);

        transaction.commit();
        mCurrentFragment = mDiscoverFragment;

        mNavigationDiscover = findViewById(R.id.navigationIconView);
        mNavigationDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNavigationIcon((NavigationIconView)v);
                showFragment(mDiscoverFragment);
            }
        });

        mNavigationContacts = findViewById(R.id.navigationIconView1);
        mNavigationContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNavigationIcon((NavigationIconView)v);
                showFragment(mContactsFragment);

            }
        });

        mNavigationTalks = findViewById(R.id.navigationIconView2);
        mNavigationTalks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNavigationIcon((NavigationIconView)v);
                showFragment(mTalksFragment);
            }
        });

        mNavigationMe = findViewById(R.id.navigationIconView3);
        mNavigationMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNavigationIcon((NavigationIconView)v);
                showFragment(mMeFragment);

                startActivity(new Intent(MainActivity.this,
                        RegisterActivity.class));
            }
        });

        mCurrentNavigation = mNavigationDiscover;
        mCurrentNavigation.setSecondColor();


    }

}
