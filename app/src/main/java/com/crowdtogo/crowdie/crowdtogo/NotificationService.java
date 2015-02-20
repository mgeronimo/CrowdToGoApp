package com.crowdtogo.crowdie.crowdtogo;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.crowdtogo.crowdie.model.ErrorMessage;
import com.crowdtogo.crowdie.model.OrdersResponse;
import com.crowdtogo.crowdie.network.requests.OrdersRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import retrofit.RetrofitError;

public class NotificationService extends Service {
	int numMessages = 0;
    private View mView;
    Context ctx;
    MainActivity mainActivity = new MainActivity();
    OrdersSpiceActivity ordersSpiceActivity = new OrdersSpiceActivity();

    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;

	public NotificationService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void onCreate() {
		Toast.makeText(this, "Service was Created", Toast.LENGTH_SHORT).show();



    }

	@Override
	public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
		Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        super.onCreate();

        Intent trIntent = new Intent("android.intent.action.MAIN");
        trIntent.setClass(getApplicationContext(), com.crowdtogo.crowdie.crowdtogo.Dialog.class);
        trIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(trIntent);

	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        super.onDestroy();
        ((WindowManager)getSystemService(WINDOW_SERVICE)).removeView(mView);
        mView = null;

	}

}




