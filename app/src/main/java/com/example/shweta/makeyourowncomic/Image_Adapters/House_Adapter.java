package com.example.shweta.makeyourowncomic.Image_Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.shweta.makeyourowncomic.R;

import static com.example.shweta.makeyourowncomic.Image_Adapters.Circle_Adapter.convertDpToPixels;

/**
 * Created by shweta on 04-07-2017.
 */

public class House_Adapter extends BaseAdapter {

    private Context mContext;

    // Keep all Images in array
     public static Integer[] mThumbIds_House = {
            R.drawable.house1 , R.drawable.house2
    };

    // Constructor
    public House_Adapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds_House.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds_House[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds_House[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new GridView.LayoutParams(convertDpToPixels(110,mContext),convertDpToPixels(110,mContext)));
        return imageView;
    }

}
