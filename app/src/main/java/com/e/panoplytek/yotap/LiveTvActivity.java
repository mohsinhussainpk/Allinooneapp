package com.e.panoplytek.yotap;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class LiveTvActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tv);


        youTubePlayerView= (YouTubePlayerView) findViewById(R.id.youtubeplayerview);
         url=getIntent().getStringExtra("brandUrl");

        youTubePlayerView.initialize("AIzaSyA_L3fFNa5nqT9P49UW7Cx0P-Fjk9hWcuQ", this);



    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        //boolean wasRestored;
        if (!b) {
            youTubePlayer.cueVideo(url);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();

    }
    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }
        @Override
        public void onPaused() {
        }
        @Override
        public void onPlaying() {
        }
        @Override
        public void onSeekTo(int arg0) {
        }
        @Override
        public void onStopped() {
        }
    };
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }
        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }
        @Override
        public void onLoaded(String arg0) {
        }
        @Override
        public void onLoading() {
        }
        @Override
        public void onVideoEnded() {
        }
        @Override
        public void onVideoStarted() {
        }
    };
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


}

