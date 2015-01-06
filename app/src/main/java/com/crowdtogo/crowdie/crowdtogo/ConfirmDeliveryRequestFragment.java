package com.crowdtogo.crowdie.crowdtogo;

//<<<<<<< HEAD
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
//=======
import android.content.Intent;
import android.os.Bundle;
//>>>>>>> 0c487dfa1a583ed25a16d1e51e4d1abd90476608
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
//<<<<<<< HEAD
import com.crowdtogo.crowdie.model.ErrorMessage;
import com.crowdtogo.crowdie.model.SuccessResponse;
import com.crowdtogo.crowdie.network.OrdersSpiceService;
import com.crowdtogo.crowdie.network.requests.ConfirmationRequest;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import retrofit.RetrofitError;
//=======
//>>>>>>> 0c487dfa1a583ed25a16d1e51e4d1abd90476608

public class ConfirmDeliveryRequestFragment extends SherlockFragment {


//<<<<<<< HEAD
    private SpiceManager confirmationSpiceManager = new SpiceManager(OrdersSpiceService.class);
    OrdersSpiceActivity ordersSpiceActivity  = new OrdersSpiceActivity();
    MainActivity mainActivity = new MainActivity();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Intent intent = new Intent(getSherlockActivity(), DeliveryDetailsActivity.class);
        //startActivity(intent);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //setContentView(R.layout.activity_delivery_details);
       // accept = getView().findViewById(R.id.accept);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirm_delivery_request, container, false);
        v.findViewById(R.id.accept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Toast.makeText(getSherlockActivity(), "ACCEPT", Toast.LENGTH_SHORT).show();
                confirmationSpiceManager().execute(new ConfirmationRequest("ACCEPT","17"), "ConfirmationRequest", DurationInMillis.ALWAYS_EXPIRED, new ConfirmationRequestListener());

                }

        });

        v.findViewById(R.id.reject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(getSherlockActivity(), "REJECT", Toast.LENGTH_SHORT).show();
                confirmationSpiceManager().execute(new ConfirmationRequest("REJECT","17"), "ConfirmationRequest", DurationInMillis.ALWAYS_EXPIRED, new ConfirmationRequestListener());

            }
        });
        return v;

    }

    private class ConfirmationRequestListener implements RequestListener<SuccessResponse> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            if (spiceException.getCause() instanceof RetrofitError) {
                RetrofitError error = (RetrofitError) spiceException.getCause();
                ErrorMessage body = (ErrorMessage) error.getBodyAs(ErrorMessage.class);
                //mProgressDialog.dismiss();
                Toast.makeText(getActivity(), "Error: " + body.getError() + "\n" + "Description: " + body.getError_description(), Toast.LENGTH_LONG).show();
            }
        }

        //Success Request
        @Override
        public void onRequestSuccess(SuccessResponse successResponse) {
            //Toast.makeText(DeliveryDetailsActivity.this, "Success" ,Toast.LENGTH_LONG).show();
            updateOrder(successResponse);
        }

    };

    private void updateOrder(final SuccessResponse response){

        if(response!=null){
            Toast.makeText(getActivity(),"ConfirmDeliveryRequestFragment",Toast.LENGTH_LONG).show();
            Fragment frag = new HomeFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, frag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }else{
            Toast.makeText(getActivity(),"empty",Toast.LENGTH_LONG).show();

        }
    }


    //get stored crowdie_id
    public static String getCrowdieId(String accessToken, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(accessToken, null);
    }

    @Override
    public void onStart() {
        confirmationSpiceManager.start(getSherlockActivity());
        super.onStart();
    }
    @Override
    public void onStop() {

        confirmationSpiceManager.shouldStop();

        super.onStop();
    }

    protected SpiceManager confirmationSpiceManager()
    {
        return confirmationSpiceManager;
////=======
//    Button accept;
//    Button reject;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(getSherlockActivity(), DeliveryDetailsActivity.class);
//        startActivity(intent);
//        //getActionBar().setDisplayHomeAsUpEnabled(true);
//        //setContentView(R.layout.activity_delivery_details);
//       // accept = getView().findViewById(R.id.accept);
//
//
////        accept.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View view) {
////
////                Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
////
////
////            }
////        });
//
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_confirm_delivery_request, container, false);
//
//        return v;
//
/////>>>>>>> 0c487dfa1a583ed25a16d1e51e4d1abd90476608
    }

}
