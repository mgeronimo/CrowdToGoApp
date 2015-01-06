package com.crowdtogo.crowdie.crowdtogo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crowdtogo.crowdie.model.ErrorMessage;
import com.crowdtogo.crowdie.model.Token;
import com.crowdtogo.crowdie.model.UserLoginResponse;
import com.crowdtogo.crowdie.network.requests.AccessTokenRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.Timer;
import java.util.TimerTask;


import retrofit.RetrofitError;


public class LoginActivity extends BaseSpiceActivity {

    EditText emailAddress;
    EditText password;
    EditText oAuth;
    Button btnLogin;
    Button btnForgotPass;
    ProgressDialog mProgressDialog;
    String secret;

    LocationManager locationManager;
    LocationListener locationListener;
    double latitude;
    double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        changeFonts();//change ui fonts


        secret = getSecret("secret",LoginActivity.this);
        if(secret != null){
            oAuth.setVisibility(View.INVISIBLE);
            oAuth.setText(secret);
            //sample
            emailAddress.setText("crowdie_2@gmail.com");
            password.setText("crowdie");
        }

        emailAddress.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);
        oAuth.addTextChangedListener(textWatcher);

        //loginbutton action
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                    // Start NewActivity.class
                if(secret!= null){
                    Token token = new Token();
                    token.setUsername(emailAddress.getText().toString());
                    token.setPassword(password.getText().toString());

                    token.setClient_secret(secret);//"h9Q40"
                    token.setClient_id(emailAddress.getText().toString());
                    token.setGrant_type("password");
                    token.setScope("Crowdie");
                    mProgressDialog = new ProgressDialog(LoginActivity.this);
                    // Set progressdialog title
                    mProgressDialog.setTitle("CrowdToGo");
                    // Set progressdialog message
                    mProgressDialog.setMessage("Loading...");
                    mProgressDialog.setIndeterminate(false);
                    // Show progressdialog
                    mProgressDialog.show();
                    //perform login by providing valid parameters to get access token
                    getAccessTokenSpiceManager().execute(new AccessTokenRequest(token), "getAccessToken", DurationInMillis.ALWAYS_EXPIRED, new AccessRequestListener());

                }else {

                    Token token = new Token();
                    token.setUsername(emailAddress.getText().toString());
                    token.setPassword(password.getText().toString());
                    token.setClient_secret(oAuth.getText().toString());//"h9Q40"
                    saveSecret("secret",oAuth.getText().toString(), LoginActivity.this);
                    token.setClient_id(emailAddress.getText().toString());
                    token.setGrant_type("password");
                    token.setScope("Crowdie");
                    mProgressDialog = new ProgressDialog(LoginActivity.this);
                    // Set progressdialog title
                    mProgressDialog.setTitle("CrowdToGo");
                    // Set progressdialog message
                    mProgressDialog.setMessage("Loading...");
                    mProgressDialog.setIndeterminate(false);
                    // Show progressdialog
                    mProgressDialog.show();
                    //perform login by providing valid parameters to get access token
                    getAccessTokenSpiceManager().execute(new AccessTokenRequest(token), "getAccessToken", DurationInMillis.ALWAYS_EXPIRED, new AccessRequestListener());
                }

            }
        });

        //forgot password action
        btnForgotPass.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0)
            {
//                Toast.makeText(getApplicationContext(),
//                        "Forgot Password Clicked", Toast.LENGTH_LONG).show();
                deleteSecret(LoginActivity.this);
                oAuth.setVisibility(View.VISIBLE);
                Intent mainIntent = new Intent(LoginActivity.this, LoginActivity.class);
                startActivity(mainIntent);

                GPSTracker gps = new GPSTracker(LoginActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
       });
    }

    public void changeFonts(){

        Typeface GothamMedium = Typeface.createFromAsset(getAssets(),
                "fonts/Gotham-Medium.ttf");

        Typeface GothamLight = Typeface.createFromAsset(getAssets(),
                "fonts/Gotham-Light.ttf");

        //declared forms
        emailAddress = (EditText)findViewById(R.id.emailEditText);
        password = (EditText)findViewById(R.id.passwordEditText);
        oAuth = (EditText)findViewById(R.id.oauthEditText);
        btnLogin = (Button) findViewById(R.id.loginButton);
        btnForgotPass = (Button) findViewById(R.id.forgotPasswordButton);

        emailAddress.setText("crowdie_2@gmail.com");
        password.setText("crowdie");
        oAuth.setText("SEC01");
        btnLogin.setEnabled(true);

        //set custom font to forms
        /*emailAddress.setTypeface(GothamMedium);
        password.setTypeface(GothamMedium);
        oAuth.setTypeface(GothamMedium);
        btnLogin.setTypeface(GothamMedium);
        btnForgotPass.setTypeface(GothamMedium);*/

    }

    //TextWatcher
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
        {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private void checkFieldsForEmptyValues(){

            final String emailAdd = emailAddress.getText().toString();
            final String passWord = password.getText().toString();
            final String oAuthKey = oAuth.getText().toString();

            //if fields are empty
            if(emailAdd.equals("") && passWord.equals("") && oAuthKey.equals(""))
            {
                btnLogin.setEnabled(false);
            }

            else if(!emailAdd.equals("") && passWord.equals("") && oAuthKey.equals(""))
            {
                btnLogin.setEnabled(false);
            }

            else if(!passWord.equals("") && oAuthKey.equals("") && emailAdd.equals(""))
            {
                btnLogin.setEnabled(false);
            }

            else if(!oAuthKey.equals("") && emailAdd.equals("") && passWord.equals(""))
            {
                btnLogin.setEnabled(false);
            }

            else if(!emailAdd.equals("") && !passWord.equals("") && oAuthKey.equals(""))
            {
                btnLogin.setEnabled(false);
            }

            else if(!passWord.equals("") && !oAuthKey.equals("") && emailAdd.equals(""))
            {
                btnLogin.setEnabled(false);
            }

            else if(!oAuthKey.equals("") && !emailAdd.equals("") && passWord.equals(""))
            {
                btnLogin.setEnabled(false);
            }

            else
            {
                btnLogin.setEnabled(true);

            }
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

    @Override
    protected void onStart() {
        super.onStart();
}

    //Request listener
    private class AccessRequestListener implements RequestListener<UserLoginResponse> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            if (spiceException.getCause() instanceof RetrofitError) {
                RetrofitError error = (RetrofitError) spiceException.getCause();
                ErrorMessage body = (ErrorMessage) error.getBodyAs(ErrorMessage.class);
                mProgressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Error: " + body.getError() + "\n" + "Description: " + body.getError_description(), Toast.LENGTH_LONG).show();
            }

        }
        @Override
        public void onRequestSuccess(UserLoginResponse userLoginResponse) {
            updateScreen(userLoginResponse);

        }
    }

    private void updateScreen(final UserLoginResponse response){
        if(response!=null){

            saveAccessToken("access_token", response.getAccess_token(), LoginActivity.this);
            saveCrowdieID("crowdie_id", response.getCrowdie_id(), LoginActivity.this);
            //Toast.makeText(LoginActivity.this, "Access Token: "+ response.getAccess_token(), Toast.LENGTH_LONG).show();

            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(mainIntent);
            mProgressDialog.dismiss();

        }


    }


    //Save Access Token
    private void saveAccessToken(String key, String value, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    //Save CrowdieID
    private void saveCrowdieID(String key, String value, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    private void saveSecret(String key, String value, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private void deleteSecret(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("secret");

        editor.commit();
    }

    //get stored Secret
    public static String getSecret(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

}
