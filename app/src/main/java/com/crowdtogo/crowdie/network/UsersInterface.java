package com.crowdtogo.crowdie.network;

/**
 * Created by User on 11/25/2014.
 */

import com.crowdtogo.crowdie.model.UsersResponse;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;


import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface UsersInterface {
    @GET("/api/v1/users")
    UsersResponse getData();

   // @POST("/api/v1/user")
   // void createUser(@Body User user, Callback<User> cb);
    //@GET("/gists/{id}")
    //GistDetail gist(@Path("id") String id);
}
