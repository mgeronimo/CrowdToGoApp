package com.crowdtogo.crowdie.crowdtogo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;


public class SplashActivity extends Activity {

    private static long SLEEP_TIME = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DBHelper myDbHelper = new DBHelper(this);
        changeFonts();


//        try {
//             myDbHelper.onCreate();
//            myDbHelper.onCreate(this);
//            } catch (IOException ioe) {
//                  throw new Error("Unable to create database");
//            }


        IntentLauncher launcher =new IntentLauncher();
        launcher.start();

        Animation animFade  = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade);
        ImageView logo = (ImageView) findViewById(R.id.splashImage);
        logo.setVisibility(View.VISIBLE);
        logo.startAnimation(animFade);

        TextView txtCopy = (TextView) findViewById(R.id.txtCopyright);
        txtCopy.setVisibility(View.VISIBLE);
        txtCopy.startAnimation(animFade);

    }

    private class IntentLauncher extends Thread {
        public void run(){
            try {
                Thread.sleep(SLEEP_TIME * 500);
            }catch (Exception e) {

            }
            Intent loginIntent= new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(loginIntent);
            SplashActivity.this.finish();
        }
    }

    public void changeFonts(){
        Typeface GothamMedium = Typeface.createFromAsset(getAssets(),
                "fonts/Gotham-Medium.ttf");

        //declared forms
        TextView txtCopy = (TextView) findViewById(R.id.txtCopyright);
        //txtCopy.setTypeface(GothamMedium);
    }

}
