package com.example.mohsinhussain.allinoneapp;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.android.gms.ads.MobileAds;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends BottomNavigtionViewActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //ViewPager viewPager;
    //CustomSwipeAdapter customSwipeAdapter;

    public static final String CATEGORY = "category";
    private static String DB_NAME = "Brand Records";

    private  ViewPager mPager;
    private static int currentPage = 0;
    private static int currentImage =-1;
   // private static final Integer[] XMEN= {R.drawable.a,R.drawable.armchair,R.drawable.taxi,R.drawable.b,R.drawable.blackbackground};


    private ArrayList<String> XMENArray = DAL.sliderImage;


    int click = 0;
    public static String category = "";
    TextView textviewShoppiing;

    TextView textviewFood ;
    TextView textViewCarHiring;
    TextView textViewClassified;
    TextView textViewJobSite;
    TextView textViewNews;
    TextView textViewWallets;
    TextView textViewOffers;
    TextView textViewBooking;
    TextView textViewGrocery;
    TextView textViewCarSite;
    TextView textViewRealState;
    TextView textViewPriceCompany;
    TextView textViewEducation;

    TextView textViewTravelling;
    TextView textViewHomeServices;
    TextView textViewMedicine;
    TextView textViewFinanace;
    TextView textViewFurniture;
    TextView textViewPrinting;
    TextView textViewFlower;
    TextView textViewDeal;
    TextView textViewKids;
    TextView textViewWomen;
    TextView textViewDelivery;
    TextView textViewEbuzz;
    TextView textViewMusic;
    TextView textViewLegal;
    static boolean  flag=false;


    GridView grid;
    //static String category = "";
    public static FirebaseDatabase firebase;
    public static DatabaseReference database;
    public static DatabaseReference table;
    public static StorageReference mStorageRef;

    String[] web = {

            "News",
            "Offers",
            "Shopping",
            "Food",
            "Carhiring",
            "Classified Sites",
            "Job Site",
            "Mobile Wallets",
            "Booking",
            "Grocery",
            "Car Site",
            "Real State",
            "Price Company",
            "Education",
            "Travelling",
            "Home Service",
            "Medicines",
            "Finance",
            "Furniture",
            "Printing",
            "Gifts and Flowers",
            "Deals and Coupons",
            "Baby and Kids",
            "Women",
            "Delivery Services",
            "Entertainment",
            "Music",






    } ;
    int[] imageId = {

            R.drawable.newspaper,
            R.drawable.technews,
            R.drawable.shoppingcart,
            R.drawable.food,
            R.drawable.carbuy,
            R.drawable.onlinejobsearchsymbol,
            R.drawable.onlinejobsearchsymbol,
            R.drawable.wallet,
            R.drawable.deals,
            R.drawable.groceriesbag,
            R.drawable.distancetotravelbetweentwopoints,
            R.drawable.realestate,
            R.drawable.deals,
            R.drawable.education,
            R.drawable.taxi,
            R.drawable.homeservices,
            R.drawable.medicines,
            R.drawable.finance,
            R.drawable.armchair,
            R.drawable.printbutton,
            R.drawable.gift,
            R.drawable.deals,
            R.drawable.babydummywithbearheadsilhouette,
            R.drawable.dress,
            R.drawable.gift,
            R.drawable.ebuzz,
            R.drawable.music,









    };

    public static DAL layer;
    ImageButton GridImageButton;
  //  static DAL layer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for making bottom bar

//        LinearLayout linearLayout=(LinearLayout) findViewById(R.id.myfragment);
//        View wizard = getLayoutInflater().inflate(R.layout.activity_main, null);
//        linearLayout.addView(wizard);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
      setContentView(R.layout.activity_main);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        CustomGrid adapter = new CustomGrid(this, web, imageId);
        grid=(GridView)findViewById(R.id.customgrid);
        grid.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

     // MyDrawerLayout drawer = (MyDrawerLayout)findViewById(R.id.drawer_layout);
//        drawer.openDrawer(linearLayout);

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



//        initializeConnection();

        layer=new DAL(this,this);
        //layer.sliderDetail();



        init();

        // layer.sliderDetail();

        Log.i("count", IntroActivity.count + " "  );

        GridImageButton= (ImageButton) findViewById(R.id.os_images);

//        AdView adView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder() .setRequestAgent("android_studio:ad_template").build();
//        adView.loadAd(adRequest);


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder() .build();

        mAdView.loadAd(adRequest);



//        ImageButton imageButton= (ImageButton) findViewById(R.id.shoppingImage);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                category12="Shopping";
//            }
//        });


       //layer.addProfile();
        //layer.addImages();
//        layer.retrieve();
//        //layer.getBrandName();
//        layer.printData();
//layer.searchProfile();

        //layer.printData();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_feedback) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","panoplytek@gmail.com", null));

            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
