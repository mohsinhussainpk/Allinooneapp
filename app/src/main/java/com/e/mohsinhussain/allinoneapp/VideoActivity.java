package com.e.mohsinhussain.allinoneapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        //to use RecycleView, you need a layout manager. default is LinearLayoutManager
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        YoutubeCustomAdapter adapter=new YoutubeCustomAdapter(VideoActivity.this,DAL.getVideSrc,DAL.getVideTitlte);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {


//        super.onBackPressed();
//        Intent intent= new Intent(this,MainActivity.class);
//        startActivity(intent);


        super.onBackPressed();

        try {
            if (MainActivity.mInterstitialAd.isLoaded()) {
                MainActivity.mInterstitialAd.show();
            }
            //Toast.makeText(this,"onBackPressed",Toast.LENGTH_SHORT).show();

            finish();
            overridePendingTransition(R.anim.slide_enter,R.anim.slide_exit);

        }
        catch (Throwable e)
        {
            e.printStackTrace();

        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();


    }

}
