package com.crowdtogo.crowdie.network.requests;

import android.util.Log;

import com.crowdtogo.crowdie.model.SuccessResponse;
import com.crowdtogo.crowdie.network.LocationInterface;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Edu on 12/18/2014.
 */
public class LocationRequest  extends RetrofitSpiceRequest<SuccessResponse,LocationInterface>
{
    //private AvailabilityParam availabilityParam;
    Double latitude;
    Double longitude;
    String crowdieId;


    public LocationRequest(Double latitude, Double longitude,String crowdieId)
    {
        super(SuccessResponse.class, LocationInterface.class);
        this.latitude = latitude;
        this.longitude = longitude;
        this.crowdieId = crowdieId;
        Log.w("latitude", String.valueOf(latitude));
        Log.w("longitude", String.valueOf(longitude));
    }

    @Override
    public SuccessResponse loadDataFromNetwork() throws Exception
    {
        Double tempLatitude = latitude;
        Double tempLongitude = longitude;
        latitude = null;
        longitude = null;
        return getService().changeLocation(tempLatitude, tempLongitude,crowdieId);

    }
}
