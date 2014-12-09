package com.crowdtogo.crowdie.crowdtogo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.crowdtogo.crowdie.model.AccessTokenError;
import com.crowdtogo.crowdie.model.AccessTokenResponse;
import com.crowdtogo.crowdie.model.OrdersResponse;
import com.crowdtogo.crowdie.model.Token;
import com.crowdtogo.crowdie.model.UserLoginResponse;
import com.crowdtogo.crowdie.model.UsersResponse;
import com.crowdtogo.crowdie.network.requests.AccessTokenRequest;
import com.crowdtogo.crowdie.network.requests.OrdersRequest;
import com.crowdtogo.crowdie.network.requests.UserRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import retrofit.RetrofitError;

import static com.crowdtogo.crowdie.model.UserLoginResponse.*;


public class LoginActivity extends BaseSpiceActivity {

    EditText emailAddress;
    EditText password;
    EditText oAuth;
    Button btnLogin;
    Button btnForgotPass;
    ProgressDialog mProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        changeFonts();//change ui fonts

        emailAddress.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);
        oAuth.addTextChangedListener(textWatcher);
        String secret = getSecret("secret",LoginActivity.this);


        if(secret != null){
            oAuth.setText(secret);
            oAuth.setVisibility(View.GONE);
        }else{
            oAuth.setVisibility(View.VISIBLE);
        }

        //login button action
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                    // Start NewActivity.class
                if(getSecret("secret",LoginActivity.this) == null){
                    Token token = new Token();
                    token.setUsername(emailAddress.getText().toString());
                    token.setPassword(password.getText().toString());
                    token.setClient_secret(oAuth.getText().toString());//"h9Q40"
                    token.setClient_id(emailAddress.getText().toString());
                    token.setGrant_type("password");
                    token.setScope("Crowdie");
                    setSecret("secret",oAuth.getText().toString(),LoginActivity.this);


                    mProgressDialog = new ProgressDialog(LoginActivity.this);
                    // Set progressdialog title
                    mProgressDialog.setProgressStyle(AlertDialog.THEME_HOLO_DARK);
                    mProgressDialog.setTitle("CrowdToGo");
                    // Set progressdialog message
                    mProgressDialog.setMessage("Loading... \nPlease Wait");
                    mProgressDialog.setIndeterminate(false);
                    // Show progressdialog
                    mProgressDialog.show();
                    //perform login by providing valid parameters to get access token
                    getAccessTokenSpiceManager().execute(new AccessTokenRequest(token), "getAccessToken", DurationInMillis.ALWAYS_EXPIRED, new AccessRequestListener());

                }else{
                    Token token = new Token();
                    token.setUsername(emailAddress.getText().toString());
                    token.setPassword(password.getText().toString());
                    token.setClient_secret(getSecret("secret",LoginActivity.this));//"h9Q40"
                    token.setClient_id(emailAddress.getText().toString());
                    token.setGrant_type("password");
                    token.setScope("Crowdie");


                    mProgressDialog = new ProgressDialog(LoginActivity.this);
                    // Set progressdialog title
                    mProgressDialog.setProgressStyle(AlertDialog.THEME_HOLO_DARK);
                    mProgressDialog.setTitle("CrowdToGo");
                    // Set progressdialog message
                    mProgressDialog.setMessage("Loading... \nPlease Wait");
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
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(),
                        "Forgot Password Clicked", Toast.LENGTH_LONG).show();



                //temporary function to clear secret from sharedpreferences
                resetSecret(LoginActivity.this);
                Intent mainIntent = new Intent(LoginActivity.this, LoginActivity.class);
                startActivity(mainIntent);

            }
        });
    }

    public void changeFonts(){

        Typeface LatoRegular = Typeface.createFromAsset(getAssets(),
                "fonts/Lato-Regular.ttf");

        Typeface LatoBold = Typeface.createFromAsset(getAssets(),
                "fonts/Lato-Bold.ttf");

        //declared forms
        emailAddress = (EditText)findViewById(R.id.emailEditText);
        password = (EditText)findViewById(R.id.passwordEditText);
        oAuth = (EditText)findViewById(R.id.oauthEditText);
        btnLogin = (Button) findViewById(R.id.loginButton);
        btnForgotPass = (Button) findViewById(R.id.forgotPasswordButton);

        //set custom font to forms
        emailAddress.setTypeface(LatoRegular);
        password.setTypeface(LatoRegular);
        oAuth.setTypeface(LatoRegular);
        btnLogin.setTypeface(LatoBold);
        btnForgotPass.setTypeface(LatoRegular);

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
            if(emailAdd.equals("") && passWord.equals("") && oAuthKey.equals("") )//
            {
                btnLogin.setEnabled(false);
            }

            else if(!emailAdd.equals("") && passWord.equals("") && oAuthKey.equals(""))//
            {
                btnLogin.setEnabled(false);
            }

            else if(!passWord.equals("") && emailAdd.equals("") && oAuthKey.equals("") )//&&
            {
                btnLogin.setEnabled(false);
            }

            else if(emailAdd.equals("") && passWord.equals(""))//!oAuthKey.equals("") &&
            {
                btnLogin.setEnabled(false);
            }

            else if(!emailAdd.equals("") && !passWord.equals("") && oAuthKey.equals(""))//
            {
                btnLogin.setEnabled(false);
            }

            else if(!oAuthKey.equals("") && !passWord.equals("") &&  emailAdd.equals("") )//
            {
                btnLogin.setEnabled(false);
            }

            else if(!oAuthKey.equals("") && !emailAdd.equals("") && passWord.equals(""))//
            {
                btnLogin.setEnabled(false);
            }
            else if(!oAuthKey.equals("") && !emailAdd.equals("") && !passWord.equals("") )// && //&& getSecret("secret",LoginActivity.this)!= null
            {
                btnLogin.setEnabled(true);
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
                AccessTokenError body = (AccessTokenError) error.getBodyAs(AccessTokenError.class);
                mProgressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Error: "+body.getError()+ "\n" +"Description: " +body.getError_description() , Toast.LENGTH_LONG).show();
            }
        }

        //Success Request
        @Override
        public void onRequestSuccess(UserLoginResponse userLoginResponse) {

                updateScreen(userLoginResponse);
        }

    };

    private void updateScreen(final UserLoginResponse response){

        if(response!=null){
            setDefaults("access_token",response.getAccess_token(),LoginActivity.this);
            setDefaults("crowdie_id",response.getCrowdie_id(),LoginActivity.this);
           // Toast.makeText(LoginActivity.this, "Crowdie ID: "+ response.getCrowdie_id(), Toast.LENGTH_LONG).show();
            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(mainIntent);
            mProgressDialog.dismiss();
        }

    }

     //SharedPreferences for access Token
    public static void setDefaults(String accessToken, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(accessToken, value);
        editor.commit();
    }
    //get stored access token
    public static String getDefaults(String accessToken, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(accessToken, null);
    }

    //SharedPreferences for Secret
    public static void setSecret(String secret, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(secret, value);
        editor.commit();
    }
    //get stored access token
    public static String getSecret(String secret, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(secret, null);
    }
    public static void resetSecret(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        editor.remove("secret");
        editor.commit();
    }





}
