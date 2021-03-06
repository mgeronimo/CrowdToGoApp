package com.crowdtogo.crowdie.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.GsonBuilder;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Alj on 12/5/2014.
 */
public class OrdersSpiceService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "http://devapp.crowdtogo.com";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(AvailabilityInterface.class);
        addRetrofitInterface(LocationInterface.class);
        addRetrofitInterface(DeliveryStatusInterface.class);
        addRetrofitInterface(CheckOrderCodeInterface.class);
        addRetrofitInterface(OrdersConfirmation.class);
        addRetrofitInterface(UserProfileInterface.class);
        addRetrofitInterface(UsersInterface.class);

    }

    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        return new RestAdapter.Builder()
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        //access token added to header
                        request.addHeader("Authorization",getDefaults("access_token",OrdersSpiceService.this) );
                    }
                })
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation().create()))
                .setClient(new OkClient(new OkHttpClient()))
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
