package com.crowdtogo.crowdie.crowdtogo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class ConfirmDeliveryRequestFragment extends SherlockFragment {


    Button accept;
    Button reject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getSherlockActivity(), DeliveryDetailsActivity.class);
        startActivity(intent);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //setContentView(R.layout.activity_delivery_details);
       // accept = getView().findViewById(R.id.accept);


//        accept.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//
//                Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
//
//
//            }
//        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirm_delivery_request, container, false);

        return v;

    }

}
