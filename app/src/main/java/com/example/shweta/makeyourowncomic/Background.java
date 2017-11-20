package com.example.shweta.makeyourowncomic;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.shweta.makeyourowncomic.Image_Adapters.City_Adapter;
import com.example.shweta.makeyourowncomic.Image_Adapters.House_Adapter;

import static com.example.shweta.makeyourowncomic.Blank_page.DrawingArea;
import static com.example.shweta.makeyourowncomic.Image_Adapters.City_Adapter.mThumbIds_City;
import static com.example.shweta.makeyourowncomic.Image_Adapters.House_Adapter.mThumbIds_House;

/**
 * Created by shweta on 13-06-2017.
 */

public class Background extends Fragment {

    LinearLayout B_HSV_LL;
    GridView b_gridView;
    String Tag_Background = "CITY";
    RelativeLayout BK_RL;

    float x , y;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.background, container, false);

        B_HSV_LL = (LinearLayout) v.findViewById(R.id.B_HSV_LL);
        BK_RL = (RelativeLayout)v.findViewById(R.id.Background_RL);

        b_gridView = (GridView) v.findViewById(R.id.B_SV_GV);
        b_gridView.setAdapter(new City_Adapter(getActivity()));

        add();

        b_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {

                switch (Tag_Background){
                    case "CITY" :   DrawingArea.setBackgroundResource(mThumbIds_City[position]);   break;
                    case "HOUSE" :   DrawingArea.setBackgroundResource(mThumbIds_House[position]);   break;
                }

            }
        });
                return v;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    void add() {

        final float scale = getResources().getDisplayMetrics().density;

        int dpWidthInPx  = (int) (55 * scale);
        int dpHeightInPx = (int) (55 * scale);

        ImageButton city = new ImageButton(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dpWidthInPx, dpHeightInPx);
        params.setMarginStart(10);
        city.setLayoutParams(params);

        city.setImageResource(R.drawable.city1);
        city.setBackgroundResource(R.drawable.my_box_bg);
        city.setScaleType(ImageView.ScaleType.FIT_XY);
        city.setId(R.id.city_1);
        city.setOnClickListener(new MyClickListener());

        ImageButton house = new ImageButton(getActivity());
        house.setImageResource(R.drawable.house1);
        house.setLayoutParams(params);
        house.setBackgroundResource(R.drawable.my_box_bg);
        house.setScaleType(ImageView.ScaleType.FIT_XY);
        house.setId(R.id.house_1);
        house.setOnClickListener(new MyClickListener());

        B_HSV_LL.addView(city);
        B_HSV_LL.addView(house);
    }

    class MyClickListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.city_1 :
                    b_gridView.setAdapter(new City_Adapter(getActivity()));
                    Tag_Background = "CITY";
                    break;
                case R.id.house_1 :
                    b_gridView.setAdapter(new House_Adapter(getActivity()));
                    Tag_Background = "HOUSE";
                    break;

            }
        }
    }

    void clear(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, new Plain());
        fragmentTransaction.commit();
    }


}