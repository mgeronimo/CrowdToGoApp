package com.crowdtogo.crowdie.crowdtogo;

import android.app.ActionBar;
import android.app.LoaderManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

import com.crowdtogo.crowdie.model.ErrorMessage;
import com.crowdtogo.crowdie.model.OrdersResponse;
import com.crowdtogo.crowdie.network.OrdersSpiceService;
import com.crowdtogo.crowdie.network.requests.OrdersRequest;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.RetrofitError;

public class DeliveryRequestFragment extends SherlockFragment   {//
   Context context;
    String[] name;
    String[] date;
    String[] pickup;
    String[] delivery;
    int[] thumbnail;
    ListView list;
    MainActivity mainActivity = new MainActivity();

    DeliveryRequestListViewAdapter adapter;
    OrdersSpiceActivity orders  = new OrdersSpiceActivity();
    OrdersResponse ordersResponse = new OrdersResponse();
    private SQLiteDatabase db;
    private SpiceManager DeliveryStatusSpiceManager = new SpiceManager(OrdersSpiceService.class);


    @Override
    public void onStart() {

        DeliveryStatusSpiceManager.start(getActivity());
        super.onStart();
    }
    @Override
    public void onStop() {

        DeliveryStatusSpiceManager.shouldStop();
         super.onStop();
    }

    protected SpiceManager DeliveryStatusSpiceManager()
    {
        return DeliveryStatusSpiceManager;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

       getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_delivery_request, container, false);
        // Locate the ListView in fragment_delivery_request.xml
        list = (ListView) view.findViewById(R.id.listview);




        DBHelper ordersDB = new DBHelper(getActivity());
        ordersDB.open();
        ////---------------
        db = ordersDB.getReadableDatabase();
        String columns[] = {
                       "orderId",
//                        "firstname",
//                        "lastname",
                        "destination_address",
//                        "contact",
//                        "size",
//                        "status",
//                        "destination_latitude",
//                        "destination_longitude",
//                        "merchantId",
                        "store_name",
//                        "store_contact",
                        "pickup_address",
//                        "pickup_address_line_2",
//                        "pickup_latitude",
//                        "pickup_longitude",
//                        "pickup_date",
//                        "pickup_time"
//                      "groupId",
                      "deliveryStatus"//,
                      //"duration"

        };

        Cursor cursor = db.query("ORDERS", columns, "deliveryStatus ='0'", null, null, null, null, null);

        int orderId = cursor.getColumnIndex("orderId");
//        int firstname = cursor.getColumnIndex("firstname");
//        int lastname = cursor.getColumnIndex("lastname");
        int destination_address = cursor.getColumnIndex("destination_address");
//        int contact = cursor.getColumnIndex("contact");
//        int size = cursor.getColumnIndex("size");
//        int status = cursor.getColumnIndex("status");
//        int destination_latitude = cursor.getColumnIndex("destination_latitude");
//        int destination_longitude = cursor.getColumnIndex("destination_longitude");
//        int merchantId = cursor.getColumnIndex("merchantId");
        int store_name = cursor.getColumnIndex("store_name");
//        int store_contact = cursor.getColumnIndex("store_contact");
        int pickup_address = cursor.getColumnIndex("pickup_address");
//        int pickup_address_line_2 = cursor.getColumnIndex("pickup_address_line_2");
//        int pickup_latitude = cursor.getColumnIndex("pickup_latitude");
//        int pickup_longitude = cursor.getColumnIndex("pickup_longitude");

//         int pickup_date = cursor.getColumnIndex("pickup_date");
//        int pickup_time = cursor.getColumnIndex("pickup_time");
//        int groupId = cursor.getColumnIndex("groupId");
//        int deliveryStatus = cursor.getColumnIndex("deliveryStatus");


                ArrayList<String> arrOrderId = new ArrayList<String>(),
//                arrFirstname = new ArrayList<String>(),
//                arrLastname = new ArrayList<String>(),
                arrDestination_address= new ArrayList<String>(),
//                arrContact = new ArrayList<String>(),
//                arrSize = new ArrayList<String>(),
//                arrStatus = new ArrayList<String>(),
//                arrDestination_latitude = new ArrayList<String>(),
//                arrDestination_longitude = new ArrayList<String>(),
//                arrMerchantId = new ArrayList<String>(),
                arrStore_name = new ArrayList<String>(),
//                arrStore_contact = new ArrayList<String>(),
                arrPickup_address = new ArrayList<String>(); //,
//                arrPickup_address_line_2 = new ArrayList<String>(),
//                arrPickup_latitude = new ArrayList<String>(),
//                arrPickup_longitude= new ArrayList<String>(),
//                arrPickup_date = new ArrayList<String>(),
//                arrPickup_time= new ArrayList<String>(),
//                arrGroupId = new ArrayList<String>(),
//                arrDeliveryStatus= new ArrayList<String>();



        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

