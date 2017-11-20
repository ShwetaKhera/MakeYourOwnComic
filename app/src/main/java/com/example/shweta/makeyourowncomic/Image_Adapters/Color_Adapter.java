package com.example.shweta.makeyourowncomic.Image_Adapters;

/**
 * Created by shweta on 14-07-2017.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.shweta.makeyourowncomic.R;

import static com.example.shweta.makeyourowncomic.Image_Adapters.Circle_Adapter.convertDpToPixels;

public class Color_Adapter extends BaseAdapter {


        private Context mContext;

        // Keep all Images in array
        static public Integer[] mThumbIds_Color = {
                R.color.black , R.color.cardview_light_background , R.color.colorAccent ,
                R.color.colorPrimary , R.color.colorPrimaryDark , R.color.light_black
        };

        // Constructor
        public Color_Adapter(Context c){
            mContext = c;
        }

        @Override
        public int getCount() {
            return mThumbIds_Color.length;
        }

        @Override
        public Object getItem(int position) {
            return mThumbIds_Color[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(mThumbIds_Color[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new GridView.LayoutParams(convertDpToPixels(20,mContext),convertDpToPixels(20,mContext)));
            return imageView;
        }
 }

