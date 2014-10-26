package com.crowdtogo.crowdie.crowdtogo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends Activity {

    TextView txtTitle;
    EditText usertxt;
    EditText passtxt;
    Button btnlogin;
    Button btnforgotpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        changeFonts();//change ui fonts

        // Capture button clicks
        btnlogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Start NewActivity.class
                Intent myIntent = new Intent(LoginActivity.this,
                        MainActivity.class);
                startActivity(myIntent);
            }
        });

    }

    public void changeFonts(){

        Typeface gothamBook = Typeface.createFromAsset(getAssets(),
                "fonts/Gotham-Book.ttf");

        Typeface gothamBold = Typeface.createFromAsset(getAssets(),
                "fonts/Gotham-Bold.ttf");

        //declared forms
        usertxt = (EditText)findViewById(R.id.txtUser);
        passtxt = (EditText)findViewById(R.id.txtPassword);
        btnlogin = (Button) findViewById(R.id.btnLogin);
        btnforgotpass = (Button) findViewById(R.id.btnForgotPass);

        //set custom font to forms
        usertxt.setTypeface(gothamBook);
        passtxt.setTypeface(gothamBook);
        btnlogin.setTypeface(gothamBold);
        btnforgotpass.setTypeface(gothamBook);
    }

    //Keypress event to prevent app to back from previous acivity
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
