package com.crowdtogo.crowdie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alj on 12/1/2014.
 */
public class UserLoginResponse {


    @Expose
    @SerializedName("access_token")
    private String access_token;
    @Expose
    @SerializedName("token_type")
    private String token_type;
    @Expose
    @SerializedName("expires_in")
    private String expires_in;
    @Expose
    @SerializedName("crowdie_id")
    private String crowdie_id;
    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("userId")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrowdie_id() {
        return crowdie_id;
    }

    public void setCrowdie_id(String crowdie_id) {
        this.crowdie_id = crowdie_id;
    }

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

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}

