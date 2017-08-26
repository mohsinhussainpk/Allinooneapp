package com.e.mohsinhussain.allinoneapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.provider.Settings.Secure;
import android.widget.Button;
import android.content.ClipboardManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.util.LinkProperties;

public class ReferAndEarn extends AppCompatActivity   {
     AlertDialog.Builder myDialog;
   static Branch branch;
    String url1;
    int credits;
    int minimumVal=50;
    SeekBar seekBar1;
    TextView amountText;
    SeekBar simpleSeekBar;
    BranchUniversalObject branchUniversalObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_and_earn);
       // final AlertDialog myDialog = new AlertDialog(this);
         myDialog = new AlertDialog.Builder(this);
       //  simpleSeekBar = (SeekBar) findViewById(R.id.simpleSeekBar); // initiate the Seek bar
        final Button btn=(Button)findViewById(R.id.getreedembutton);
        final TextView amount=(TextView)findViewById(R.id.amountText);
        final TextView referallink=(TextView)findViewById(R.id.referallinktext);
        final Button copybtn=(Button)findViewById(R.id.referalcopybtn);
        final Button sharebtn=(Button)findViewById(R.id.refsharebutton);




//        Branch.getInstance(getApplicationContext()).userCompletedAction("signup");
//            CommerceEvent commerceEvent = new CommerceEvent();
//            commerceEvent.setRevenue(1101.99);
//            Branch.getInstance().sendCommerceEvent(commerceEvent, null, null);

        Branch.getInstance(getApplicationContext()).setIdentity(Secure.getString(this.getContentResolver(),
                Secure.ANDROID_ID));

//        Branch.getInstance().loadRewards(new Branch.BranchReferralStateChangedListener() {
//            @Override
//            public void onStateChanged(boolean changed, BranchError error) {
//                if (error == null && Branch.getInstance().getCredits() > 5) {
//                    Branch.getInstance().redeemRewards(5);
//                }
//            }
//        });

        LinkProperties linkProperties = new LinkProperties()
                .setChannel("facebook")
                .setFeature("sharing")
                .addControlParameter("$desktop_url", "https://play.google.com/store/apps/details?id=com.e.mohsinhussain.allinoneapp")
                .addControlParameter("$android_url", "https://play.google.com/store/apps/details?id=com.e.mohsinhussain.allinoneapp")
                .addControlParameter("$ios_url", "https://play.google.com/store/apps/details?id=com.e.mohsinhussain.allinoneapp");


        branchUniversalObject = new BranchUniversalObject();
        branchUniversalObject.generateShortUrl(this, linkProperties, new Branch.BranchLinkCreateListener() {
            @Override
            public void onLinkCreate(final  String url, BranchError error) {
                if (error == null) {
                    url1=url;
                    copybtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("copy", url);
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(getApplicationContext(),"Copied",Toast.LENGTH_SHORT).show();
                        }
                    });

                    Log.i("MyApp", "got my Branch link to share: " + url);
                    referallink.setText(url);
                    Toast.makeText(getApplicationContext(),url,Toast.LENGTH_SHORT).show();

                    sharebtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            try {
                                Intent i = new Intent(Intent.ACTION_SEND);
                                i.setType("text/plain");
                                i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                                String sAux = "\nLet me recommend you this application\n\n";
                                sAux = sAux + url;
                                i.putExtra(Intent.EXTRA_TEXT, sAux);
                                startActivity(Intent.createChooser(i, "choose one"));
                            } catch(Exception e) {
                                //e.toString();
                            }
                        }
                    });

                }

            }
        });


