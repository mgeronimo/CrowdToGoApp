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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockFragmentActivity implements OnClickListener {


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


    public static String CUR_PAGE_TITLE = "Title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);


        //String accessToken =  getDefaults("access_token", MainActivity.this);
        //Toast.makeText(MainActivity.this, "MainActivity Access Token: "+ accessToken, Toast.LENGTH_LONG).show();
        initMenu();

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

    @Override
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




}