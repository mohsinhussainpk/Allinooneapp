package com.example.mohsinhussain.allinoneapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.mohsinhussain.allinoneapp.R.id.imageView;

/**
 * Created by Hp on 7/10/2017.
 */

public class SliderAdapter extends PagerAdapter {

    private ArrayList<String> images;
    private LayoutInflater inflater;
    private Context context;
    View myImageLayout;
    ViewPager mPager;
    public static ImageView myImage;


    public SliderAdapter(Context context, ArrayList<String> images) {
        this.context = context;
        this.images=images;
        inflater = LayoutInflater.from(context);


    }

    public boolean onInterceptTouchEvent(MotionEvent ev){
        return false;
    }




    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View)object;
        ((ViewPager) container).removeView(view);
        view = null;
    }


    @Override
    public int getCount() {
        return images.size();
    }






    @Override
    public Object instantiateItem(ViewGroup view, int position) {

         myImageLayout = inflater.inflate(R.layout.slide, view, false);
                 myImage = (ImageView) myImageLayout
                                .findViewById(R.id.image);

        Glide.with(context)
                .load(images.get(position))
                .into(myImage);
                view.addView(myImageLayout, 0);
                return myImageLayout;




//        inflater = ((MainActivity) context).getLayoutInflater();
//        myImageLayout = inflater.inflate(R.layout.slide,null);
//        ImageButton myImage = (ImageButton) myImageLayout
//                .findViewById(R.id.image);
//
//        notifyDataSetChanged();
//        Glide.with(context)
//                .load(images.get(position))
//                .into(myImage);
//
//       ((ViewPager)view).addView(myImageLayout, 0);
//
//
//        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