//        Branch.getInstance(getApplicationContext()).loadRewards(new Branch.BranchReferralStateChangedListener() {
//            @Override
//            public void onStateChanged(boolean changed, BranchError error) {
//                // changed boolean will indicate if the balance changed from what is currently in memory
//
//                if (error == null) {
//                    String bucket = "myBucket";
//
//                    Toast.makeText(getApplicationContext(),String.valueOf(Branch.getInstance(getApplicationContext()).getCreditsForBucket(bucket)),Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });

        Branch.getInstance(getApplicationContext()).loadRewards(new Branch.BranchReferralStateChangedListener() {
            @Override
            public void onStateChanged(boolean changed, BranchError error) {
                // changed boolean will indicate if the balance changed from what is currently in memory

                // will return the balance of the current user's credits
                //Branch branch = Branch.getInstance();
                 credits = branch.getCredits();
                amount.setText(String.valueOf(credits));
                //Toast.makeText(getApplicationContext(),String.valueOf(credits),Toast.LENGTH_SHORT).show();

                if(credits>49)
                {
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            callLoginDialog(credits);
                        }
                    });

                }
                else {
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(),"minimum 50rs earn to redeem",Toast.LENGTH_SHORT).show();
                        }
                    });



                }
            }
        });






    }
    @Override
    public void onStart() {
        super.onStart();
        branch = Branch.getInstance();

        branch.initSession(new Branch.BranchReferralInitListener(){
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error == null) {
                    // params are the deep linked params associated with the link that the user clicked -> was re-directed to this app
                    // params will be empty if no data found
                    // ... insert custom logic here ...
                } else {
                    Log.i("Allinone", error.getMessage());
                }
            }
        }, this.getIntent().getData(), this);
    }
    private void callLoginDialog(final int credits)
    {

//        LayoutInflater inflater = (LayoutInflater)this.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
//
//        View dialogView= inflater.inflate(R.layout.login_ialougue, null);
        setContentView(R.layout.login_ialougue);

        Button login = (Button) findViewById(R.id.reddembutton);

         amountText=(TextView)findViewById(R.id.amountText);
        seekBar1=(SeekBar) findViewById(R.id.seekBar1);
        //simpleSeekBar.setMax(200);
        final EditText phoneNumber = (EditText) findViewById(R.id.userphonenumber);
        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        //simpleSeekBar.setMax(300);
//        simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();
//                // seekNumberView.setText(numberAsString);
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(getApplicationContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(getApplicationContext(),"seekbar touch stopped!", Toast.LENGTH_SHORT).show();
//            }
//        });


//        myDialog.setView(dialogView);
        myDialog.setCancelable(true);
        myDialog.show();
        seekBar1.setMax(branch.getCredits());
        seekBar1.post(new Runnable() {
            @Override
            public void run() {
                seekBar1.setProgress(10);
            }
        });
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int stepSize = 10;
                progress = (progress/stepSize)*stepSize;
                if (progress >= 50) {
                    seekBar.setProgress(progress);
                    amountText.setText(String.valueOf(progress));
                } else {

                    amountText.setText("50");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                branchUniversalObject = new BranchUniversalObject()
//                        .setCanonicalIdentifier("item/12345")
//                        .setTitle("My Content Title")
//                        .setContentDescription("My Content Description")
//                        .setContentImageUrl("https://example.com/mycontent-12345.png")
//                        .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)
//                        .addContentMetadata("property1", phoneNumber.getText().toString())
//                        .addContentMetadata("property2", spinner1.getSelectedItem().toString());

                Branch.getInstance().loadRewards(new Branch.BranchReferralStateChangedListener() {
            @Override
            public void onStateChanged(boolean changed, BranchError error) {
                if (error == null && Branch.getInstance().getCredits() > 50) {
                    Branch.getInstance().redeemRewards(Integer.parseInt(amountText.getText().toString()));
                Toast.makeText(getApplicationContext(),amountText.getText().toString()+"rewarded",Toast.LENGTH_SHORT).show();
                    JSONObject metaData=new JSONObject();
                    try {
                        metaData.put("phone number",phoneNumber.getText().toString());
                        metaData.put("network",spinner1.getSelectedItem().toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Branch.getInstance().userCompletedAction("user_info",metaData);

                }
            }
        });

            }
        });


    }



}
