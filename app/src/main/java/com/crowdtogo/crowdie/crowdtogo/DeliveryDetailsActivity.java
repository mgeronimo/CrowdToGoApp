package com.crowdtogo.crowdie.crowdtogo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.crowdtogo.crowdie.model.AccessTokenError;
import com.crowdtogo.crowdie.model.OrdersResponse;
import com.crowdtogo.crowdie.model.UserLoginResponse;
import com.crowdtogo.crowdie.network.UsersInterface;
import com.crowdtogo.crowdie.network.requests.AccessTokenRequest;
import com.crowdtogo.crowdie.network.requests.OrdersRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;

public class DeliveryDetailsActivity extends OrdersSpiceActivity {
    String detailsName;
    String detailsDate;
    String detailsPickup;
    String detailsDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_delivery_details);

        getOrdersSpiceManager().execute(new OrdersRequest(getCrowdieId("crowdie_id", DeliveryDetailsActivity.this)), "getOrders", DurationInMillis.ALWAYS_EXPIRED, new OrdersRequestListener());


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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private class OrdersRequestListener implements RequestListener<OrdersResponse> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            if (spiceException.getCause() instanceof RetrofitError) {
                RetrofitError error = (RetrofitError) spiceException.getCause();
                AccessTokenError body = (AccessTokenError) error.getBodyAs(AccessTokenError.class);
                // mProgressDialog.dismiss();
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
            // setDefaults("access_token",response.getAccess_token(),LoginActivity.this);




            Toast.makeText(DeliveryDetailsActivity.this, "Order Details: "+ response.getData().get(0).getPickup_address_line_2().toString(), Toast.LENGTH_LONG).show();
            //Intent mainIntent = new Intent(DeliveryDetailsActivity.this, MainActivity.class);
            //startActivity(mainIntent);
            //mProgressDialog.dismiss();
        }else{
            Toast.makeText(DeliveryDetailsActivity.this, "wala pang order(s)", Toast.LENGTH_LONG).show();
        }

    }

    //get stored crowdie_id
    public static String getCrowdieId(String accessToken, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(accessToken, null);
    }

}
