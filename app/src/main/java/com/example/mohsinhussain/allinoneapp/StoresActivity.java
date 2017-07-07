package com.example.mohsinhussain.allinoneapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.example.mohsinhussain.allinoneapp.R.id.listView;

public class StoresActivity extends AppCompatActivity {


    TextView name[];
    TextView cgpatext[];
    public static ListView listView;
    int click=0;
    DAL layer = new DAL();
    //calling Dal object to fetch the brand name which is fetched from firebase in Dal layer


    Integer[] imgId = {

      //      R.drawable.daraz,
        //    R.drawable.yayvo

    };




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
        setContentView(R.layout.activity_stores);



//        LongOperation op=new LongOperation();
//        op.execute("");
//        op.onPreExecute();


        String category=getIntent().getStringExtra(MainActivity.CATEGORY);

//layer.searchProfile();






        //  Toast.makeText(this,category,Toast.LENGTH_SHORT).show();
        //layer1 = new DAL(this, this);
        //layer1.searchProfile();

        //this is for taking the name and image src by using file
//        String[] tokens = new String[100];
//        InputStream is = getResources().openRawResource(R.raw.shoppingrecords);

//        scanner = new Scanner(is);
//        brand = new String[10];
//        imgId = new Integer[10];
//
//        int k = 0;
////        while (scanner.hasNext()) {
//            tokens = scanner.nextLine().split(";");
//            brand[k] = tokens[0];
//            // imgId[k]= Integer.valueOf(tokens[1]);
//
////
////
////            k++;
////
////
////
////        }
//        }

        //scanner.close();

        CustomListView myAdapter = null;

        try {
                    Thread.sleep(400);
                           myAdapter = new CustomListView(StoresActivity.this, DAL.getBrandName, DAL.getImageUrl);




                       } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(myAdapter);

            click++;



        Log.i("DAL::deleteProfile", DAL.getBrandName.size()+"");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent = new Intent(StoresActivity.this, Webview.class);
                mIntent.putExtra("brandUrl", DAL.getBrandUrl.get(i));
                startActivity(mIntent);


            }
        });


    }




//    private void notifyAdapter(final CustomListView adapter, final ListView listView)  {
//        this.runOnUiThread(new Runnable()  {
//            public void run() {
//
//                           }
//        });
//    }

    private class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            for(int i=0;i<5;i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {


        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

}