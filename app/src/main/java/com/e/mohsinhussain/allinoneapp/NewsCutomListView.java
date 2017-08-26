package com.e.mohsinhussain.allinoneapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Hp on 8/25/2017.
 */

public class NewsCutomListView extends ArrayAdapter<String> {


    ArrayList<String> brand;
    //String[] brand;
//     ArrayList<Bitmap>bitmaps;
    ArrayList<String>images;
    ArrayList<String>src;
    Context mcontext;

    private static LayoutInflater mInflater=null;


    public NewsCutomListView(Activity context, ArrayList<String> brand, ArrayList<String> images,ArrayList<String> src) {
        super(context, R.layout.news_custom_view,brand);
        this.brand = brand;

        this.images = images;
        this.src=src;
        this.mcontext = context;


    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub

        return (brand == null) ? 0 : brand.size();
    }
//    @Override
//    public String getItem(int position) {
// //TODO Auto-generated method stub
//        return brand.get(position);// may be in your case
//    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi=convertView;
        NewsCutomListView.ViewHolderNew mViewHolder = new NewsCutomListView.ViewHolderNew();

        if (vi == null) {
            mInflater = (LayoutInflater) mcontext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = mInflater.inflate(R.layout.news_custom_view, parent,false);
            //convertView = mInflater.inflate(R.layout.custom_view, parent, false);
            mViewHolder.mFlag = (ImageView) vi.findViewById(R.id.imageView);
            mViewHolder.mName = (TextView) vi.findViewById(R.id.textView);
            mViewHolder.src= (TextView) vi.findViewById(R.id.newsSrc);
            vi.setTag(mViewHolder);
        } else {

            mViewHolder = (NewsCutomListView.ViewHolderNew) vi.getTag();
        }
        notifyDataSetChanged();

        if(MainActivity.flag==false)
        {
            Picasso.with(mcontext).load(images.get(position)).placeholder(R.drawable.ic_menu_camera).resize(150, 150)
                    .error(R.drawable.ic_menu_camera).into(mViewHolder.mFlag);
//            Glide.with(mcontext)
//                    .load(images.get(position))
//                    .into(mViewHolder.mFlag);

        }

        mViewHolder.mName.setText(brand.get(position));
        mViewHolder.src.setText(src.get(position));


        return vi;
    }

    static class ViewHolderNew {
        ImageView mFlag;
        TextView mName;
        TextView src;
    }
}
