package com.crowdtogo.crowdie.crowdtogo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;



@SuppressWarnings({ "deprecation", "deprecation" })
public class CallSupportFragment extends SherlockFragment {

    SQLiteDatabase calllog;

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

        try {
            EndCallListener callListener = new EndCallListener();
            TelephonyManager mTM = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
            mTM.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);
        } catch(Exception e) {
            Log.e("callMonitor", "Exception: "+e.toString());
        }

        v.findViewById(R.id.callSupport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:09277010228"));
                startActivity(callIntent);
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
                if (active) {
                    active = false;
                    // stop listening
                    TelephonyManager mTM = (TelephonyManager) getActivity().getSystemService( Context.TELEPHONY_SERVICE );
                    mTM.listen(this, PhoneStateListener.LISTEN_NONE);
                    getCallDetails();

                }
            }
        }
    }


    private void getCallDetails() {

       CallHistoryDBHelper callHistoryDBHelper = new CallHistoryDBHelper(getActivity());

        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = getActivity().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, "number =?", new String [] {"09277010228"}, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);

        while (managedCursor.moveToNext()) {

            String callDuration = managedCursor.getString(duration);

            callHistoryDBHelper.open();

            cv.put("CALL_NUMBER", number);
            cv.put("CALL_TYPE", type);
            cv.put("CALL_DURATION", duration);
            callHistoryDBHelper.insertValues("Records", cv);
            callHistoryDBHelper.close();

            sb.append("" + callDuration);

        }
        managedCursor.close();
    }



}


