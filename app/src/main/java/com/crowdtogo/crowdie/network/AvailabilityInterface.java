package com.crowdtogo.crowdie.network;

/**
 * Created by Edu on 12/11/2014.
 */

import com.crowdtogo.crowdie.model.AccessTokenResponse;
import com.crowdtogo.crowdie.model.OrdersResponse;
import com.crowdtogo.crowdie.model.SuccessResponse;
import com.crowdtogo.crowdie.model.Token;
import com.crowdtogo.crowdie.model.UserLoginResponse;
import com.crowdtogo.crowdie.model.UsersResponse;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;


import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;

public interface AvailabilityInterface
{

    @FormUrlEncoded
    @PUT("/api/v1/availability/1")
    SuccessResponse changeAvailability( @Field("availability") String availability );
}
