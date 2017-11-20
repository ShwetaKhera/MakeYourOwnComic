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
 * Created by shweta on 05-07-2017.
 */

public class Batman_Adapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public static Integer[] mThumbIds_Batman = {
            R.drawable.batman_1 , R.drawable.batman_2 ,
            R.drawable.batmanjoker1 , R.drawable.batmanjoker2
    };

    // Constructor
    public Batman_Adapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds_Batman.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds_Batman[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds_Batman[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new GridView.LayoutParams(convertDpToPixels(50,mContext),convertDpToPixels(50,mContext) ));
        return imageView;
    }

}


