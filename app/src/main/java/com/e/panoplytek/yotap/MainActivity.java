package com.e.panoplytek.yotap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import io.branch.referral.Branch;

public class MainActivity extends BottomNavigtionViewActivity
        implements NavigationView.OnNavigationItemSelectedListener ,  BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    //ViewPager viewPager;
    //CustomSwipeAdapter customSwipeAdapter;

    public static final String CATEGORY = "category";
    private static String DB_NAME = "Brand Records";
    boolean exit;

    private  ViewPager mPager;

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



    //static String category = "";
    public static FirebaseDatabase firebase;
    public static DatabaseReference database;
    public static DatabaseReference table;
    public static StorageReference mStorageRef;

    String[] web = {
"Refer and Earn",
            "News",
            "Videos",
            "Live TV",
            "Games",

            "Deals",
            "Shopping",
            "Government Services",
            "Travel",

            "Food",
            "Delivery Services",

            "Taxi",
            "Classifieds",
            "Jobs",
            "Payments",
            "Tickets",
            "Grocery",
            "Car Maintenance",
            "Real State",
            "Price Comparison",
            "Education",

            "Health",

            "Furniture",
            "Printing",
            "Gifts and Flowers",
            "Baby and Kids",
            "Women",
            "Entertainment",
            "Music",
            "Social",









    } ;
    int[] imageId = {
            R.drawable.money,
            R.drawable.newspaper,
            R.drawable.youtube,
            R.drawable.live,
            R.drawable.gamepad,
            R.drawable.deals,
            R.drawable.shoppingcart,
            R.drawable.building,
            R.drawable.distancetotravelbetweentwopoints,

            R.drawable.food,
            R.drawable.truck,
            R.drawable.taxi,
            R.drawable.classifieds,
            R.drawable.onlinejobsearchsymbol,
            R.drawable.wallet,
            R.drawable.theaterticket,
            R.drawable.groceriesbag,
            R.drawable.carbuy,
            R.drawable.realestate,
            R.drawable.comparison,
            R.drawable.education,
            R.drawable.medicines,
            R.drawable.armchair,
            R.drawable.printbutton,
            R.drawable.gift,

            R.drawable.babydummywithbearheadsilhouette,
            R.drawable.dress,
            R.drawable.mask,
            R.drawable.music,
            R.drawable.users,









    };
    Branch branch;
    int currentImage =-1;
    public static DAL layer;
    ImageButton GridImageButton;
  //  static DAL layer;
  public static InterstitialAd mInterstitialAd;
    SliderLayout sliderLayout;

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

        MobileAds.initialize(this, "ca-app-pub-7071387714574454~2252212923");

        CustomGrid adapter = new CustomGrid(this, web, imageId);
        ExpandableHeightGridView grid=(ExpandableHeightGridView) findViewById(R.id.customgrid);
        grid.setAdapter(adapter);

        grid.setExpanded(true);


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

         sliderLayout = (SliderLayout)findViewById(R.id.slider);

//        initializeConnection();




        //layer.sliderDetail();



        for(int i=0 ; i<XMENArray.size() ; i++){

            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .image(XMENArray.get(i));

            final int finalI = i;
            textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
             //  Toast.makeText(getApplicationContext(),XMENArray.get(finalI),Toast.LENGTH_SHORT).show();
                    Intent mIntent = new Intent(MainActivity.this, Webview.class);

                    mIntent.putExtra("brandUrl", DAL.sliderUrl.get(finalI));

                    startActivity(mIntent);
                }
            });

//            textSliderView
//                    .description(name)
//                    .image(Hash_file_maps.get(name))
//                    .setScaleType(BaseSliderView.ScaleType.Fit)
//                    .setOnSliderClickListener(this);
//            textSliderView.bundle(new Bundle());

//            textSliderView.getBundle()
//                    .putString("extra",name);
//
 sliderLayout.addSlider(textSliderView);
            currentImage=i;
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener((ViewPagerEx.OnPageChangeListener) this);
        //init();

        // layer.sliderDetail();

        Log.i("count", IntroActivity.count + " "  );

        GridImageButton= (ImageButton) findViewById(R.id.os_images);

//        AdView adView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder() .setRequestAgent("android_studio:ad_template").build();
//        adView.loadAd(adRequest);


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder() .build();

        mAdView.loadAd(adRequest);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7071387714574454/1821118602");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

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

