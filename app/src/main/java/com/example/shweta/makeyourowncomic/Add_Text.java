package com.example.shweta.makeyourowncomic;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shweta.makeyourowncomic.Image_Adapters.Color_Adapter;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.example.shweta.makeyourowncomic.Blank_page.Change_View;
import static com.example.shweta.makeyourowncomic.Blank_page.DrawingArea;
import static com.example.shweta.makeyourowncomic.Blank_page.edit_text_button;
import static com.example.shweta.makeyourowncomic.Image_Adapters.Color_Adapter.mThumbIds_Color;

/**
 * Created by shweta on 14-07-2017.
 */

public class Add_Text extends Fragment {

    static EditText text;
    Button text_done;
    static String TEXT;
    static TextView textView;
    GridView color_grid;
    RelativeLayout color_LL;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.add_text, container, false);

        color_LL = (RelativeLayout) v.findViewById(R.id.color_LL);
        text = (EditText)v.findViewById(R.id.text);
        text_done = (Button)v.findViewById(R.id.text_done);
        color_grid = (GridView)v.findViewById(R.id.color_gridview);

        color_grid.setAdapter(new Color_Adapter(getActivity()));

        color_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(color_grid.getVisibility() == INVISIBLE)
                color_grid.setVisibility(VISIBLE);
                else
                    color_grid.setVisibility(INVISIBLE);
            }
        });

        color_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(textView != null)
                    textView.setTextColor(mThumbIds_Color[position]);
            }
        });
        text_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TEXT = text.getText().toString();
                if(textView == null) {
                    textView = new TextView(getActivity());
                    textView.setText(TEXT);
                    textView.setTag(Change_View.getTag());
                    textView.setLayoutParams(Change_View.getLayoutParams());
                    textView.setX(Change_View.getX());
                    textView.setY(Change_View.getY());
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextColor(getResources().getColor(R.color.black));
                    DrawingArea.addView(textView);
                }
                else{
                        edit_text_button.setVisibility(VISIBLE);
                        textView.setText(TEXT);
                    }

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                        edit_text_button.setVisibility(VISIBLE);

                    }
                });


                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place, new Plain()).commit();


            }
        });

        return v;
    }
}
