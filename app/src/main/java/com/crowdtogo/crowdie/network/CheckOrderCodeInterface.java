package com.crowdtogo.crowdie.network;

import com.crowdtogo.crowdie.model.SuccessResponse;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Alj on 01/9/2015
 */

// confirm order completion using a five digit code.
public interface CheckOrderCodeInterface {

    @FormUrlEncoded
    @PUT("/api/v1/orders/confirm/{id}")
    SuccessResponse checkOrderCode(@Field("code") String action, @Path("id") String groupId);// orderId
}
