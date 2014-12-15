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
        Log.w("myMessage","You passed getMessage");
        return message;
    }

    public void setMessage(String message)
    {
        Log.w("myMessage","You passed setMessage");
        this.message = message;
    }
}
