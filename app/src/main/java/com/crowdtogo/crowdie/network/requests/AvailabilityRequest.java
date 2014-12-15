package com.crowdtogo.crowdie.network.requests;

import android.util.Log;

import com.crowdtogo.crowdie.model.SuccessResponse;
import com.crowdtogo.crowdie.model.Token;
import com.crowdtogo.crowdie.model.UserLoginResponse;
import com.crowdtogo.crowdie.network.AvailabilityInterface;
import com.crowdtogo.crowdie.network.UsersInterface;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by Edu on 12/11/2014.
 */
public class AvailabilityRequest extends RetrofitSpiceRequest<SuccessResponse,AvailabilityInterface>
{
    //private AvailabilityParam availabilityParam;
    String availabilityParam;


    public AvailabilityRequest(String availabilityParam)
    {
        super(SuccessResponse.class, AvailabilityInterface.class);
        Log.w("myApp", "You passed Availability Request");
        Log.w("myApp", availabilityParam);
        this.availabilityParam = availabilityParam;

    }

    @Override
    public SuccessResponse loadDataFromNetwork() throws Exception
    {
        Log.w("myApp", "You passed loadDataFromNetwork");
        Log.w("myApp", availabilityParam);
        return getService().changeAvailability(availabilityParam);
    }
}
