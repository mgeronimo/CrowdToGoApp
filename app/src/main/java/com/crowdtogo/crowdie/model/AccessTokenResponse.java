package com.crowdtogo.crowdie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by User on 12/1/2014.
 */
public class AccessTokenResponse {



    @Expose
    private List<UserLoginResponse> accessToken;


    public List<UserLoginResponse> getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(List<UserLoginResponse> accessToken) {
        this.accessToken = accessToken;
    }

    public class access_Token{
        @Expose
        @SerializedName("access_token")
        private String access_token;
        @Expose
        @SerializedName("token_type")
        private String token_type;
        @Expose
        @SerializedName("expires_in")
        private String client_secret;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getToken_type() {
            return token_type;
        }

        public void setToken_type(String token_type) {
            this.token_type = token_type;
        }

        public String getClient_secret() {
            return client_secret;
        }

        public void setClient_secret(String client_secret) {
            this.client_secret = client_secret;
        }
    }


}
