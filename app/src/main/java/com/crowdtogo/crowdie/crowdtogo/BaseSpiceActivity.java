package com.crowdtogo.crowdie.crowdtogo;


import com.octo.android.robospice.SpiceManager;
import android.app.Activity;
import com.crowdtogo.crowdie.network.UsersInterfaceSpiceService;
/**
 * Created by User on 11/25/2014.
 */
public abstract class BaseSpiceActivity extends Activity {

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
