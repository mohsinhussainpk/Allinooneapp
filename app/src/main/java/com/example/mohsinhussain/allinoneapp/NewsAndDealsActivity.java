package com.example.mohsinhussain.allinoneapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Scanner;

public class NewsAndDealsActivity extends AppCompatActivity {


    TextView name[];
    TextView cgpatext[];
    public static ListView listView1;
    public static String category = "";
    public static final String CATEGORY = "category";

    int click=0;
    ArrayList<String>list=new ArrayList<String>();
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
        setContentView(R.layout.activity_news_and_deals);



//        LongOperation op=new LongOperation();
//        op.execute("");
//        op.onPreExecute();


        final String category=getIntent().getStringExtra(MainActivity.CATEGORY);
         DAL layer=new DAL();

//layer.searchProfile();

if(category.equalsIgnoreCase("News"))
{

    list.add("Tech News");
    list.add("English News");
    list.add("Urdu News");

}




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

//        CustomListView myAdapter = null;
//
//        try {
//            Thread.sleep(400);
//            myAdapter = new CustomListView(NewsAndDealsActivity.this, DAL.getBrandName, DAL.getImageUrl);
//
//
//
//
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
 listView1 = (ListView) findViewById(R.id.listView123);
   ArrayAdapter myAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView1.setAdapter(myAdapter);





        // Log.i("DAL::deleteProfile", DAL.getBrandName.size()+"");



        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               MainActivity.layer.searchProfile(list.get(i));

                Intent mIntent = new Intent(NewsAndDealsActivity.this, StoresActivity.class);


                mIntent.putExtra("category", list.get(i));

              //  mIntent.putExtra("brandUrl", DAL.getBrandUrl.get(i));

                startActivity(mIntent);




//                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });


    }
}
