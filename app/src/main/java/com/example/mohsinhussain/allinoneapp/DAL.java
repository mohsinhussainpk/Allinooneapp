package com.example.mohsinhussain.allinoneapp;

/**
 * Created by Hp on 6/19/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Hp on 5/13/2017.
 */

public class DAL  {

    private static String DB_NAME = "Brand Records";
    private static String ENTITY_NAME_PROFILES = "Records";

    private FirebaseDatabase firebase;
    private DatabaseReference database;
    private static DatabaseReference table;
    private Context context;

    //List<Profile> searchResults;
    private boolean success = false;
    Activity activity;
    ArrayList<String> brandName = new ArrayList<String>();
    ArrayList<String> brandImgId = new ArrayList<String>();
    ArrayList<String> brandCategory = new ArrayList<String>();
    ArrayList<String> brandUrl = new ArrayList<String>();
    public static ArrayList<String> getBrandName = new ArrayList<String>();
    ArrayList<String> getBrandCategory = new ArrayList<String>();
    ArrayList<String> getBrandName1=null;
String[] copy;

    Scanner scanner;
    public static int  counter = 0;

    //public static String[] getBrandCategory=new  String[10];


    public DAL(Activity activity, Context context) {
        this.activity = activity;
        firebase = FirebaseDatabase.getInstance();
        database = firebase.getReference(DB_NAME);
        table = database.child(ENTITY_NAME_PROFILES);

        this.context = context;
    }

    public boolean addProfile() {

        String[] tokens = new String[1000];
        InputStream is = context.getResources().openRawResource(R.raw.shoppingrecords);


        scanner = new Scanner(is);
        int k = 0;
        while (scanner.hasNext()) {
            tokens = scanner.nextLine().split(";");
            brandName.add(tokens[0]);
            brandImgId.add(tokens[1]);
            brandCategory.add(tokens[2]);
            brandUrl.add(tokens[3]);

            k++;
        }

//i am fetching name and cgpa from file and save the data to firebase

        for (int i = 0; i < brandName.size(); i++) {


            final DatabaseReference record = table.child(String.valueOf(i));
            record.child("Name").setValue(brandName.get(i), new DatabaseReference.CompletionListener() {


                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    //    Toast.makeText(context, "h", Toast.LENGTH_SHORT).show();
                }
            });

            record.child("Imgid").setValue(brandImgId.get(i), new DatabaseReference.CompletionListener() {


                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    //    Toast.makeText(context, "h", Toast.LENGTH_SHORT).show();
                }
            });

            record.child("Category").setValue(brandCategory.get(i), new DatabaseReference.CompletionListener() {


                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    //    Toast.makeText(context, "h", Toast.LENGTH_SHORT).show();
                }
            });

            record.child("Url").setValue(brandUrl.get(i), new DatabaseReference.CompletionListener() {


                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    //    Toast.makeText(context, "h", Toast.LENGTH_SHORT).show();
                }
            });


        }
        return success;
    }

    public static void searchProfile() {


        Query query = table.orderByChild("Name");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot record : dataSnapshot.getChildren()) {


                    //Log.i("DAL::deleteProfile", record.child("name").getValue(String.class) + " "  );
                    //Log.i("DAL::deleteProfile", record.child("Roll").getValue(String.class) + " "  );
                    getBrandName.add(counter, String.valueOf(record.child("Name").getValue(String.class)));

                    //getBrandCategory[counter]= String.valueOf( record.child("Category").getValue(String.class));
                    //Toast.makeText(context,getBrandName.get(counter),Toast.LENGTH_SHORT).show();
                    // Log.i("DAL::deleteProfile", getitemname[counter] + " "  );
                    //Log.i("DAL::deleteProfile", getcgpa[counter] + " "  );
                    counter++;


                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public ArrayList<String> getBrandName() {

        getBrandName.add("DARAZ.PK");
        getBrandName.add("YAYVO.PK");
        return getBrandName;

    }





    public void printData() {
        if (getBrandName.isEmpty()) {
            Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show();

        } else {
            for (int i = 0; i < getBrandName.size(); i++) {
                Toast.makeText(context, getBrandName.get(i), Toast.LENGTH_SHORT).show();
            }


        }

    }
}