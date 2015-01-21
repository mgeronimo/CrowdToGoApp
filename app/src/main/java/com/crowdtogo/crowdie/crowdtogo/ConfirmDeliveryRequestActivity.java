package com.crowdtogo.crowdie.crowdtogo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.HashMap;

import retrofit.RetrofitError;

public class ConfirmDeliveryRequestActivity extends OrdersSpiceActivity {
    Context context;
    String detailsName;
    String orderId;
    String detailsPickup;
    String detailsDelivery;
    String[] name;
    String[] date;
    String[] pickup;
    String[] delivery;
    HashMap<String, String> queryValues;

    // DB Class to perform DB related operations
    DBHelper ordersDB = new DBHelper(this);
    Button accept;
    Button reject;
    TextView code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.fragment_confirm_delivery_request);
        DBHelper ordersDB = new DBHelper(this);
        //Order Request
        getOrdersSpiceManager().execute(new OrdersRequest(getCrowdieId("crowdie_id", ConfirmDeliveryRequestActivity.this)), "getOrders", DurationInMillis.ALWAYS_EXPIRED, new OrdersRequestListener());

//        Intent i = getIntent();
//        // Get the result of country
//        orderId = i.getStringExtra("date");
        accept = (Button)findViewById(R.id.accept);
        reject = (Button)findViewById(R.id.reject);

        //Onclick event for ACCEPT
        accept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(ConfirmDeliveryRequestActivity.this, "ACCEPT " + getGroupId("groupId",ConfirmDeliveryRequestActivity.this) , Toast.LENGTH_SHORT).show();
                //confirmationSpiceManager().execute(new ConfirmationRequest("ACCEPT",getGroupId("groupId",ConfirmDeliveryRequestActivity.this)), "ConfirmationRequest", DurationInMillis.ALWAYS_EXPIRED, new ConfirmationRequestListener());

                //---TEST
                Intent mainIntent = new Intent(ConfirmDeliveryRequestActivity.this, MainActivity.class);
                startActivity(mainIntent);
                //---TEST
            }
        });
        //Onclick event for REJECT
        reject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(ConfirmDeliveryRequestActivity.this, "REJECT", Toast.LENGTH_SHORT).show();
                confirmationSpiceManager().execute(new ConfirmationRequest("REJECT",getGroupId("groupId",ConfirmDeliveryRequestActivity.this)), "ConfirmationRequest", DurationInMillis.ALWAYS_EXPIRED, new ConfirmationRequestListener());

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

    //-----ConfirmationRequestListener----///
    private class ConfirmationRequestListener implements RequestListener<SuccessResponse> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            RetrofitError error = (RetrofitError) spiceException.getCause();
            if (spiceException.getCause() instanceof RetrofitError && error.getBody() != null) {
                // RetrofitError error = (RetrofitError) spiceException.getCause();
                ErrorMessage body = (ErrorMessage) error.getBodyAs(ErrorMessage.class);
                //mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error: " + body.getError() + "\n" + "Description: " + body.getError_description(), Toast.LENGTH_LONG).show();
            }else{
                ;
                //mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Connection Error: \nPlease check your connection then try again", Toast.LENGTH_LONG).show();
            }
        }

        //Success Request
        @Override
        public void onRequestSuccess(SuccessResponse successResponse) {
            //Toast.makeText(DeliveryDetailsActivity.this, "Success" ,Toast.LENGTH_LONG).show();

            if(successResponse!=null){

                Intent mainIntent = new Intent(ConfirmDeliveryRequestActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        }

    };

    //-----ConfirmationRequestListener----///



    ///---OrdersRequestListener----///
    private class OrdersRequestListener implements RequestListener<OrdersResponse> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            if (spiceException.getCause() instanceof RetrofitError) {
                RetrofitError error = (RetrofitError) spiceException.getCause();
                ErrorMessage body = (ErrorMessage) error.getBodyAs(ErrorMessage.class);
                //mProgressDialog.dismiss();
                Toast.makeText(ConfirmDeliveryRequestActivity.this, "Error: " + body.getError() + "\n" + "Description: " + body.getError_description(), Toast.LENGTH_LONG).show();
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

        if(response.getData().size()!=0){
            //ordersDB.DeleteOrders();
            Toast.makeText(ConfirmDeliveryRequestActivity.this, response.getData().toArray().length, Toast.LENGTH_LONG).show();
            saveGroudId("groupId",response.getData().get(0).getGroupId(),ConfirmDeliveryRequestActivity.this);

            for(int index = 0; index < response.getData().toArray().length; index++) {
                HashMap<String, String> queryValues = new HashMap<String, String>();

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
                queryValues.put("pickup_date", response.getData().get(index).getPickup_date());
                queryValues.put("pickup_time", response.getData().get(index).getPickup_time());
                queryValues.put("groupId", response.getData().get(index).getGroupId());
                queryValues.put("deliveryStatus", response.getData().get(index).getDeliveryStatus());

                ordersDB.insertOrders(queryValues);
            }


        }else{
            //  Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
        }
    }
///---OrdersRequestListener----///


    //Save groudId
    private void saveGroudId(String key, String value, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //get stored groudId
    public static String getGroupId(String accessToken, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(accessToken, null);
    }


    //get stored crowdie_id
    public static String getCrowdieId(String accessToken, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(accessToken, null);
    }

}
