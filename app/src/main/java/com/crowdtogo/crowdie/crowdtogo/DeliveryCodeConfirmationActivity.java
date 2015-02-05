package com.crowdtogo.crowdie.crowdtogo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.crowdtogo.crowdie.model.ErrorMessage;
import com.crowdtogo.crowdie.model.SuccessResponse;
import com.crowdtogo.crowdie.network.requests.CheckOrderCodeRequest;
import com.crowdtogo.crowdie.network.requests.DeliveryStatusRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.HashMap;

import retrofit.RetrofitError;

public class DeliveryCodeConfirmationActivity extends OrdersSpiceActivity {
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
    Button enterCode;
    TextView code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.fragment_delivery_code_confirmation);

        Intent i = getIntent();
        // Get the result of country
        orderId = i.getStringExtra("date");

        code =(TextView)findViewById(R.id.codeEditText);
        enterCode = (Button)findViewById(R.id.enterCode);

        //Onclick event for 5 digit unique code
        enterCode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if(code.equals(" ")){
                    Toast.makeText(getApplicationContext(),"Please enter the valid code",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),code.getText().toString() +"  "+orderId,Toast.LENGTH_LONG).show();
                    //CheckOrderCode request
                    getRoboSpiceManager().execute(new CheckOrderCodeRequest(code.getText().toString(),orderId), "CheckOrderCodeRequest", DurationInMillis.ALWAYS_EXPIRED, new  CheckOrderCodeRequestListener());

                    ///---TEST for sqlite: Set the status value to 0--///
//                    DBHelper ordersDB = new DBHelper(getApplicationContext());
//                    ordersDB.UpdateDeliveryStatus(orderId,"0");
//                    Intent refresh = new Intent(getApplicationContext(),MainActivity.class);
//                    refresh.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    getApplicationContext().startActivity(refresh);
                    ///---sqlite --///
                }
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

////----- CheckOrderCodeRequestListener ------///////////
    private class CheckOrderCodeRequestListener implements RequestListener<SuccessResponse> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            if (spiceException.getCause() instanceof RetrofitError) {
                RetrofitError error = (RetrofitError) spiceException.getCause();
                //ErrorMessage body = (ErrorMessage) error.getBodyAs(ErrorMessage.class);
                Toast.makeText(getApplicationContext(),"Please enter valid code", Toast.LENGTH_LONG).show();
            }
        }
        //Success Request
        @Override
        public void onRequestSuccess(SuccessResponse successResponse) {
            if(successResponse!=null){
                Log.w("DeliveryCodeConfirmationActivity", successResponse.getMessage());


                //--execute request for DONE--//
                getRoboSpiceManager().execute(new DeliveryStatusRequest("DONE",orderId) , "CheckOrderCodeRequestListener", DurationInMillis.ALWAYS_EXPIRED, new DeliveryStatusRequestListener());
                //--execute request for DONE--//
            }
        }

    };
    ////----- CheckOrderCodeRequestListener ------///////////


    ///----DeliveryStatusRequestListener----//
    private class DeliveryStatusRequestListener implements RequestListener<SuccessResponse> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            RetrofitError error = (RetrofitError) spiceException.getCause();
            if (spiceException.getCause() instanceof RetrofitError && error.getBody() != null) {
                // RetrofitError error = (RetrofitError) spiceException.getCause();
                ErrorMessage body = (ErrorMessage) error.getBodyAs(ErrorMessage.class);
                //mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error: " + body.getError() + "\n" + "Description: " + body.getError_description(), Toast.LENGTH_LONG).show();
            }else{

                //mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Connection Error: \nCheck your connection then try again", Toast.LENGTH_LONG).show();
            }
        }
        //Success Request
        @Override
        public void onRequestSuccess(SuccessResponse successResponse) {
            if(successResponse!=null){

                ///---sqlite: Set the status value to 2(DONE)--///
                DBHelper ordersDB = new DBHelper(DeliveryCodeConfirmationActivity.this);
                ordersDB.UpdateDeliveryStatus(orderId,"2");
                Intent refresh = new Intent(DeliveryCodeConfirmationActivity.this,DeliveryCompletedActivity.class);
                DeliveryCodeConfirmationActivity.this.startActivity(refresh);
                Toast.makeText(getApplicationContext(), successResponse.getMessage(), Toast.LENGTH_LONG).show();
                ///---sqlite --///

            }
        }
    };
    ///----DeliveryStatusRequestListener----//


    //get stored crowdie_id
    public static String getCrowdieId(String accessToken, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(accessToken, null);
    }

}
