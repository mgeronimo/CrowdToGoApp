package com.crowdtogo.crowdie.network;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.crowdtogo.crowdie.model.UsersResponse;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import com.google.gson.GsonBuilder;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

public class UsersInterfaceSpiceService extends RetrofitGsonSpiceService {


    private final static String BASE_URL = "http://devapp.crowdtogo.com";

    @Override
    public void onCreate() {
        super.onCreate();
       // addRetrofitInterface(UsersResponse.class);
        addRetrofitInterface(UsersInterface.class);
    }

    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        return new RestAdapter.Builder()
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                          //access token add to header here
                        request.addHeader("Authorization",getDefaults("access_token",UsersInterfaceSpiceService.this) );
                    }
                })
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation().create()))
                .setClient(new OkClient())
                .setLogLevel(RestAdapter.LogLevel.FULL);
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }

    public static String getDefaults(String accessToken, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(accessToken, null);
    }

}
