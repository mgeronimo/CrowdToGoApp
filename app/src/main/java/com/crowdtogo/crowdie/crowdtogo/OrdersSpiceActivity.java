package com.crowdtogo.crowdie.crowdtogo;

import android.app.Activity;
import android.util.Log;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.crowdtogo.crowdie.network.OrdersSpiceService;
import com.octo.android.robospice.SpiceManager;

/**
 * Created by User on 12/9/2014.
 */
public class OrdersSpiceActivity extends SherlockFragmentActivity {

    private SpiceManager ordersSpiceManager = new SpiceManager(OrdersSpiceService.class);
    private SpiceManager availabilitySpiceManager = new SpiceManager(OrdersSpiceService.class);
    private SpiceManager locationSpiceManager = new SpiceManager(OrdersSpiceService.class);

    @Override
    protected void onStart() {
        ordersSpiceManager.start(this);
        availabilitySpiceManager.start(this);
        locationSpiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        ordersSpiceManager.shouldStop();
        availabilitySpiceManager.shouldStop();
        locationSpiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getOrdersSpiceManager(){
        return ordersSpiceManager;
    }

    protected SpiceManager getAvailabilitySpiceManager()
    {
        return availabilitySpiceManager;
    }

    protected SpiceManager getLocationSpiceManager()
    {
        return locationSpiceManager;
    }




}
