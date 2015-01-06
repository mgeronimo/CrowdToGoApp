package com.crowdtogo.crowdie.crowdtogo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class DeliveryConfirmationFragment extends SherlockFragment  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_delivery_confirmation, container, false);

        v.findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(getSherlockActivity(), "DONE", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

}
