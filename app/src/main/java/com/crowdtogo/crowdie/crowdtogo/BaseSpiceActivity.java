package com.crowdtogo.crowdie.crowdtogo;


import com.crowdtogo.crowdie.network.OrdersSpiceService;
import com.octo.android.robospice.SpiceManager;
import android.app.Activity;
import com.crowdtogo.crowdie.network.UsersInterfaceSpiceService;
/**
 * Created by User on 11/25/2014.
 */
public  class BaseSpiceActivity extends Activity {

    private SpiceManager accessTokenSpiceManager = new SpiceManager(UsersInterfaceSpiceService.class);
    private SpiceManager ordersSpiceManager = new SpiceManager(OrdersSpiceService.class);

    @Override
    protected void onStart() {
        accessTokenSpiceManager.start(this);
        ordersSpiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        accessTokenSpiceManager.shouldStop();
        ordersSpiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getAccessTokenSpiceManager(){
        return accessTokenSpiceManager;
    }
    protected SpiceManager getOrdersSpiceManager(){
        return ordersSpiceManager;
    }


}
