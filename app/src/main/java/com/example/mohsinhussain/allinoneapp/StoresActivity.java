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
    String[] brandName ;
    Integer[] ImagePath ;
    List list=new ArrayList<String>();
    ArrayAdapter theAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        getIntent();

        Scanner scanner;
        String[] tokens = new String[20];
        InputStream is = getResources().openRawResource(R.raw.shoppingrecords);

        scanner = new Scanner(is);
        brandName = new String[100];
        ImagePath = new Integer[100];

        String[] brandName ={
                "Uzair"


        };



        Integer[] imgid={
                R.drawable.daraz

        };

        int k = 0;

        scanner.close();
        CustomListView myAdapter = new CustomListView(StoresActivity.this, brandName, ImagePath);
        ListView listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }




}
