package com.e.panoplytek.yotap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TravelActivity extends AppCompatActivity {


    TextView name[];
    TextView cgpatext[];
    public static ListView listView2;
    public static String category = "";
    public static final String CATEGORY = "category";

    int click=0;
    ArrayList<String> list=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);


        final String category=getIntent().getStringExtra(MainActivity.CATEGORY);

//layer.searchProfile();


        list.add("AIR");
        list.add("RAILWAY");
        list.add("ROAD");

        listView2 = (ListView) findViewById(R.id.listView124);
        ArrayAdapter myAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView2.setAdapter(myAdapter);
//        TextView text = (TextView) adapter.getView(0, null, null); // as an example, use the first element in the list
//        Spannable str = (Spannable) text.getText();
//        str.setSpan(new StyleSpan(Typeface.BOLD), 0, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);





        // Log.i("DAL::deleteProfile", DAL.getBrandName.size()+"");

        final DAL layer=new DAL(this,this);


        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

