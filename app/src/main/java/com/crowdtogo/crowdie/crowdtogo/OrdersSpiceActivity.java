package com.crowdtogo.crowdie.crowdtogo;

import android.app.Activity;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.crowdtogo.crowdie.network.OrdersSpiceService;
import com.octo.android.robospice.SpiceManager;

/**
 * Created by User on 12/9/2014.
 */
public class OrdersSpiceActivity extends SherlockFragmentActivity {


    private SpiceManager ordersSpiceManager = new SpiceManager(OrdersSpiceService.class);

    @Override
    protected void onStart() {
        ordersSpiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        ordersSpiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getOrdersSpiceManager(){
        return ordersSpiceManager;
    }


}
