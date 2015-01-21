package com.crowdtogo.crowdie.crowdtogo;

import android.app.Activity;
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

import com.crowdtogo.crowdie.model.ErrorMessage;
import com.crowdtogo.crowdie.model.OrdersResponse;
import com.crowdtogo.crowdie.model.SuccessResponse;
import com.crowdtogo.crowdie.network.requests.ConfirmationRequest;
import com.crowdtogo.crowdie.network.requests.DeliveryStatusRequest;
import com.crowdtogo.crowdie.network.requests.OrdersRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import retrofit.RetrofitError;

public class DeliveryDetailsActivity extends OrdersSpiceActivity {
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
    MainActivity mainActivity = new MainActivity();
    Button start;
    //OrdersSpiceActivity ordersSpiceActivity = new OrdersSpiceActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delivery_details);

        Intent i = getIntent();
        // Get the result of rank
        detailsName = i.getStringExtra("name");
        // Get the result of country
        detailsDate = i.getStringExtra("date");
        // Get the result of population
        detailsPickup= i.getStringExtra("pickup");
        // Get the result of flag
        detailsDelivery = i.getStringExtra("delivery");

        TextView nameTxt=(TextView)findViewById(R.id.details_name);
        TextView dateTxt=(TextView)findViewById(R.id.details_date);
        TextView pickupTxt=(TextView)findViewById(R.id.details_pickupLocation);
        TextView deliveryTxt=(TextView)findViewById(R.id.details_deliveryLocation);
        start = (Button)findViewById(R.id.start);

        nameTxt.setText(detailsName);
        dateTxt.setText(detailsDate);
        pickupTxt.setText(detailsPickup);
        deliveryTxt.setText(detailsDelivery);


        //Onclick event to START the ORDER
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

//                Toast.makeText(DeliveryDetailsActivity.this, "START", Toast.LENGTH_SHORT).show();
//                DeliveryStatusSpiceManager().execute(new DeliveryStatusRequest("START",detailsDate) , "DeliveryStatusRequest", DurationInMillis.ALWAYS_EXPIRED, new DeliveryStatusRequestListener());
//                ordersDB.UpdateDeliveryStatus(detailsDate,"1");
            }
        });

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                this.finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//   }


    ////----- DeliveryStatusRequestListener ------///////////
//    private class DeliveryStatusRequestListener implements RequestListener<SuccessResponse> {
//
//        @Override
//        public void onRequestFailure(SpiceException spiceException) {
//            if (spiceException.getCause() instanceof RetrofitError) {
//                RetrofitError error = (RetrofitError) spiceException.getCause();
//                ErrorMessage body = (ErrorMessage) error.getBodyAs(ErrorMessage.class);
//                //mProgressDialog.dismiss();
//                Toast.makeText(DeliveryDetailsActivity.this, "Error: " + body.getError() + "\n" + "Description: " + body.getError_description(), Toast.LENGTH_LONG).show();
//            }
//        }
//
//        //Success Request
//        @Override
//        public void onRequestSuccess(SuccessResponse successResponse) {
//            //Toast.makeText(DeliveryDetailsActivity.this, "Success" ,Toast.LENGTH_LONG).show();
//            updateOrder(successResponse);
//        }
//
//    };
//
//    private void updateOrder(final SuccessResponse response){
//
//        if(response!=null){
//            Log.w("DeliveryDetailActivity", response.getMessage());
//        }
//    }

    ////----- DeliveryStatusRequestListener ------///////////
//
//    //get stored crowdie_id
//    public static String getCrowdieId(String accessToken, Context context) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        return preferences.getString(accessToken, null);
//    }

}
