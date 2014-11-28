package com.crowdtogo.crowdie.network;

/**
 * Created by User on 11/25/2014.
 */
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
        addRetrofitInterface(UsersResponse.class);
    }

    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        return new RestAdapter.Builder()
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                         //access token add to header
                          // request.addHeader("Authorization", "access_token " + myaccess token);
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



}
