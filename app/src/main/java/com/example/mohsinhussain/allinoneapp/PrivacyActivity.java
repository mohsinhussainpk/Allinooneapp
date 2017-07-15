package com.example.mohsinhussain.allinoneapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PrivacyActivity extends BottomNavigtionViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_privacy);
        LinearLayout linearLayout=(LinearLayout) findViewById(R.id.myfragment);
        View wizard = getLayoutInflater().inflate(R.layout.activity_privacy, null);
        linearLayout.addView(wizard);

        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = getApplicationContext().getResources().openRawResource(R.raw.privacy);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        TextView textView = (TextView) findViewById(R.id.privacyTextView);
        textView.setText(stringBuilder.toString());
    }
}
