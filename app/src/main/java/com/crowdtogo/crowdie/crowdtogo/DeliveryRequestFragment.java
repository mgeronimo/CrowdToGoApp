package com.crowdtogo.crowdie.crowdtogo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class DeliveryRequestFragment extends SherlockFragment {

    String[] name;
    String[] date;
    String[] pickup;
    String[] delivery;
    int[] thumbnail;
    ListView list;
    DeliveryRequestListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the view from fragment_delivery_request.xml
        View view = inflater.inflate(R.layout.fragment_delivery_request, container, false);

        // Generate sample data
        name = new String[] { "The Kingfish Cafe", "The Home Depot", "Walgreens" };

        date = new String[] { "November 5, 2014", "November 5, 2014", "November 5, 2014"};

        pickup = new String[] { "602 19th Avenue East, Seattle, WA" , "7345 Delridge Way SW, Seattle, WA" ,
                "566 Denny Way, Seattle, WA" };

        delivery = new String[] { "456 Rainier Ave, Seattle, WA" , "457 Rainier Ave, Seattle, WA" ,
                "458 Rainier Ave, Seattle, WA"};

        thumbnail = new int[] { R.drawable.ic_home, R.drawable.ic_home,R.drawable.ic_home};

        // Locate the ListView in fragment_delivery_request.xml
        list = (ListView) view.findViewById(R.id.listview);

        // Pass results to ListViewAdapter Class
        adapter = new DeliveryRequestListViewAdapter(getActivity(), name, date, pickup, delivery,
                thumbnail);
        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Capture clicks on ListView items
        //list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           // @Override
           /// public void onItemClick(AdapterView<?> parent, View view,
              //                      int position, long id) {
                // Send single item click data to DeliveryDetails Class
            //    Intent DeliveryDetailsIntent = new Intent(getActivity(), DeliveryDetailsActivity.class);
                // Pass all data name
             //   DeliveryDetailsIntent.putExtra("name",name);
                // Pass all data date
             //   DeliveryDetailsIntent.putExtra("date", date);
                // Pass all data pickup
            //    DeliveryDetailsIntent.putExtra("pickup", pickup);
                // Pass all data delivery
           //     DeliveryDetailsIntent.putExtra("delivery", delivery);
                // Open DeliveryDetailsActivity.java Activity
            //    startActivity(DeliveryDetailsIntent);
           // }

        //});

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }

}