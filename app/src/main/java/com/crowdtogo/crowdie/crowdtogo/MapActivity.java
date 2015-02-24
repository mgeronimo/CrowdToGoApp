package com.crowdtogo.crowdie.crowdtogo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.crowdtogo.crowdie.network.requests.AvailabilityRequest;
import com.crowdtogo.crowdie.network.requests.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.octo.android.robospice.persistence.DurationInMillis;

import java.util.TimerTask;

/**
 * Created by Edu on 1/15/2015.
 */
public class MapActivity extends SherlockFragmentActivity{

    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.fragment_about);
        GPSTracker gps = new GPSTracker(MapActivity.this);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        googleMap = mapFragment.getMap();
        //googleMap.setMyLocationEnabled(true); // This will add a blue dot showing your location

        if(gps.canGetLocation())
        {
            final double latitude = gps.getLatitude();
            final double longitude = gps.getLongitude();

            LatLng PERTH = new LatLng(latitude, longitude);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PERTH, 19));
            Marker perth = googleMap.addMarker(new MarkerOptions()
                    .position(PERTH)
                    .title("You are Here")
                    .draggable(true));
        }
        else
        {
            gps.showSettingsAlert();
        }


    }
}