CustomDialogClass customDialogClass=new CustomDialogClass(this);
        customDialogClass.show();




        //Toast.makeText(this,"onBackPressed",Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_feedback) {
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.e.panoplytek.yotap")));




//        } else if (id == R.id.nav_slideshow) {
//
//            Intent intent=new Intent(getApplicationContext(),TermsAndConditionActivity.class);
//            startActivity(intent);


        }

//        else if (id == R.id.nav_manage) {
////            Intent intent=new Intent(getApplicationContext(),PrivacyActivity.class);
//            startActivity(intent);

//
//         else if (id == R.id.nav_share) {
////            Intent myIntent=new Intent(Intent.ACTION_SEND);
////            myIntent.setType("Text/Plan");
////            String shareBody="Your body here";
////            String shareSubject="Your Subject here";
////            myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
////            myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
////            startActivity(Intent.createChooser(myIntent,"Share"));
//
//            try {
//                Intent i = new Intent(Intent.ACTION_SEND);
//                i.setType("text/plain");
//                i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
//                String sAux = "\nLet me recommend you this application\n\n";
//                sAux = sAux + "https://play.google.com/store/apps/details?id=Orion.Soft \n\n";
//                i.putExtra(Intent.EXTRA_TEXT, sAux);
//                startActivity(Intent.createChooser(i, "choose one"));
//            } catch(Exception e) {
//                //e.toString();
//            }
//
//
//        }
        else if (id == R.id.nav_referandearn) {

            startActivity(new Intent(MainActivity.this,ReferAndEarn.class));





//        } else if (id == R.id.nav_slideshow) {
//
//            Intent intent=new Intent(getApplicationContext(),TermsAndConditionActivity.class);
//            startActivity(intent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7071387714574454/1821118602");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }

    public void itemClicked(int position) {



        CheckConnetivity check = new CheckConnetivity();
        Boolean conn = check.isNetworkAvailable(this.getApplicationContext());

       ImageButton imageButton= (ImageButton) findViewById(R.id.os_images);


        if(conn) {
            if (web[position] == "News") {

                Intent intent = new Intent(this, NewsAndDealsActivity.class);
                startActivity(intent);

              //  overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

// for solving the proble after news click animation we should finish the main activity but come back to main actticty it will finish



            }
            else if (web[position] == "Government Services") {

                Intent intent = new Intent(this, GovermentServicesActivity.class);
                startActivity(intent);
            }

            else if(web[position] == "Deals"){
                Intent intent = new Intent(this,DealsAndCouponsActivty.class);
                startActivity(intent);

            }

            else if(web[position] == "Travel"){
                Intent intent = new Intent(this,TravelActivity.class);
                startActivity(intent);

            }
            else if(web[position] == "Videos"){
                layer=new DAL(this,this);
                layer.searchVideos();
            }
            else if(web[position] == "Refer and Earn"){
                Intent intent = new Intent(this,ReferAndEarn.class);
                startActivity(intent);

            }
            else {
                layer=new DAL(this,this);
                layer.searchProfile(web[position]);


            }
        }

        else
        {
            check.connectivityMessage("Check Network Connection",this);

        }



    }
    private void init() {

        final int[] currentPage = {0};

        final SliderAdapter[] sliderAdapter = {new SliderAdapter(MainActivity.this, XMENArray)};
//        mPager = (ViewPager) findViewById(R.id.pager);

        mPager.setAdapter(sliderAdapter[0]);
        mPager.beginFakeDrag();
//        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
//        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update;
       // currentImage=0;

         Update = new Runnable() {
            public void run() {
                if (currentPage[0] == XMENArray.size()) {
                    currentPage[0] = 0;
                    currentImage=-1;
                }


                mPager.setCurrentItem(currentPage[0], false);
                currentPage[0]++;

                currentImage++;
               // Toast.makeText(getApplicationContext(),String.valueOf(currentImage),Toast.LENGTH_LONG).show();
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(Update);


            }
        }, 2000, 2000);






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


}

    public void initializeConnection()
    {
        firebase = FirebaseDatabase.getInstance();
        //firebase.setPersistenceEnabled(true);

        database = firebase.getReference(DB_NAME);
//        table = database.child(ENTITY_NAME_PROFILES);

        mStorageRef = FirebaseStorage.getInstance().getReference();










    }

//    @Override
//    protected void onStop() {
//        sliderLayout.stopAutoCycle();
//        super.onStop();
//    }

    public void sliderImageClick(View view) {



    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }


}