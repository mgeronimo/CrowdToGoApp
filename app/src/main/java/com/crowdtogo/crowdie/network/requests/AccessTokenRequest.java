package com.crowdtogo.crowdie.network.requests;

import android.app.ProgressDialog;

import com.crowdtogo.crowdie.crowdtogo.LoginActivity;
import com.crowdtogo.crowdie.model.AccessTokenResponse;
import com.crowdtogo.crowdie.model.Token;
import com.crowdtogo.crowdie.model.UserLoginResponse;
import com.crowdtogo.crowdie.model.UserLoginResponse;
import com.crowdtogo.crowdie.network.UsersInterface;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import static com.crowdtogo.crowdie.model.AccessTokenResponse.*;

/**
 * Created by User on 12/1/2014.
 */
public class AccessTokenRequest extends RetrofitSpiceRequest<UserLoginResponse,UsersInterface> {

    private Token token;

    public AccessTokenRequest(Token token) {
        super(UserLoginResponse.class, UsersInterface.class);
        this.token = token;
    }

    @Override
    public UserLoginResponse loadDataFromNetwork() throws Exception {
        return getService().createUser(token.getUsername(), token.getPassword(),token.getClient_secret(), token.getClient_id(),  token.getGrant_type(), token.getScope());
    }
}
