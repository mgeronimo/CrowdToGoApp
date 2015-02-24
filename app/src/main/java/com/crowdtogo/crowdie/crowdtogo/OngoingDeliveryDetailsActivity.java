package com.crowdtogo.crowdie.crowdtogo;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

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
    Button contactCustomer;
    TextView nameTxt;
    TextView dateTxt;
    TextView pickupTxt;
    TextView deliveryTxt;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar;
        actionBar=getActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_ongoing_delivery_detail);

        GPSTracker gps = new GPSTracker(OngoingDeliveryDetailsActivity.this);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        googleMap = mapFragment.getMap();
        //googleMap.setMyLocationEnabled(true); // This will add a blue dot showing your location

        if(gps.canGetLocation())
        {
            final double latitude = gps.getLatitude();
            final double longitude = gps.getLongitude();

            LatLng PERTH = new LatLng(latitude, longitude);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PERTH, 19));
            Marker perth = googleMap.addMarker(new MarkerOptions()
                    .position(PERTH)
                    .title("You are Here")
                    .draggable(true));
        }
        else
        {
            gps.showSettingsAlert();
        }


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
        contactCustomer = (Button)findViewById(R.id.contactCustomer);

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

        //Onclick event for call activity
        contactCustomer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                messageDialog("Contact Customer", "Call or Text Customer", OngoingDeliveryDetailsActivity.this);
            }
        });

    }

    public void messageDialog(String title, String message, Context activity) {

        final Dialog myDialog = new Dialog(activity);
        myDialog.setContentView(R.layout.activity_contact_customer);
        myDialog.setTitle(title);
        //myDialog.setCancelable(false);
        myDialog.setCanceledOnTouchOutside(true);
        Window window = this.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        try {
            EndCallListener callListener = new EndCallListener();
            TelephonyManager mTM = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
            mTM.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);
        } catch(Exception e) {
            Log.e("callMonitor", "Exception: "+e.toString());
        }



        Button btn_call = (Button) myDialog.findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //myDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Call Customer" ,Toast.LENGTH_LONG).show();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:09277010228"));
                startActivity(callIntent);
            }
        });

        Button btn_sms = (Button) myDialog.findViewById(R.id.btn_sms);
        btn_sms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //myDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Text Customer" ,Toast.LENGTH_LONG).show();
                Intent detailsIntent = new Intent(getApplicationContext(),SMSActivity.class);
                startActivity(detailsIntent);

            }
        });
        myDialog.show();
    }


    private class EndCallListener extends PhoneStateListener {
        private boolean active = false;
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if(TelephonyManager.CALL_STATE_RINGING == state) {
                Log.i("EndCallListener", "RINGING, number: " + incomingNumber);
            }
            if(TelephonyManager.CALL_STATE_OFFHOOK == state) {
                //wait for phone to go offhook (probably set a boolean flag) so you know your app initiated the call.
                active = true;
                Log.i("EndCallListener", "OFFHOOK");
            }
            if(TelephonyManager.CALL_STATE_IDLE == state) {
                //when this state occurs, and your flag is set, restart your app
                Log.i("EndCallListener", "IDLE");
                if (active) {
                    active = false;
                    // stop listening
                    TelephonyManager mTM = (TelephonyManager) getApplicationContext().getSystemService( Context.TELEPHONY_SERVICE );
                    mTM.listen(this, PhoneStateListener.LISTEN_NONE);
                }
            }
        }
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
