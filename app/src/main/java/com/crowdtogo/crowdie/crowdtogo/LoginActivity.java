package com.crowdtogo.crowdie.crowdtogo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends Activity {

    EditText usertxt;
    EditText passtxt;
    Button btnlogin;
    Button btnforgotpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        changeFonts();//change ui fonts

        //login button action
        btnlogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                final String email = usertxt.getText().toString();
                final String password = passtxt.getText().toString();
                //if email is valid
                if (!isValidEmail(email)) {
                    usertxt.setError("Invalid Email");
                    //if email is empty
                    if(TextUtils.isEmpty(email)){
                        usertxt.setError("Required");
                    }
                    //if password is empty
                    if(TextUtils.isEmpty(password)){
                        passtxt.setError("Required");
                    }
                }
                else {
                    // Start NewActivity.class
                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                }
            }
        });

        //forgot password action.
        btnforgotpass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(),
                        "Forgot Password Clicked", Toast.LENGTH_LONG).show();            }
        });
    }

    public void changeFonts(){

        Typeface LatoRegular = Typeface.createFromAsset(getAssets(),
                "fonts/Lato-Regular.ttf");

        Typeface LatoBold = Typeface.createFromAsset(getAssets(),
                "fonts/Lato-Bold.ttf");

        //declared forms
        usertxt = (EditText)findViewById(R.id.txtUser);
        passtxt = (EditText)findViewById(R.id.txtPassword);
        btnlogin = (Button) findViewById(R.id.btnLogin);
        btnforgotpass = (Button) findViewById(R.id.btnForgotPass);

        //set custom font to forms
        usertxt.setTypeface(LatoRegular);
        passtxt.setTypeface(LatoRegular);
        btnlogin.setTypeface(LatoBold);
        btnforgotpass.setTypeface(LatoRegular);
    }

    // validating Email Address
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