//            Intent myIntent = new Intent(Intent.ACTION_SEND);
//            PackageManager pm = getPackageManager();
//            Intent tempIntent = new Intent(Intent.ACTION_SEND);
//            tempIntent.setType("*/*");
//            List<ResolveInfo> resInfo = pm.queryIntentActivities(tempIntent, 0);
//            for (int i = 0; i < resInfo.size(); i++) {
//                ResolveInfo ri = resInfo.get(i);
//                if (ri.activityInfo.packageName.contains("android.gm")) {
//                    myIntent.setComponent(new ComponentName(ri.activityInfo.packageName, ri.activityInfo.name));
//
//                    myIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"panoplytek@gmail.com"});
//                    myIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("panoplytek@gmail.com"));
//                    myIntent.setAction(Intent.ACTION_SEND);
//                }
//            }
//            startActivity(myIntent);

//
//            final Intent intent = new Intent(android.content.Intent.ACTION_SEND));
//            intent.setType("text/plain");
//            final PackageManager pm = getPackageManager();
//            final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
//            ResolveInfo best = null;
//            for (final ResolveInfo info : matches)
//                if (info.activityInfo.packageName.endsWith(".gm") ||
//                        info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
//            if (best != null)
//                intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
//            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=Orion.Soft")));




        } else if (id == R.id.nav_slideshow) {

            Intent intent=new Intent(getApplicationContext(),TermsAndConditionActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_manage) {
            Intent intent=new Intent(getApplicationContext(),PrivacyActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_share) {
//            Intent myIntent=new Intent(Intent.ACTION_SEND);
//            myIntent.setType("Text/Plan");
//            String shareBody="Your body here";
//            String shareSubject="Your Subject here";
//            myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
//            myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
//            startActivity(Intent.createChooser(myIntent,"Share"));

            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String sAux = "\nLet me recommend you this application\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=Orion.Soft \n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void itemClicked(int position) {




        if(web[position]== "News")
        {
            Intent intent = new Intent(this, NewsAndDealsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


        }
        else
        {

            layer.searchProfile(web[position]);



        }



    }
    private void init() {


        SliderAdapter sliderAdapter=new SliderAdapter(MainActivity.this,XMENArray);
        mPager = (ViewPager) findViewById(R.id.pager);

        mPager.setAdapter(sliderAdapter);
        mPager.beginFakeDrag();
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        final float[] startXValue = {1};
        //currentImage=0;

        // Auto start of viewpager
        final Handler handler = new Handler();

       // currentImage=0;

        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMENArray.size()) {
                    currentPage = 0;
                    currentImage=-1;
                }


                mPager.setCurrentItem(currentPage, false);
                currentPage++;

                currentImage++;
               // Toast.makeText(getApplicationContext(),String.valueOf(currentImage),Toast.LENGTH_LONG).show();
            }
        };

//        mPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
////                if (currentPage == XMENArray.size()) {
////                    currentPage = 0;
////                    currentImage=-1;
////                }
//////
//////
////                currentPage++;
////
////                currentImage++;
//
//
////                float endXValue = 0;
////                float x1 = event.getAxisValue(MotionEvent.AXIS_X);
////                int action = MotionEventCompat.getActionMasked(event);
////
////                if (currentPage == XMENArray.size()) {
////                    currentPage = 0;
////                    currentImage = -1;
////                }
////                switch (action) {
////                    case (MotionEvent.ACTION_DOWN):
////                        startXValue[0] = event.getAxisValue(MotionEvent.AXIS_X);
////
////                        return true;
////
////                    case (MotionEvent.ACTION_UP):
////                        endXValue = event.getAxisValue(MotionEvent.AXIS_X);
////                        if (endXValue > startXValue[0]) {
////                            if (endXValue - startXValue[0] > 100) {
////                                System.out.println("Left-Right");
////                                currentPage++;
////
////                                currentImage++;
////                                handler.post(Update);
////
////                            }
////                        }                         }
////
////
////
////
////
////
////
////
////
////
////                return false;
//
//                handler.post(Update);
//
//                return false;
//
//            }
//
//        });


        //mPager.setCurrentItem(currentPage, true);



        //

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(Update);


            }
        }, 2000, 2000);





}

    public void initializeConnection()
    {
        firebase = FirebaseDatabase.getInstance();
        //firebase.setPersistenceEnabled(true);

        database = firebase.getReference(DB_NAME);
//        table = database.child(ENTITY_NAME_PROFILES);

        mStorageRef = FirebaseStorage.getInstance().getReference();










    }


    public void sliderImageClick(View view) {


        Intent mIntent = new Intent(MainActivity.this, Webview.class);

        mIntent.putExtra("brandUrl", DAL.sliderUrl.get(currentImage));

        startActivity(mIntent);
    }
}