package com.crowdtogo.crowdie.crowdtogo;


import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.crowdtogo.crowdie.network.OrdersSpiceService;
import com.octo.android.robospice.SpiceManager;
import android.app.Activity;
import com.crowdtogo.crowdie.network.UsersInterfaceSpiceService;
/**
 * Created by Alj on 11/25/2014.
 */
public  class BaseSpiceActivity extends SherlockFragmentActivity {


    private SpiceManager accessTokenSpiceManager = new SpiceManager(UsersInterfaceSpiceService.class);

    @Override
    protected void onStart() {
        accessTokenSpiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        accessTokenSpiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getAccessTokenSpiceManager(){
        return accessTokenSpiceManager;
    }


}
