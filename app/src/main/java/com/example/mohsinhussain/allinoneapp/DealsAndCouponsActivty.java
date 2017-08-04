package com.example.mohsinhussain.allinoneapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Scanner;

public class DealsAndCouponsActivty extends AppCompatActivity {


    TextView name[];
    TextView cgpatext[];
    public static ListView listViewDealsAndCoupons;
    public static String category = "";
    public static final String CATEGORY = "category";

    int click=0;
    ArrayList<String> list=new ArrayList<String>();
    //calling Dal object to fetch the brand name which is fetched from firebase in Dal layer






//    String[] brand={
//
//            "DARAZ.PK",
//            "YAYVO.PK"
//    };

    //  ArrayList<String> brandName = new ArrayList<String>();
//    DAL layer1 = new DAL(this, this);
//    Integer[] imgId;
//    String[] brandName;


    Scanner scanner;


    ArrayAdapter theAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals_and_coupons_activty);
        final String category=getIntent().getStringExtra(MainActivity.CATEGORY);

//layer.searchProfile();


        list.add("Daily Deals");
        list.add("Deals and Coupons");


        listViewDealsAndCoupons = (ListView) findViewById(R.id.listViewdealsAndCoupons);
        ArrayAdapter myAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listViewDealsAndCoupons.setAdapter(myAdapter);

        final DAL layer=new DAL(this,this);


        listViewDealsAndCoupons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                layer.searchProfile(list.get(i));


            }
        });

    }
    @Override
    public void onBackPressed() {


//        super.onBackPressed();
//        Intent intent= new Intent(this,MainActivity.class);
//        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_enter,R.anim.slide_exit);
    }
}


