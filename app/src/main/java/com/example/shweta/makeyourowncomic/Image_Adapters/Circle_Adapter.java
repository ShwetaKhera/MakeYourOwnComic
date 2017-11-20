package com.example.shweta.makeyourowncomic.Image_Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.shweta.makeyourowncomic.R;

/**
 * Created by shweta on 02-07-2017.
 */

public class Circle_Adapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    static public Integer[] mThumbIds_Circle = {
            R.drawable.blackdialog ,
            R.drawable.circle1 ,
            R.drawable.circle2,
            R.drawable.circle_1 ,
            R.drawable.circle_2 ,
            R.drawable.circularspeechbubbleoutline ,
            R.drawable.dialog
    };

    // Constructor
    public Circle_Adapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds_Circle.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds_Circle[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds_Circle[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new GridView.LayoutParams(convertDpToPixels(50,mContext),convertDpToPixels(50,mContext)));
        return imageView;
    }
    public static int convertDpToPixels(float dp, Context context){
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                resources.getDisplayMetrics()
        );
    }

}
