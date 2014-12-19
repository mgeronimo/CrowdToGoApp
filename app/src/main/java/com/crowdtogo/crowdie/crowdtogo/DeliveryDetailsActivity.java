package com.crowdtogo.crowdie.crowdtogo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.crowdtogo.crowdie.model.AccessTokenError;
import com.crowdtogo.crowdie.model.OrdersResponse;
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

public class DeliveryDetailsActivity extends Activity {
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
    //OrdersSpiceActivity ordersSpiceActivity = new OrdersSpiceActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_delivery_details);

       // getOrdersSpiceManager().execute(new OrdersRequest(getCrowdieId("crowdie_id", DeliveryDetailsActivity.this)), "getOrders", DurationInMillis.ALWAYS_EXPIRED, new OrdersRequestListener());
       // getOrdersSpiceManager().execute(new OrdersRequest(), "getOrders", DurationInMillis.ALWAYS_EXPIRED, new OrdersRequestListener());


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

        nameTxt.setText(detailsName);
        dateTxt.setText(detailsDate);
        pickupTxt.setText(detailsPickup);
        deliveryTxt.setText(detailsDelivery);

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                this.finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


    private class OrdersRequestListener implements RequestListener<OrdersResponse> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            if (spiceException.getCause() instanceof RetrofitError) {
                RetrofitError error = (RetrofitError) spiceException.getCause();
                AccessTokenError body = (AccessTokenError) error.getBodyAs(AccessTokenError.class);
                //mProgressDialog.dismiss();
                Toast.makeText(DeliveryDetailsActivity.this, "Error: " + body.getError() + "\n" + "Description: " + body.getError_description(), Toast.LENGTH_LONG).show();
            }
        }

        //Success Request
        @Override
        public void onRequestSuccess(OrdersResponse ordersResponse) {
            //Toast.makeText(DeliveryDetailsActivity.this, "Success" ,Toast.LENGTH_LONG).show();
            updateOrder(ordersResponse);
        }

    };

    private void updateOrder(final OrdersResponse response){

        if(response!=null){


            for(int index = 0; index < response.getData().toArray().length; index++) {

                queryValues = new HashMap<String, String>();

                queryValues.put("orderId",response.getData().get(index).getOrderId());
                queryValues.put("firstname", response.getData().get(index).getFirstname());
                queryValues.put("lastname", response.getData().get(index).getLastname());
                queryValues.put("destination_address", response.getData().get(index).getDestination_address());
                queryValues.put("contact", response.getData().get(index).getContact());
                queryValues.put("size", response.getData().get(index).getSize());
                queryValues.put("status", response.getData().get(index).getStatus());
                queryValues.put("destination_latitude", response.getData().get(index).getDestination_latitude());
                queryValues.put("destination_longitude", response.getData().get(index).getDestination_longitude());
                queryValues.put("merchantId", response.getData().get(index).getMerchantId());
                queryValues.put("store_name", response.getData().get(index).getStore_name());
                queryValues.put("store_contact", response.getData().get(index).getStore_contact());
                queryValues.put("pickup_address", response.getData().get(index).getPickup_address());
                queryValues.put("pickup_address_line_2", response.getData().get(index).getPickup_address_line_2());
                queryValues.put("pickup_latitude", response.getData().get(index).getPickup_latitude());
                queryValues.put("pickup_longitude", response.getData().get(index).getPickup_longitude());

                ordersDB.insertOrders(queryValues);

//                Toast.makeText(DeliveryDetailsActivity.this, "Record saved", Toast.LENGTH_LONG).show();
                //List<Orders> notes = Orders.findWithQuery(Orders.class, "Select * from ORDERS");
                //Toast.makeText(DeliveryDetailsActivity.this, notes.toArray().toString(), Toast.LENGTH_LONG).show();
            }

        }
    }

    //get stored crowdie_id
    public static String getCrowdieId(String accessToken, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(accessToken, null);
    }

}
