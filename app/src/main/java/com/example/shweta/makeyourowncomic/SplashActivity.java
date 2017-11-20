package com.example.shweta.makeyourowncomic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import static android.os.SystemClock.sleep;

/**
 * Created by shweta on 24-05-2017.
 */

public class SplashActivity extends AppCompatActivity {

    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        sleep(3000);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
