package com.crowdtogo.crowdie.crowdtogo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.crowdtogo.crowdie.network.requests.ConfirmationRequest;
import com.octo.android.robospice.persistence.DurationInMillis;

public class GoOnlineActivity extends SherlockFragmentActivity {

	RelativeLayout rlCover;
	ImageView ivUserImage;

	RoundedImage roundedImage;
    TextView tvUserName;

    TextView txtuserCurrentLocation;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_online);
		Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_user_temp);
		roundedImage = new RoundedImage(bm);
        //rlCover = (RelativeLayout) v.findViewById(R.id.ivCover);
        ivUserImage   = (ImageView) findViewById(R.id.ivImage);
        ivUserImage.setImageDrawable(roundedImage);


        tvUserName    = (TextView) findViewById(R.id.userProfileName);
        tvUserName.setText(getUserProfileData("name",getApplicationContext()));



        txtuserCurrentLocation = (TextView) findViewById(R.id.txtuserCurrentLocation);
        String address = getUserProfileData("address",getApplicationContext());
        if (address==null){
            address = "Current Location Unavailable";
        }
        txtuserCurrentLocation.setText(address);


        Button reject = (Button) findViewById(R.id.goOnline);
        reject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Go Online", Toast.LENGTH_SHORT).show();
                saveLogInStatus("loginStatus", "true", getApplicationContext());
                Intent mainIntent = new Intent(GoOnlineActivity.this, MainActivity.class);
                startActivity(mainIntent);

            }
        });

	}


    public void saveLogInStatus(String key, String value, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    //get stored Name
    public static String getUserProfileData(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("CrowdToGo")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent loginIntent = new Intent(GoOnlineActivity.this, LoginActivity.class);
                        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(loginIntent);
                        finish();
                    }
                }).create().show();
    }

  }
