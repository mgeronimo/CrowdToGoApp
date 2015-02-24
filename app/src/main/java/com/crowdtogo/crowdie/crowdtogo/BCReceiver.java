package com.crowdtogo.crowdie.crowdtogo;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.crowdtogo.crowdie.model.ErrorMessage;
import com.crowdtogo.crowdie.model.OrdersResponse;
import com.crowdtogo.crowdie.network.OrdersSpiceService;
import com.crowdtogo.crowdie.network.UsersInterfaceSpiceService;
import com.crowdtogo.crowdie.network.requests.OrdersRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.Calendar;
import java.util.HashMap;

import retrofit.RetrofitError;



public class BCReceiver extends BroadcastReceiver {
    static int noOfTimes = 0;

    Context ctx;
    MainActivity mainActivity = new MainActivity();
    @Override
    public void onReceive(final Context context, Intent intent) {
//        // TODO Auto-generated method stub

        noOfTimes++;
        Toast.makeText(context, "New Delivery Request " + noOfTimes + " times", Toast.LENGTH_SHORT).show();

        try {
            Intent trIntent = new Intent("android.intent.action.LAUNCHER");
            trIntent.setClass(context, com.crowdtogo.crowdie.crowdtogo.Dialog.class);
            trIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(trIntent);

    } catch(Exception exception)
    {
        System.out.println("getSpiceManager Error " + exception + ": ");
    }

}


}



