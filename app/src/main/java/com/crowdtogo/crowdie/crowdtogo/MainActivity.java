package com.crowdtogo.crowdie.crowdtogo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import com.actionbarsherlock.view.MenuItem;
//<<<<<<< Updated upstream
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.crowdtogo.crowdie.model.AccessTokenError;
import com.crowdtogo.crowdie.model.OrdersResponse;
import com.crowdtogo.crowdie.model.SuccessResponse;
import com.crowdtogo.crowdie.network.requests.AvailabilityRequest;
import com.crowdtogo.crowdie.network.requests.LocationRequest;
import com.crowdtogo.crowdie.network.requests.OrdersRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.RetrofitError;

//public class MainActivity extends OrdersSpiceActivity implements OnClickListener {
//=======
import com.crowdtogo.crowdie.model.AccessTokenError;
import com.crowdtogo.crowdie.model.OrdersResponse;
import com.crowdtogo.crowdie.network.requests.OrdersRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.HashMap;

import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class MainActivity extends OrdersSpiceActivity  implements OnClickListener {//SherlockFragmentActivity
//>>>>>>> Stashed changes


    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    // SLIDING MENU OPTIONS
    RelativeLayout rlProfile;
    RelativeLayout rlHome;
    RelativeLayout rlMessages;
    RelativeLayout rlNotifs;
    RelativeLayout rlSettings;
    RelativeLayout rlHelp;
    RelativeLayout rlAbout;
    RelativeLayout rlLogout;

    DBHelper ordersDB = new DBHelper(this);
    OrdersSpiceActivity orders  = new OrdersSpiceActivity();


    public static String CUR_PAGE_TITLE = "Title";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBHelper ordersDB = new DBHelper(this);

        setContentView(R.layout.activity_main);

        //String accessToken =  getDefaults("access_token", MainActivity.this);
        //Toast.makeText(MainActivity.this, "MainActivity Access Token: "+ accessToken, Toast.LENGTH_LONG).show();
        initMenu();

        Log.w("switch","Ognhaha");

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RelativeLayout) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(this, // host Activity
                mDrawerLayout, // DrawerLayout object
                R.drawable.ic_drawer, // nav drawer image to replace 'Up' image
                R.string.navigation_drawer_open, // "open drawer" description for accessibility
                R.string.navigation_drawer_close // "close drawer" description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            switchFragment(new HomeFragment());
            setTitle("Home");
            setSelected(rlHome);
            //mDrawerLayout.openDrawer(mDrawerList); // Keep drawer open everytime the application starts
        }

        getOrdersSpiceManager().execute(new OrdersRequest(getCrowdieId("crowdie_id", MainActivity.this)), "getOrders", DurationInMillis.ALWAYS_EXPIRED, new OrdersRequestListener());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.home, menu);

        Switch swi = (Switch) menu.findItem(R.id.mySwitch).getActionView().findViewById(R.id.availability);

        Log.w("switch","Initialize");

        swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                Timer timer = new Timer ();

                if (isChecked)
                {
                    Log.w("switch","On");

                    TimerTask hourlyTask = new TimerTask ()
                    {
                        @Override
                        public void run ()
                        {
                            Log.w("Timer","verna is love");
                            getAvailabilitySpiceManager().execute(new LocationRequest(1.234,5.678), "setAvailability", DurationInMillis.ALWAYS_EXPIRED, new LocationRequestListener());

                            //Toast.makeText(LoginActivity.this, "A", Toast.LENGTH_LONG).show();
                        }
                    };
                    timer.schedule (hourlyTask, 0l, 100000); // 30000 = 5 minutes
                    getAvailabilitySpiceManager().execute(new AvailabilityRequest("1"), "setAvailability", DurationInMillis.ALWAYS_EXPIRED, new AvailabilityRequestListener());

                }
                else
                {
                    Log.w("switch","Off");
                    getAvailabilitySpiceManager().execute(new AvailabilityRequest("0"), "setAvailability", DurationInMillis.ALWAYS_EXPIRED, new AvailabilityRequestListener());
                    timer.cancel();
                    timer.purge();
                    timer = null;
                    Log.w("Timer","Stop Sending location Information");
                }
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public static String getDefaults(String accessToken, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(accessToken, null);
    }

    private void initMenu() {
        rlProfile = (RelativeLayout) findViewById(R.id.rlProfile);
        rlHome = (RelativeLayout) findViewById(R.id.rlHome);
        rlMessages = (RelativeLayout) findViewById(R.id.rlMessages);
        rlNotifs = (RelativeLayout) findViewById(R.id.rlNotifs);
        rlSettings = (RelativeLayout) findViewById(R.id.rlSettings);
        rlHelp = (RelativeLayout) findViewById(R.id.rlHelp);
        rlAbout = (RelativeLayout) findViewById(R.id.rlAbout);
        rlLogout = (RelativeLayout) findViewById(R.id.rlLogout);

        rlProfile.setOnClickListener(this);
        rlHome.setOnClickListener(this);
        rlMessages.setOnClickListener(this);
        rlNotifs.setOnClickListener(this);
        rlSettings.setOnClickListener(this);
        rlHelp.setOnClickListener(this);
        rlAbout.setOnClickListener(this);
        rlLogout.setOnClickListener(this);
    }

