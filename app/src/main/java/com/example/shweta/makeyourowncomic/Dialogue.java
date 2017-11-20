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

import com.example.shweta.makeyourowncomic.Image_Adapters.Circle_Adapter;
import com.example.shweta.makeyourowncomic.Image_Adapters.Cloud_Adapter;
import com.example.shweta.makeyourowncomic.Image_Adapters.Rectangle_Adapter;
import com.example.shweta.makeyourowncomic.Image_Adapters.Short_Adapter;

import static com.example.shweta.makeyourowncomic.Blank_page.POS;


/**
 * Created by shweta on 12-06-2017.
 */

public class Dialogue extends Fragment {

    LinearLayout D_HSV_LL;
    RelativeLayout Dialog_RL;
    GridView d_gridView;
    static String Tag_Dialog = "CIRCLE";
    static int POS_dialog;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialogue, container, false);

        D_HSV_LL = (LinearLayout)v.findViewById(R.id.D_HSV_LL);
        Dialog_RL = (RelativeLayout)v.findViewById(R.id.Dialog_RL);

        d_gridView = (GridView)v. findViewById(R.id.D_SV_GV);
        d_gridView.setAdapter(new Circle_Adapter(getActivity()));


        add();

        Dialog_RL.setOnDragListener(new MyDragListener());
        d_gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

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

       LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dpWidthInPx,dpHeightInPx);
       params.setMarginStart(5);


       ImageButton circle = new ImageButton(getActivity());
       circle.setImageResource(R.drawable.circle);
       circle.setLayoutParams(params);
       circle.setBackgroundResource(R.drawable.my_box_bg);
       circle.setScaleType(ImageView.ScaleType.FIT_XY);
       circle.setId(R.id.circle_1);
       circle.setOnClickListener(new MyClickListener());


       ImageButton cloud = new ImageButton(getActivity());
       cloud.setImageResource(R.drawable.cloud);
       cloud.setLayoutParams(params);
       cloud.setBackgroundResource(R.drawable.my_box_bg);
       cloud.setScaleType(ImageView.ScaleType.FIT_XY);
       cloud.setId(R.id.cloud_1);
       cloud.setOnClickListener(new MyClickListener());

       ImageButton rectangle = new ImageButton(getActivity());
       rectangle.setImageResource(R.drawable.rectangle);
       rectangle.setLayoutParams(params);
       rectangle.setBackgroundResource(R.drawable.my_box_bg);
       rectangle.setScaleType(ImageView.ScaleType.FIT_XY);
       rectangle.setId(R.id.rectangle_1);
       rectangle.setOnClickListener(new MyClickListener());

       ImageButton short_1 = new ImageButton(getActivity());
       short_1.setImageResource(R.drawable.short_1);
       short_1.setLayoutParams(params);
       short_1.setBackgroundResource(R.drawable.my_box_bg);
       short_1.setScaleType(ImageView.ScaleType.FIT_XY);
       short_1.setId(R.id.short_1);
       short_1.setOnClickListener(new MyClickListener());


       D_HSV_LL.addView(circle);
       D_HSV_LL.addView(cloud);
       D_HSV_LL.addView(rectangle);
       D_HSV_LL.addView(short_1);

    }

    class MyClickListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.circle_1 :
                     d_gridView.setAdapter(new Circle_Adapter(getActivity()));
                     Tag_Dialog = "CIRCLE";
                     break;
                case R.id.cloud_1 :
                    d_gridView.setAdapter(new Cloud_Adapter(getActivity()));
                    Tag_Dialog = "CLOUD";
                    break;
                case R.id.rectangle_1 :
                    d_gridView.setAdapter(new Rectangle_Adapter(getActivity()));
                    Tag_Dialog = "RECTANGLE";
                    break;
                case R.id.short_1 :
                    d_gridView.setAdapter(new Short_Adapter(getActivity()));
                    Tag_Dialog = "SHORT";
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
