package com.example.mohsinhussain.allinoneapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoresActivity extends AppCompatActivity {


    TextView name[];
    TextView cgpatext[];
    String[] brandName={
            "DARAZ.PK",
            "YAYVO.PK"

    };
    int[] imgId={

            R.drawable.daraz,
            R.drawable.yayvo

    };

            Scanner scanner;

    List list=new ArrayList<String>();
    ArrayAdapter theAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        getIntent();

        //this is for taking the name and image by using file
//        String[] tokens = new String[20];
//        InputStream is = getResources().openRawResource(R.raw.shoppingrecords);

//        scanner = new Scanner(is);
//        brandName = new String[100];
//        imgId = new int[100];
//
//        int k = 0;
//        while(scanner.hasNext()){
//            tokens = scanner.nextLine().split(";");
//            brandName[k]=tokens[0];
//
//
//            k++;
//
//
//
//        }



//        scanner.close();



        ListView listView=(ListView)findViewById(R.id.listView);

        CustomListView myAdapter = new CustomListView(StoresActivity.this, brandName, imgId);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent = new Intent(StoresActivity.this, Webview.class);
                mIntent.putExtra("brandName", brandName[i]);
                startActivity(mIntent);


            }
        });
    }




}
