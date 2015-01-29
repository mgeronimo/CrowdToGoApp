package com.crowdtogo.crowdie.crowdtogo;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

import com.crowdtogo.crowdie.model.ErrorMessage;
import com.crowdtogo.crowdie.model.OrdersResponse;
import com.crowdtogo.crowdie.model.SuccessResponse;
import com.crowdtogo.crowdie.network.OrdersSpiceService;
import com.crowdtogo.crowdie.network.requests.ConfirmationRequest;
import com.crowdtogo.crowdie.network.requests.OrdersRequest;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.HashMap;

import retrofit.RetrofitError;

public class ConfirmDeliveryRequestFragment extends SherlockFragment {

    private SpiceManager ordersSpiceManager = new SpiceManager(OrdersSpiceService.class);
    private SpiceManager confirmationSpiceManager = new SpiceManager(OrdersSpiceService.class);
    DBHelper dbHelper = new DBHelper(getSherlockActivity());
    MainActivity mainActivity = new MainActivity();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirm_delivery_request, container, false);
        DBHelper dbHelper = new DBHelper(getSherlockActivity());


        v.findViewById(R.id.accept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Toast.makeText(getActivity(), "ACCEPT " + getGroupId("groupId",getSherlockActivity()) , Toast.LENGTH_SHORT).show();
                confirmationSpiceManager().execute(new ConfirmationRequest("ACCEPT",getGroupId("groupId",getActivity())), "ConfirmationRequest", DurationInMillis.ALWAYS_EXPIRED, new ConfirmationRequestListener());

//                //---TEST
//                Fragment frag = new HomeFragment();
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.content_frame, frag);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                ft.addToBackStack(null);
//                ft.commit();
////                Intent mainIntent = new Intent(getActivity(), MainActivity.class);
////                startActivity(mainIntent);
//                //---TEST
                }

        });

        v.findViewById(R.id.reject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(getActivity(), "REJECT", Toast.LENGTH_SHORT).show();
                confirmationSpiceManager().execute(new ConfirmationRequest("REJECT",getGroupId("groupId",getActivity())), "ConfirmationRequest", DurationInMillis.ALWAYS_EXPIRED, new ConfirmationRequestListener());

            }
        });
        return v;

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
                Toast.makeText(getActivity(), "Error: " + body.getError() + "\n" + "Description: " + body.getError_description(), Toast.LENGTH_LONG).show();
            }else{
                ;
                //mProgressDialog.dismiss();
                Toast.makeText(getActivity(), "Connection Error: \nPlease check your connection then try again", Toast.LENGTH_LONG).show();
            }
        }

        //Success Request
        @Override
        public void onRequestSuccess(SuccessResponse successResponse) {
            //Toast.makeText(DeliveryDetailsActivity.this, "Success" ,Toast.LENGTH_LONG).show();

            if(successResponse!=null){

                getOrdersSpiceManager().execute(new OrdersRequest(getCrowdieId("crowdie_id", getSherlockActivity())), "getOrders", DurationInMillis.ALWAYS_EXPIRED, new OrdersRequestListener());

//                Intent mainIntent = new Intent(ConfirmDeliveryRequestActivity.this, MainActivity.class);
//                startActivity(mainIntent);
                Toast.makeText(getActivity(),"ConfirmDeliveryRequestFragment",Toast.LENGTH_LONG).show();
                Fragment frag = new HomeFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
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
                //ErrorMessage body = (ErrorMessage) error.getBodyAs(ErrorMessage.class);
                //mProgressDialog.dismiss();
                Toast.makeText(getActivity(),spiceException.getMessage() , Toast.LENGTH_LONG).show();
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
            DBHelper dbHelper = new DBHelper(getSherlockActivity());
            Toast.makeText(getActivity(), "" +response.getData().size(), Toast.LENGTH_LONG).show();
            saveGroudId("groupId",response.getData().get(0).getGroupId(),getSherlockActivity());

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
                queryValues.put("duration", response.getData().get(index).getDuration());


                dbHelper.insertOrders(queryValues);
            }


        }else{
            //  Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
        }
    }
///---OrdersRequestListener----///



    //get stored crowdie_id
    public static String getCrowdieId(String accessToken, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(accessToken, null);
    }

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

    @Override
    public void onStart() {
        confirmationSpiceManager.start(getSherlockActivity());
        ordersSpiceManager.start(getSherlockActivity());
        super.onStart();
    }
    @Override
    public void onStop() {

        confirmationSpiceManager.shouldStop();
        ordersSpiceManager.shouldStop();

        super.onStop();
    }

    protected SpiceManager confirmationSpiceManager()
    {
        return confirmationSpiceManager;

    }
    protected SpiceManager getOrdersSpiceManager(){
        return ordersSpiceManager;
    }

}
