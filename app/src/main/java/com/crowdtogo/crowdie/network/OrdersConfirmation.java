package com.crowdtogo.crowdie.network;

import com.crowdtogo.crowdie.model.SuccessResponse;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Alj on 12/19/2014.
 */

// Accept and Reject Group Orders
public interface OrdersConfirmation {
    @FormUrlEncoded
    @PUT("/api/v1/orders/group/{groupId}/delivery")
    SuccessResponse confirmation( @Field("action") String action ,@Path("groupId") String groupId);//in API: userId
}
