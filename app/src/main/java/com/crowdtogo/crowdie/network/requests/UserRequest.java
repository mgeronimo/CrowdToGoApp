package com.crowdtogo.crowdie.network.requests;

/**
 * Created by User on 11/25/2014.
 */
import com.crowdtogo.crowdie.model.UsersResponse;
import com.crowdtogo.crowdie.network.UsersInterface;
import com.crowdtogo.crowdie.network.UsersInterfaceSpiceService;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

public class UserRequest extends RetrofitSpiceRequest<UsersResponse, UsersInterface> {


    public UserRequest() {
        super(UsersResponse.class, UsersInterface.class);
    }

    @Override
    public UsersResponse loadDataFromNetwork() throws Exception {
        //return getService().getData();
    return null;
    }
}
