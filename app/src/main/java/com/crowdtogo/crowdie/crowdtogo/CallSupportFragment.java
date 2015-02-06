package com.crowdtogo.crowdie.crowdtogo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

import java.text.DateFormat;
import java.util.ArrayList;


@SuppressWarnings({ "deprecation", "deprecation" })
public class CallSupportFragment extends SherlockFragment {

    SQLiteDatabase calllog;
    Context context;

    Handler hand = new Handler();
    Cursor cursor;
    ContentValues cv;

    TextView tvnatmins, tvintmins, tvnatsms;

    @SuppressWarnings("deprecation")

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_call_support, container, false);
        super.onCreate(savedInstanceState);
        DBHelper callLogs = new DBHelper(getActivity());

        try {
            EndCallListener callListener = new EndCallListener();
            TelephonyManager mTM = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
            mTM.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);
        } catch(Exception e) {
            Log.e("callMonitor", "Exception: "+e.toString());
        }

        this.getActivity()
                .getContentResolver()
                .registerContentObserver(
                        android.provider.CallLog.Calls.CONTENT_URI, true,
                        new CallLogObserver(new Handler()));

        try {

            addLog();
        }
        finally {

            callLogs.close();
        }

        v.findViewById(R.id.callSupport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:09277010228"));

                startActivity(callIntent);
                //getCallDetails();
            }
        });

        return v;
    }

    private class EndCallListener extends PhoneStateListener {
        private boolean active = false;
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if(TelephonyManager.CALL_STATE_RINGING == state) {
                Log.i("EndCallListener", "RINGING, number: " + incomingNumber);
            }
            if(TelephonyManager.CALL_STATE_OFFHOOK == state) {
                //wait for phone to go offhook (probably set a boolean flag) so you know your app initiated the call.
                active = true;
                Log.i("EndCallListener", "OFFHOOK");
            }
            if(TelephonyManager.CALL_STATE_IDLE == state) {
                //when this state occurs, and your flag is set, restart your app
                Log.i("EndCallListener", "IDLE");
                //getCallDetails();

                if (active) {
                    active = false;
                    try{
                    // stop listening
                    TelephonyManager mTM = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
                    mTM.listen(this, PhoneStateListener.LISTEN_NONE);
                    } catch(Exception e) {
                    Log.e("callMonitor: ", e.toString());
                    }
                }
            }
        }
    }



    private void addLog() {


        // TODO Auto-generated method stub
        SQLiteDatabase db;


        Cursor cursor = getActivity().getContentResolver().query(
                android.provider.CallLog.Calls.CONTENT_URI, null, CallLog.Calls.NUMBER+"='09277010228'", null,
                android.provider.CallLog.Calls.DATE + " DESC ");

        DBHelper callLogs = new DBHelper(getActivity());
        db = callLogs.getWritableDatabase();


        getActivity().startManagingCursor(cursor);
        int Id = cursor.getColumnIndex(CallLog.Calls._ID);
        int cnumber = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int cduration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        //int dateId = cursor.getColumnIndex(android.provider.CallLog.Calls.DATE);
        int ctype = cursor.getColumnIndex(CallLog.Calls.TYPE);

//        Date dt = new Date();
//        int hours = dt.getHours();
//        int minutes = dt.getMinutes();
//        int seconds = dt.getSeconds();
//        String currTime = hours + ":" + minutes + ":" + seconds;






       // ArrayList<String> callList = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {

//                if (callLogs.checkCallLogDuplicate(String.valueOf(Id))) {
//                    System.out.println("Duplicate:");
//                }else{
                    String id = cursor.getString(Id);
                    String number = cursor.getString(cnumber);
                    String duration = cursor.getString(cduration);
                    //String callDate = DateFormat.getDateInstance().format(dateId);
                    String type = cursor.getString(ctype);

                    ContentValues values = new ContentValues();
                    values.put("id", id);
                    values.put("cnumber", number);
                    values.put("cduration", duration);
                    values.put("ctype", type);

                    db.insert("CALL_LOGS", null, values);
                    //Toast.makeText(getActivity(), "Call log Inserted!", Toast.LENGTH_SHORT).show();
               // }
            } while (cursor.moveToNext());
        }





    }

    public class CallLogObserver extends ContentObserver
    {



        public CallLogObserver(Handler handler) {
            super(handler);
            // TODO Auto-generated constructor stub
        }


        @Override
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override
        public void onChange(boolean selfChange) {
            Log.d("LOG_TAG", "MyContentObserver.onChange( " + selfChange
                    + ")");
            super.onChange(selfChange);
            addLog();

        }





    }



}


