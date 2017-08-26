package com.e.mohsinhussain.allinoneapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.Scanner;

public class GovermentServicesActivity extends AppCompatActivity {

    TextView name[];
    TextView cgpatext[];
    public static ListView listView1;
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
        setContentView(R.layout.activity_goverment_services);



//        LongOperation op=new LongOperation();
//        op.execute("");
//        op.onPreExecute();


        final String category=getIntent().getStringExtra(MainActivity.CATEGORY);

//layer.searchProfile();


        list.add("CPLC");
        list.add("Electricity Bill");
        list.add("Gas Bill");
        list.add("PTCL");
        list.add("Sim Ownership");
        list.add("Vehicle Verification");







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
        listView1 = (ListView) findViewById(R.id.listView124);
        ArrayAdapter myAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView1.setAdapter(myAdapter);
//        TextView text = (TextView) adapter.getView(0, null, null); // as an example, use the first element in the list
//        Spannable str = (Spannable) text.getText();
//        str.setSpan(new StyleSpan(Typeface.BOLD), 0, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);





        // Log.i("DAL::deleteProfile", DAL.getBrandName.size()+"");

        final DAL layer=new DAL(this,this);


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