///<<<<<<< Updated upstream

    @Override
//=======
    //@Override
//>>>>>>> Stashed changes
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {

        // update the main content by replacing fragments
        Fragment newContent = null;
        Bundle bundle = new Bundle();

        if (v.getId() == R.id.rlProfile) {
            // PROFILE
            newContent = new UserProfileFragment();
            setTitle("Profile");
            setSelected(rlProfile);
        } else if (v.getId() == R.id.rlHome) {
            // HOME
            newContent = new HomeFragment();
            setTitle("Home");
            setSelected(rlHome);
        } else if (v.getId() == R.id.rlMessages) {
            // MESSAGES
            newContent = new MessageFragment();
            setTitle("Messages");
            setSelected(rlMessages);
        } else if (v.getId() == R.id.rlNotifs) {
            // NOTIFICATIONS
            newContent = new NotificationFragment();
            setTitle("Notifications");
            setSelected(rlNotifs);
        } else if (v.getId() == R.id.rlSettings) {
            // SETTINGS
            newContent = new SettingsFragment();
            setTitle("Settings");
            setSelected(rlSettings);
        } else if (v.getId() == R.id.rlHelp) {
            // HELP
            newContent = new HelpFragment();
            setTitle("Help");
            setSelected(rlHelp);
        } else if (v.getId() == R.id.rlAbout) {
            // ABOUT
            newContent = new AboutFragment();
            setTitle("About");
            setSelected(rlAbout);
        }
        else if (v.getId() == R.id.rlLogout) {
            // LOGOUT
            Toast.makeText(getApplicationContext(), "LOGOUT", Toast.LENGTH_LONG).show();
            setSelected(rlLogout);
            resetAccessToken(MainActivity.this);
            //Back to Login page
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);

            //Stay the drawer open
            mDrawerLayout.openDrawer(mDrawerList);

            finish();
        }
        if (newContent != null) {
            newContent.setArguments(bundle);
            switchFragment(newContent);
        }
    }
    //Reset Access Token
    public static void resetAccessToken(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("access_token");
        editor.commit();
    }

    // switching fragment
    private void switchFragment(Fragment fragment) {
        mDrawerLayout.closeDrawer(mDrawerList);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    // set the selected option as enabled
    private void setSelected(RelativeLayout rl) {
        // reset all selections
        rlProfile.setSelected(false);
        rlHome.setSelected(false);
        rlMessages.setSelected(false);
        rlNotifs.setSelected(false);
        rlSettings.setSelected(false);
        rlHelp.setSelected(false);
        rlAbout.setSelected(false);
        rlLogout.setSelected(false);

        rl.setSelected(true); // set current selection
    }

    // When using the ActionBarDrawerToggle, you must call it during onPostCreate() and onConfigurationChanged()
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    //Keypress event to prevent app to back from previous acivity
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class LocationRequestListener implements RequestListener<SuccessResponse>
    {
        @Override
        public void onRequestFailure(SpiceException spiceException)
        {
            try
            {
                Log.w("myMessage", "Location Request Failed 1");

            } catch (RetrofitError e)
            {

                Log.w("myMessage", "Location Request Failed 2");
            }

        }
        @Override
        public void onRequestSuccess(SuccessResponse successResponse)
        {
           // Log.w("myMessage", "Location has been updated");
            updateScreen(successResponse);
        }
    }

    private class AvailabilityRequestListener implements RequestListener<SuccessResponse>
    {
        @Override
        public void onRequestFailure(SpiceException spiceException)
        {
            try
            {
                Log.w("myMessage", "Request Failed 1");

            } catch (RetrofitError e)
            {

                Log.w("myMessage", "Request Failed 2");
            }

        }
        @Override
        public void onRequestSuccess(SuccessResponse successResponse)
        {
            Log.w("myMessage", "Request Has Succeed");
            updateScreen2(successResponse);
        }
    }

    private void updateScreen(SuccessResponse successResponse)
    {
        if(successResponse != null )
        {
            Log.w("myMessage", successResponse.getMessage());
        }
    }

    private void updateScreen2(SuccessResponse successResponse2)
    {
        if(successResponse2 != null )
        {
            Log.w("myMessage", successResponse2.getMessage());
        }
    }




    private class OrdersRequestListener implements RequestListener<OrdersResponse> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            if (spiceException.getCause() instanceof RetrofitError) {
                RetrofitError error = (RetrofitError) spiceException.getCause();
                AccessTokenError body = (AccessTokenError) error.getBodyAs(AccessTokenError.class);
                //mProgressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Error: " + body.getError() + "\n" + "Description: " + body.getError_description(), Toast.LENGTH_LONG).show();
            }
        }

        //Success Request
        @Override
        public void onRequestSuccess(OrdersResponse ordersResponse) {
            //Toast.makeText(DeliveryDetailsActivity.this, "Success" ,Toast.LENGTH_LONG).show();
            updateOrder(ordersResponse);
        }

    };

    private void updateOrder(final OrdersResponse response){

        if(response!=null){
            // setDefaults("access_token",response.getAccess_token(),LoginActivity.this);
            for(int index = 0; index < response.getData().toArray().length; index++) {

                HashMap<String, String> queryValues = new HashMap<String, String>();

                queryValues.put("orderId",response.getData().get(index).getOrderId());
                queryValues.put("firstname", response.getData().get(index).getFirstname());
                queryValues.put("lastname", response.getData().get(index).getLastname());
                queryValues.put("destination_address", response.getData().get(index).getDestination_address());
                queryValues.put("contact", response.getData().get(index).getContact());
                queryValues.put("size", response.getData().get(index).getSize());
                queryValues.put("status", response.getData().get(index).getStatus());
                queryValues.put("destination_latitude", response.getData().get(index).getDestination_latitude());
                queryValues.put("destination_longitude", response.getData().get(index).getDestination_longitude());
                queryValues.put("merchantId", response.getData().get(index).getMerchantId());
                queryValues.put("store_name", response.getData().get(index).getStore_name());
                queryValues.put("store_contact", response.getData().get(index).getStore_contact());
                queryValues.put("pickup_address", response.getData().get(index).getPickup_address());
                queryValues.put("pickup_address_line_2", response.getData().get(index).getPickup_address_line_2());
                queryValues.put("pickup_latitude", response.getData().get(index).getPickup_latitude());
                queryValues.put("pickup_longitude", response.getData().get(index).getPickup_longitude());


                ordersDB.insertOrders(queryValues);
                //Toast.makeText(DeliveryDetailsActivity.this, "Record saved", Toast.LENGTH_LONG).show();
                //List<Orders> notes = Orders.findWithQuery(Orders.class, "Select * from ORDERS");
                //Toast.makeText(DeliveryDetailsActivity.this, notes.toArray().toString(), Toast.LENGTH_LONG).show();
            }
            //Toast.makeText(MainActivity.this, "Main: "+ ordersDB.getAllOrders(), Toast.LENGTH_LONG).show();
            //Intent mainIntent = new Intent(DeliveryDetailsActivity.this, MainActivity.class);
            //startActivity(mainIntent);
            //mProgressDialog.dismiss();
        }

    }

    //get stored crowdie_id
    public static String getCrowdieId(String accessToken, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(accessToken, null);
    }



}