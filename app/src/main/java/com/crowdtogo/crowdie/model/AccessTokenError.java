package com.crowdtogo.crowdie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alj on 12/4/2014.
 */
public class AccessTokenError
{
    @Expose
    @SerializedName("error")
    private String error;
    @Expose
    @SerializedName("error_description")
    private String error_description;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }
}
