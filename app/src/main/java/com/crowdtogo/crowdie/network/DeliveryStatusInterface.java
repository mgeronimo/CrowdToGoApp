package com.crowdtogo.crowdie.network;

import com.crowdtogo.crowdie.model.SuccessResponse;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Alj on 12/19/2014.
 */
public interface DeliveryStatusInterface {
    @FormUrlEncoded
    @PUT("/api/v1/orders/{orderId}/delivery")
    SuccessResponse deliveryStatus( @Field("status") String status ,@Path("orderId") String orderId);//START
}
