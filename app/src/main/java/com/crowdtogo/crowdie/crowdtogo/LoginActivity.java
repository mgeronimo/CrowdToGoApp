package com.crowdtogo.crowdie.crowdtogo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.crowdtogo.crowdie.model.UsersResponse;
import com.crowdtogo.crowdie.network.requests.UserRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;



public class LoginActivity extends BaseSpiceActivity {

    EditText emailAddress;
    EditText password;
    EditText oAuth;
    Button btnLogin;
    Button btnForgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        changeFonts();//change ui fonts

        emailAddress.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);
        oAuth.addTextChangedListener(textWatcher);


        //getUsersSpiceManager().execute(new UserRequest(), "UserRequest", DurationInMillis.ALWAYS_EXPIRED, new UsersRequestListener());


        //login button action
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                    // Start NewActivity.class
                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(mainIntent);
            }
        });

        //forgot password action
        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(),
                        "Forgot Password Clicked", Toast.LENGTH_LONG).show();
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
        getUsersSpiceManager().execute(new UserRequest(), "userRequest", DurationInMillis.ALWAYS_EXPIRED, new UsersRequestListener());

        //getGithubSpiceManager().execute(new GistsRequest(), "gistsRequest", DurationInMillis.ALWAYS_EXPIRED, new GithubRequestListener());
    }

    private final class UsersRequestListener implements RequestListener<UsersResponse> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(LoginActivity.this, "Failure", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onRequestSuccess(UsersResponse result) {
            //Users u = usersResponse.get(0);

            updateScreen(result);
        }
    }

    private void updateScreen(final UsersResponse result){
        //setContentView(R.layout.main);
        Toast.makeText(LoginActivity.this, result.getData().get(0).getEmail(), Toast.LENGTH_LONG).show();
        //((TextView)findViewById(R.id.cityName)).setText(result.getName());
        //((TextView)findViewById(R.id.longitudeValue)).setText(""+result.getCoord().getLon());
        //((TextView)findViewById(R.id.latitudeValue)).setText(""+result.getCoord().getLat());
        //((TextView)findViewById(R.id.temperatureValue)).setText(""+result.getMain().getTemp());
        //((TextView)findViewById(R.id.pressureValue)).setText(""+result.getMain().getPressure());
        //((TextView)findViewById(R.id.humidityValue)).setText(""+result.getMain().getHumidity());
        //((TextView)findViewById(R.id.windValue)).setText(""+result.getWind().getSpeed());
    }

}
