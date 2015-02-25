package com.crowdtogo.crowdie.crowdtogo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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
import com.crowdtogo.crowdie.model.SuccessResponse;
import com.crowdtogo.crowdie.network.requests.AvailabilityRequest;
import com.crowdtogo.crowdie.network.requests.ConfirmationRequest;
import com.crowdtogo.crowdie.network.requests.LocationRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import retrofit.RetrofitError;

public class GoOnlineActivity extends OrdersSpiceActivity {

	RelativeLayout rlCover;
	ImageView ivUserImage;

	RoundedImage roundedImage;
    TextView tvUserName;
    ProgressDialog mProgressDialog;
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

        mProgressDialog = new ProgressDialog(GoOnlineActivity.this);
        // Set progressdialog title
        mProgressDialog.setTitle("CrowdToGo");
        // Set progressdialog message
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        // Show progressdialog


        Button reject = (Button) findViewById(R.id.goOnline);
        reject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Go Online", Toast.LENGTH_SHORT).show();
                saveLogInStatus("loginStatus", "true", getApplicationContext());
                Intent mainIntent = new Intent(GoOnlineActivity.this, MainActivity.class);
                startActivity(mainIntent);

                getRoboSpiceManager().execute(new AvailabilityRequest(34.7177634,-92.3763751,"1",getCrowdieId("crowdie_id", GoOnlineActivity.this)), "setAvailability", DurationInMillis.ALWAYS_EXPIRED, new AvailabilityRequestListener());
                mProgressDialog.show();

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
    //get stored crowdie_id
    public static String getCrowdieId(String accessToken, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(accessToken, null);
    }
    private class AvailabilityRequestListener implements RequestListener<SuccessResponse>
    {
        @Override
        public void onRequestFailure(SpiceException spiceException)
        {
            try
            {
                Log.w("myMessage", "Request Failed 1");
                mProgressDialog.dismiss();

            } catch (RetrofitError e)
            {
                mProgressDialog.dismiss();
                Log.w("myMessage", "Request Failed 2");
            }

        }
        @Override
        public void onRequestSuccess(SuccessResponse successResponse)
        {
            Log.w("myMessage", "Request Has Succeed");
            mProgressDialog.dismiss();
            updateScreen2(successResponse);
        }
    }

    private void updateScreen(SuccessResponse successResponse)
    {
        if(successResponse != null )
        {
            Log.w("myMessage", successResponse.getMessage());
        }
    }

    private void updateScreen2(SuccessResponse successResponse2)
    {
        if(successResponse2 != null )
        {
            Log.w("myMessage", successResponse2.getMessage());
        }
    }

}