                    arrOrderId.add(cursor.getString(orderId));
//                    arrFirstname.add(cursor.getString(firstname));
//                    arrLastname.add(cursor.getString(lastname));
                   arrDestination_address.add(cursor.getString(destination_address));
//                    arrContact.add(cursor.getString(contact));
//                    arrSize.add(cursor.getString(size));
//                    arrStatus.add(cursor.getString(status));
//                    arrDestination_latitude.add(cursor.getString(destination_latitude));
//                    arrDestination_longitude.add(cursor.getString(destination_longitude));
//                    arrMerchantId.add(cursor.getString(merchantId));
                    arrStore_name.add(cursor.getString(store_name));
//                    arrStore_contact.add(cursor.getString(store_contact));
                    arrPickup_address.add(cursor.getString(pickup_address));
//                    arrPickup_address_line_2.add(cursor.getString(pickup_address_line_2));
//                    arrPickup_latitude.add(cursor.getString(pickup_latitude));
//                    arrPickup_longitude.add(cursor.getString(pickup_longitude));
//                    arrPickup_date.add(cursor.getString(pickup_date));
//                    arrPickup_time.add(cursor.getString(pickup_time));
//                    arrGroupId.add(cursor.getString(groupId));
//                    arrDeliveryStatus.add(cursor.getString(deliveryStatus));
        }

        String[] strOrderId = new String[arrOrderId.size()];
//        String[] strFirstname = new String[arrFirstname.size()];
//        String[] strLastname = new String[arrLastname.size()];
        String[] strDestination_address = new String[arrDestination_address.size()];
//        String[] strContact = new String[arrContact.size()];
//        String[] strSize = new String[arrSize.size()];
//        String[] strStatus = new String[arrStatus.size()];
//        String[] strDestination_latitude = new String[arrDestination_latitude.size()];
//        String[] strDestination_longitude = new String[arrDestination_longitude.size()];
//        String[] strMerchantId = new String[arrMerchantId.size()];
        String[] strStore_name = new String[arrStore_name.size()];
//        String[] strStore_contact = new String[arrStore_contact.size()];
        String[] strPickup_address = new String[arrPickup_address.size()];
//        String[] strPickup_address_line_2 = new String[arrPickup_address_line_2.size()];
//        String[] strPickup_latitude = new String[arrPickup_latitude.size()];
//        String[] strPickup_longitude = new String[arrPickup_longitude.size()];
//        String[] strPickup_date = new String[arrPickup_date.size()];
//        String[] strPickup_time = new String[arrPickup_time.size()];
//        String[] strGroupId = new String[arrGroupId.size()];
//        String[] strDeliveryStatus = new String[arrDeliveryStatus.size()];


        arrOrderId.toArray(strOrderId);
//        arrFirstname.toArray(strFirstname);
//        arrLastname.toArray(strLastname);
        arrDestination_address.toArray(strDestination_address);
//        arrContact.toArray(strContact);
//        arrSize.toArray(strSize);
//        arrStatus.toArray(strStatus);
//        arrDestination_latitude.toArray(strDestination_latitude);
//        arrDestination_longitude.toArray(strDestination_longitude);
//        arrMerchantId.toArray(strMerchantId);
        arrStore_name.toArray(strStore_name);
//        arrStore_contact.toArray(strStore_contact);
        arrPickup_address.toArray(strPickup_address);
//        arrPickup_address_line_2.toArray(strPickup_address_line_2);
//        arrPickup_latitude.toArray(strPickup_latitude);
//        arrPickup_longitude.toArray(strPickup_longitude);
//        arrPickup_date.toArray(strPickup_date);
//        arrPickup_time.toArray(strPickup_time);
//        arrGroupId.toArray(strGroupId);
//        arrPDeliveryStatus.toArray(strDeliveryStatus);

        ////---------------

        // Generate  data
        name = arrStore_name.toArray(strStore_name);
        date =  arrOrderId.toArray(strOrderId);
        pickup =  arrPickup_address.toArray(strPickup_address);
        delivery = arrDestination_address.toArray(strDestination_address);
        thumbnail = new int[] { R.drawable.ic_home, R.drawable.ic_home,R.drawable.ic_home};
        // Pass results to ListViewAdapter Class
        adapter = new DeliveryRequestListViewAdapter(getActivity(), name, date, pickup, delivery,thumbnail);
        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        list.refreshDrawableState();
        list.setItemsCanFocus(true);
        ((BaseAdapter)list.getAdapter()).notifyDataSetChanged();

        return view;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }

    public void refresh(){
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);
        list.invalidateViews();
        list.scrollBy(0, 0);
    }
    @Override
    public void onResume() {
        super.onResume();
       // items.clear();
        //items = dbHelper.getItems(); // reload the items from database
        adapter.notifyDataSetChanged();
    }


}