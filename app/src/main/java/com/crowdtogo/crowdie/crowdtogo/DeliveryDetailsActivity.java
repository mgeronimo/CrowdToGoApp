package com.crowdtogo.crowdie.crowdtogo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;

public class DeliveryDetailsActivity extends Activity {
    String detailsName;
    String detailsDate;
    String detailsPickup;
    String detailsDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_delivery_details);

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
}
