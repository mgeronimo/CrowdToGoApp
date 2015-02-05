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


    private SpiceManager RoboSpiceManager = new SpiceManager(OrdersSpiceService.class);

    @Override
    protected void onStart() {

        RoboSpiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {

        RoboSpiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getRoboSpiceManager()
    {
        return RoboSpiceManager;
    }

}
