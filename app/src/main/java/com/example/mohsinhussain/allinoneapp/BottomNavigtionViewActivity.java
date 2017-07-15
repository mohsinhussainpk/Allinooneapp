package com.example.mohsinhussain.allinoneapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class BottomNavigtionViewActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigtionView;
    public static LinearLayout linearLayout;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigtion_view);
        getIntent();
        bottomNavigtionView= (BottomNavigationView) findViewById(R.id.bottomnav);
        linearLayout=(LinearLayout) findViewById(R.id.myfragment);
        bottomNavigtionView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if(item.getItemId()==R.id.nav_share)
                {

                    startActivity(new Intent(getBaseContext(),MainActivity.class));
                    overridePendingTransition(0, 0);

                }
                if(item.getItemId()==R.id.nav_feedback)
                {

                    startActivity(new Intent(getBaseContext(),PrivacyActivity.class));
                    overridePendingTransition(0, 0);

                }


                return false;
            }
        });

    }
}
