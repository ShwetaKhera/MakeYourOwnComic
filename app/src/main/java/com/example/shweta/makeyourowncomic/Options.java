package com.example.shweta.makeyourowncomic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.example.shweta.makeyourowncomic.MainActivity.acct;
import static com.example.shweta.makeyourowncomic.MainActivity.imageLoader;


/**
 * Created by shweta on 27-05-2017.
 */

public class Options extends AppCompatActivity{

    private ImageView imageViewRound , profile_image , dot;

    TextView profile_name , email;
    Button dropdown , nav_drawer , MYOC;
    LinearLayout dropDownMenuIconItem;
    RelativeLayout side_nav_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        dropDownMenuIconItem = (LinearLayout) findViewById(R.id.vertical_dropdown_icon_menu_items);
        side_nav_bar = (RelativeLayout)findViewById(R.id.side_nav_bar);
        dropdown = (Button)findViewById(R.id.account_button);
        nav_drawer = (Button)findViewById(R.id.nav_drawer);
        MYOC = (Button)findViewById(R.id.MYOC);

        profile_name = (TextView)findViewById(R.id.profile_name);
        email = (TextView)findViewById(R.id.email);

        imageViewRound=(ImageView)findViewById(R.id.imageView_round);
        profile_image = (ImageView)findViewById(R.id.profile_image);
        dot = (ImageView)findViewById(R.id.dot);


        imageLoader.get(acct.getPhotoUrl().toString(),
                new ImageLoader.ImageListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                        if (response.getBitmap() != null) {
                            // load image into imageview
                            imageViewRound.setImageBitmap(response.getBitmap());

                            profile_image.setImageBitmap(response.getBitmap());
                            profile_name.setText(acct.getDisplayName());
                            email.setText(acct.getEmail());
                        }
                    }
                });
       dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verticalDropDownIconMenu(v);
            }
        });


        nav_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(side_nav_bar.getVisibility() == VISIBLE){
                    side_nav_bar.setVisibility(INVISIBLE);
                    imageViewRound.setVisibility(VISIBLE);
                    dot.setVisibility(INVISIBLE);
                } else {
                    side_nav_bar.setVisibility(VISIBLE);
                    imageViewRound.setVisibility(INVISIBLE);

                    final Animation animation = new AlphaAnimation(1, 0);
                    animation.setDuration(1000);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setRepeatCount(Animation.INFINITE);
                    animation.setRepeatMode(Animation.REVERSE);

                    dot.startAnimation(animation);
                }
            }
        });

            MYOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Blank_page.class);
                startActivity(intent);
            }
        });
    }

    public void verticalDropDownIconMenu(View view) {
        if (dropDownMenuIconItem.getVisibility() == VISIBLE) {
            dropDownMenuIconItem.setVisibility(INVISIBLE);

        } else {
            dropDownMenuIconItem.setVisibility(VISIBLE);

        }
    }

    public void menuItemClick(View view) {
        dropDownMenuIconItem.setVisibility(INVISIBLE);

    }
  }
