package com.crowdtogo.crowdie.network;

/**
 * Created by User on 11/25/2014.
 */

import com.crowdtogo.crowdie.model.OrdersResponse;
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

public interface UsersInterface {


    //@Multipart
    @GET("/api/v1/orders/crowdie/{crowdie}")
    OrdersResponse getOrder(@Path("crowdie") String crowdieId);

    @FormUrlEncoded
    @POST("/oauth/access_token")
    UserLoginResponse createUser(@Field("username") String username,
                                 @Field("password") String password,
                                 @Field("client_secret") String client_secret,
                                 @Field("client_id") String client_id,
                                 @Field("grant_type") String grant_type,
                                 @Field("scope") String scope);

    @FormUrlEncoded
    @PUT("/api/v1/orders/confirm/{id}")
    OrdersResponse confirm(@Field("id") String crowdieId);

}
