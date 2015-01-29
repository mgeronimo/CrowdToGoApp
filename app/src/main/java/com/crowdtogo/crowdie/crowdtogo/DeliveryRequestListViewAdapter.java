package com.crowdtogo.crowdie.crowdtogo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.crowdtogo.crowdie.model.ErrorMessage;
import com.crowdtogo.crowdie.model.SuccessResponse;
import com.crowdtogo.crowdie.network.OrdersSpiceService;
import com.crowdtogo.crowdie.network.requests.DeliveryStatusRequest;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.lang.reflect.Field;
<<<<<<< Updated upstream

import javax.net.ssl.HostnameVerifier;

import retrofit.RetrofitError;
=======

import javax.net.ssl.HostnameVerifier;
>>>>>>> Stashed changes

import retrofit.RetrofitError;

public class DeliveryRequestListViewAdapter extends BaseAdapter  {
    // Declare Variables
    Context context;
    String status;

public class DeliveryRequestListViewAdapter extends BaseAdapter  {
    // Declare Variables
    Context context;
    String status;

    String[] name;
    String[] date;
    String[] pickup;
    String[] delivery;
    int[] thumbnail;
    LayoutInflater inflater;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerList;

    //DBHelper ordersDB = new DBHelper(context);
<<<<<<< Updated upstream
=======
    OrdersSpiceActivity ordersSpiceActivity = new OrdersSpiceActivity();

>>>>>>> Stashed changes

    OrdersSpiceActivity ordersSpiceActivity = new OrdersSpiceActivity();
    public DeliveryRequestListViewAdapter(Context context, String[] name, String[] date,
                                        String[] pickup, String[] delivery, int[] thumbnail) {

        this.context = context;
        this.name = name;
        this.date = date;
        this.pickup = pickup;
        this.delivery = delivery;
        this.thumbnail = thumbnail;

    }


    public int getCount() {
        return name.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView,final ViewGroup parent) {

        // Declare Variables
        final TextView txtname;
        TextView txtdate;
        TextView txtpickup;
        TextView txtdelivery;
        ImageView imgprofile;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listview_item_deliveryrequest, parent, false);

        // Locate the TextViews in listview_item.xml
        txtname = (TextView) itemView.findViewById(R.id.name);
        txtdate = (TextView) itemView.findViewById(R.id.date);
        txtpickup = (TextView) itemView.findViewById(R.id.pickupLocation);
        txtdelivery = (TextView) itemView.findViewById(R.id.deliveryLocation);

        // Locate the ImageView in listview_item.xml
        //imgprofile = (ImageView) itemView.findViewById(R.id.profilePic);

        // Capture position and set to the TextViews
        txtname.setText(name[position]);
        txtdate.setText(date[position]);
        txtpickup.setText(pickup[position]);
        txtdelivery.setText(delivery[position]);

        // Capture position and set to the ImageView
        //imgprofile.setImageResource(thumbnail[position]);

        //Onclick event for view details button
        Button start = (Button)itemView.findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

<<<<<<< Updated upstream
                //--execute request for START--//
                ordersSpiceActivity.DeliveryStatusSpiceManager().execute(new DeliveryStatusRequest("START",date[position]) , "DeliveryStatusRequest", DurationInMillis.ALWAYS_EXPIRED, new DeliveryStatusRequestListener());
=======

                //--execute request for START--//
                MainActivity mainActivity = new MainActivity();
                //mainActivity.DeliveryStatusSpiceManager().start(context);
                if( ! mainActivity.getOrdersSpiceManager().isStarted() ){
                    mainActivity.DeliveryStatusSpiceManager().start(context);
                }
                //ordersSpiceActivity.getOrdersSpiceManager().start(parent.getContext());
                mainActivity.DeliveryStatusSpiceManager().execute(new DeliveryStatusRequest("STARTED",date[position]) , "DeliveryStatusRequest", DurationInMillis.ALWAYS_EXPIRED, new DeliveryStatusRequestListener());
>>>>>>> Stashed changes
                //--execute request for START--//

                ///---Test for sqlite: Set the status to value 1(START)--///
//                status = date[position].toString();
//                DBHelper ordersDB = new DBHelper(context);
//                ordersDB.UpdateDeliveryStatus(status,"1");
//                Intent refresh = new Intent(context,MainActivity.class);
//                context.startActivity(refresh);
                //parent.refreshDrawableState();
                ///---Test for sqlite --///

            }
        });
        return itemView;
    }
    @Override
    public boolean isEnabled(int position) {
        return false;
    }






///----DeliveryStatusRequestListener----//
    private class DeliveryStatusRequestListener implements RequestListener<SuccessResponse> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            if (spiceException.getCause() instanceof RetrofitError) {
                RetrofitError error = (RetrofitError) spiceException.getCause();
                ErrorMessage body = (ErrorMessage) error.getBodyAs(ErrorMessage.class);
                Toast.makeText(context, "Error: " + body.getError() + "\n" + "Description: " + body.getError_description(), Toast.LENGTH_LONG).show();
            }
        }

        //Success Request
        @Override
        public void onRequestSuccess(SuccessResponse successResponse) {
            if(successResponse!=null){
                ///---sqlite: Set the status to value 1(START)--///
<<<<<<< Updated upstream
=======
                status = date[0].toString();
>>>>>>> Stashed changes
                DBHelper ordersDB = new DBHelper(context);
                ordersDB.UpdateDeliveryStatus(status,"1");
                Intent refresh = new Intent(context,MainActivity.class);
                context.startActivity(refresh);
                ///---sqlite --///
                Log.w("DeliveryRequestListViewAdapter", successResponse.getMessage());
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

