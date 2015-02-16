package com.crowdtogo.crowdie.crowdtogo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.IBinder;
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

public class NotificationService extends Service {
	int numMessages = 0;
    private View mView;

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

        super.onCreate();

    }

	@Override
	public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
		Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();



//		Intent resultIntent = new Intent(this, ConfirmDeliveryRequestActivity.class);
//		PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
//				resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

//		NotificationCompat.Builder mNotifyBuilder;
//		NotificationManager mNotificationManager;
//		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//		// Sets an ID for the notification, so it can be updated
//		int notifyID = 9001;
//		mNotifyBuilder = new NotificationCompat.Builder(this)
//				.setContentTitle("Alert")
//				.setContentText("You've received New Delivery Request.")
//				.setSmallIcon(R.drawable.ic_notif);
//		// Set pending intent
//		//mNotifyBuilder.setContentIntent(resultPendingIntent);
//		// Set Vibrate, Sound and Light
//		int defaults = 0;
//		defaults = defaults | Notification.DEFAULT_LIGHTS;
//		//defaults = defaults | Notification.DEFAULT_VIBRATE;
//		defaults = defaults | Notification.DEFAULT_SOUND;
//		mNotifyBuilder.setDefaults(defaults);
//		// Set the content for Notification
//		//mNotifyBuilder.setContentText(intent.getStringExtra("intntdata"));
//		// Set autocancel
//		mNotifyBuilder.setAutoCancel(true);
//		// Post a notification
//		mNotificationManager.notify(notifyID, mNotifyBuilder.build());
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        super.onDestroy();
        ((WindowManager)getSystemService(WINDOW_SERVICE)).removeView(mView);
        mView = null;

	}

}




