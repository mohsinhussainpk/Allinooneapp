package com.example.mohsinhussain.allinoneapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SplashActivity extends AppCompatActivity {

    public static FirebaseDatabase firebase;
    public static DatabaseReference database;
    public static DatabaseReference table;
    public static StorageReference mStorageRef;
    private static String DB_NAME = "Brand Records";
    private ProgressDialog Dialog;
    static  int timer=6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebase = FirebaseDatabase.getInstance();
        //firebase.setPersistenceEnabled(true);

        database = firebase.getReference(DB_NAME);
//        table = database.child(ENTITY_NAME_PROFILES);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        CheckConnetivity check = new CheckConnetivity();
        Boolean conn = check.isNetworkAvailable(this.getApplicationContext());

        if(conn)

        {
            DAL layer = new DAL(SplashActivity.this, SplashActivity.this);
            layer.sliderDetail();

            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();

                }
            }, timer);





        }

        else

            {

    check.connectivityMessage("Check Network Connection",this);

            }




//        if (!isTaskRoot()
//                && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
//                && getIntent().getAction() != null
//                && getIntent().getAction().equals(Intent.ACTION_MAIN)) {
//
//            finish();
//            return;
//        }

//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        if (preferences.getBoolean("firstTime", true)) {
//            // Show splash screen
//            // Wait a few seconds.
//        } else {
//            // Nothing to do here. Go straight to the second activity.
//        }
//        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(
//                getSupportActivity()).edit();
//        editor.putBoolean("firstTime", false);
//        editor.commit();
//        startActivity(MainActivity.this, ...)
//        finish();



    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Intent intent=new Intent(SplashActivity.this,MainActivity.class);
//        SplashActivity.this.startActivity(intent);
//        SplashActivity.this.finish();
//    }



}
