package com.crowdtogo.crowdie.crowdtogo;


import com.octo.android.robospice.SpiceManager;
import android.app.Activity;
import com.crowdtogo.crowdie.network.UsersInterfaceSpiceService;
/**
 * Created by User on 11/25/2014.
 */
public abstract class BaseSpiceActivity extends Activity {

    private SpiceManager usersSpiceManager = new SpiceManager(UsersInterfaceSpiceService.class);
   // private SpiceManager githubSpiceManager = new SpiceManager(GithubSpiceService.class);

    @Override
    protected void onStart() {
        usersSpiceManager.start(this);
        //githubSpiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        usersSpiceManager.shouldStop();
       // githubSpiceManager.shouldStop();
        super.onStop();
    }


    protected SpiceManager getUsersSpiceManager(){
        return usersSpiceManager;
    }
   // protected SpiceManager getusersSpiceManager() {
   //     return usersSpiceManager;
   // }
    //protected SpiceManager getGithubSpiceManager() { return githubSpiceManager; }


}
