package com.example.mohsinhussain.allinoneapp;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class Webview extends AppCompatActivity {

    private  static WebView browser;
    ProgressBar progressBar;
    int progress=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);


        browser = (WebView)findViewById(R.id.webView);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);

        CheckConnetivity check = new CheckConnetivity();
        Boolean conn = check.isNetworkAvailable(this.getApplicationContext());

        if(conn)
        {



            WebSettings webSettings = browser.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
            String url=getIntent().getStringExtra("brandUrl");

            //Toast.makeText(this,url,Toast.LENGTH_LONG).show();
            browser.getSettings().getJavaScriptEnabled();
            browser.getSettings().setBuiltInZoomControls(true);
            browser.loadUrl(url);
//            browser.setWebViewClient(new webclient());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // chromium, enable hardware acceleration
                browser.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            } else {
                // older android version, disable hardware acceleration
                browser.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
            browser.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);


        }
        else
        {
            check.connectivityMessage("Check Network Connection",this);
        }

        // TextView textView= (TextView) findViewById(R.id.textview);


    }
    @Override
    public void onBackPressed() {


            super.onBackPressed();

        if (MainActivity.mInterstitialAd.isLoaded()) {
            MainActivity.mInterstitialAd.show();
        }
        //Toast.makeText(this,"onBackPressed",Toast.LENGTH_SHORT).show();

        finish();
        overridePendingTransition(R.anim.slide_enter,R.anim.slide_exit);

    }


//    public class webclient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            // TODO Auto-generated method stub
//            view.loadUrl(url);
//            return true;
//        }
//
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            // TODO Auto-generated method stub
//            super.onPageStarted(view, url, favicon);
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            // TODO Auto-generated method stub
//            super.onPageFinished(view, url);
//            progressBar.setVisibility(View.GONE);
//        }
//            }

    }







