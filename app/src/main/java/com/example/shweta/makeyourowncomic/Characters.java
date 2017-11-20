package com.example.shweta.makeyourowncomic;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.shweta.makeyourowncomic.Image_Adapters.Batman_Adapter;
import com.example.shweta.makeyourowncomic.Image_Adapters.Homer_Adapter;

import static com.example.shweta.makeyourowncomic.Blank_page.POS;

/**
 * Created by shweta on 12-06-2017.
 */

public class Characters extends Fragment {

    LinearLayout C_HSV_LL;
    GridView c_gridView;
    RelativeLayout Charac_RL;
    static String Tag_Charac = "HOMER";
    static int POS_charac;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.characters, container, false);

        C_HSV_LL = (LinearLayout)v.findViewById(R.id.C_HSV_LL);
        Charac_RL = (RelativeLayout)v.findViewById(R.id.Charc_Rl);

        c_gridView = (GridView)v. findViewById(R.id.C_SV_GV);
        c_gridView.setAdapter(new Homer_Adapter(getActivity()));

        add();

        Charac_RL.setOnDragListener(new MyDragListener());
        c_gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long arg3) {
                POS = position;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            }
        });

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    void add(){

        final float scale = getResources().getDisplayMetrics().density;

        int dpWidthInPx  = (int) (55 * scale);
        int dpHeightInPx = (int) (55 * scale);

        ImageButton homer = new ImageButton(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dpWidthInPx,dpHeightInPx);
        params.setMarginStart(5);
        homer.setLayoutParams(params);
        homer.setImageResource(R.drawable.homer1);
        homer.setBackgroundResource(R.drawable.my_box_bg);
        homer.setScaleType(ImageView.ScaleType.FIT_XY);
        homer.setId(R.id.homer_1);
        homer.setOnClickListener(new MyClickListener());

        ImageButton batman = new ImageButton(getActivity());
        batman.setImageResource(R.drawable.batman_1);
        batman.setLayoutParams(params);
        batman.setBackgroundResource(R.drawable.my_box_bg);
        batman.setScaleType(ImageView.ScaleType.FIT_XY);
        batman.setId(R.id.batman_1);
        batman.setOnClickListener(new MyClickListener());

        C_HSV_LL.addView(homer);
        C_HSV_LL.addView(batman);
    }

    class MyClickListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.homer_1 :
                    c_gridView.setAdapter(new Homer_Adapter(getActivity()));
                    Tag_Charac = "HOMER";
                    break;
                case R.id.batman_1 :
                    c_gridView.setAdapter(new Batman_Adapter(getActivity()));
                    Tag_Charac = "BATMAN";
                    break;

            }
        }
    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {


            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:


                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_place, new Plain());
                    fragmentTransaction.commit();
                    break;
                default:
                    break;
            }
            return true;
        }
    }


}
