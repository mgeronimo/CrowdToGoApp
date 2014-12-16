package com.crowdtogo.crowdie.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Edu on 12/11/2014.
 */
public class SuccessResponse
{
    @Expose
    @SerializedName("message")
    private String message;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
