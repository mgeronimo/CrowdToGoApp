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

//Request for the Availability of crowdie: online or offline
public class AvailabilityRequest extends RetrofitSpiceRequest<SuccessResponse,AvailabilityInterface>
{
    //private AvailabilityParam availabilityParam;
    String availabilityParam;
    String crowdieId;

    public AvailabilityRequest(String availabilityParam,String crowdieId)
    {
        super(SuccessResponse.class, AvailabilityInterface.class);
        this.availabilityParam = availabilityParam;
        this.crowdieId = crowdieId;
    }

    @Override
    public SuccessResponse loadDataFromNetwork() throws Exception
    {
        return getService().changeAvailability(availabilityParam,crowdieId);
    }
}
