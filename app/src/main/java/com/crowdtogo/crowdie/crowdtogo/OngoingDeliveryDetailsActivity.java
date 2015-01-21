package com.crowdtogo.crowdie.crowdtogo;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.crowdtogo.crowdie.model.ErrorMessage;
import com.crowdtogo.crowdie.model.SuccessResponse;
import com.crowdtogo.crowdie.network.requests.DeliveryStatusRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.HashMap;

import retrofit.RetrofitError;

public class OngoingDeliveryDetailsActivity extends OrdersSpiceActivity {
    Context context;
    String detailsName;
    String detailsDate;
    String detailsPickup;
    String detailsDelivery;
    String[] name;
    String[] date;
    String[] pickup;
    String[] delivery;
    HashMap<String, String> queryValues;

    // DB Class to perform DB related operations
    DBHelper ordersDB = new DBHelper(this);
    Button done;
    TextView nameTxt;
    TextView dateTxt;
    TextView pickupTxt;
    TextView deliveryTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar;
        actionBar=getActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_ongoing_delivery_detail);

        Intent i = getIntent();
        // Get the result of rank
        detailsName = i.getStringExtra("name");
        // Get the result of country
        detailsDate = i.getStringExtra("date");
        // Get the result of population
        detailsPickup= i.getStringExtra("pickup");
        // Get the result of flag
        detailsDelivery = i.getStringExtra("delivery");

         nameTxt=(TextView)findViewById(R.id.details_name);
         dateTxt=(TextView)findViewById(R.id.details_date);
         pickupTxt=(TextView)findViewById(R.id.details_pickupLocation);
         deliveryTxt=(TextView)findViewById(R.id.details_deliveryLocation);
         done = (Button)findViewById(R.id.done);

        nameTxt.setText(detailsName);
        dateTxt.setText(detailsDate);
        pickupTxt.setText(detailsPickup);
        deliveryTxt.setText(detailsDelivery);


        //Onclick event for ccept or Reject Order button
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent detailsIntent = new Intent(getApplicationContext(),DeliveryCodeConfirmationActivity.class);
                detailsIntent.putExtra("date",dateTxt.getText().toString());
                startActivity(detailsIntent);

            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    //get stored crowdie_id
    public static String getCrowdieId(String accessToken, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(accessToken, null);
    }

}
