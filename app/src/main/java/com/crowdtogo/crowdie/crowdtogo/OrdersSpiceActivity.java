package com.crowdtogo.crowdie.crowdtogo;

import android.app.Activity;
import android.util.Log;


import com.actionbarsherlock.app.SherlockFragment;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.crowdtogo.crowdie.network.OrdersSpiceService;
import com.octo.android.robospice.SpiceManager;

/**
 * Created by Alj on 12/9/2014.
 */
public class OrdersSpiceActivity extends SherlockFragmentActivity {

    private SpiceManager ordersSpiceManager = new SpiceManager(OrdersSpiceService.class);
    private SpiceManager availabilitySpiceManager = new SpiceManager(OrdersSpiceService.class);
    private SpiceManager locationSpiceManager = new SpiceManager(OrdersSpiceService.class);
    private SpiceManager confirmationSpiceManager = new SpiceManager(OrdersSpiceService.class);
    private SpiceManager DeliveryStatusSpiceManager = new SpiceManager(OrdersSpiceService.class);
    private SpiceManager CheckOrderCodeSpiceManager = new SpiceManager(OrdersSpiceService.class);

    @Override
    protected void onStart() {
        ordersSpiceManager.start(this);
        availabilitySpiceManager.start(this);
        locationSpiceManager.start(this);
        confirmationSpiceManager.start(this);
        DeliveryStatusSpiceManager.start(this);
        CheckOrderCodeSpiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        ordersSpiceManager.shouldStop();
        availabilitySpiceManager.shouldStop();
        locationSpiceManager.shouldStop();
        confirmationSpiceManager.shouldStop();
        DeliveryStatusSpiceManager.shouldStop();
        CheckOrderCodeSpiceManager.shouldStop();
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

    protected SpiceManager confirmationSpiceManager()
    {
        return confirmationSpiceManager;
    }

    protected SpiceManager DeliveryStatusSpiceManager()
    {
        return DeliveryStatusSpiceManager;
    }

    protected SpiceManager getCheckOrderCodeSpiceManager()
    {
        return CheckOrderCodeSpiceManager;
    }




}
