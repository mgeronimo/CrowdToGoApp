package com.crowdtogo.crowdie.crowdtogo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.RequestParams;

public class NotificationActivity extends Activity {
	// DB Class to perform DB related operations
	//SyncDBController controller = new SyncDBController(this);
	// Progress Dialog Object
	ProgressDialog prgDialog;
	HashMap<String, String> queryValues;
    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private Button startB;
    public TextView tvcountDown;
    private final long startTime = 30 * 1000;
    private final long interval = 1 * 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_confirm_delivery_request);
        tvcountDown = (TextView)findViewById(R.id.tvcountDown);
		// Get User records from SQLite DB
		//ArrayList<HashMap<String, String>> userList = controller.getAllUsers();
		// If users exists in SQLite DB
//		if (userList.size() != 0) {
//			// Set the User Array list in ListView
//			ListAdapter adapter = new SimpleAdapter(SyncActivity.this, userList, R.layout.syncview_user_entry, new String[] {
//			"ID", "TITLE", "AUTHOR", "OTHER_AUTHOR", "EDITOR", "PLACE_OF_PUBLICATION", "PUBLISHER", "YEAR_OF_PUBLICATION", "DESCRIPTION", "NOTES", "ISBN", "CALL_NUMBER", "ACCESSION", "LIB_LANGUAGE", "LOCATION", "COPY", "TOPICAL_SUBJECT"
//			}, new int[] { R.id.id, R.id.title, R.id.author, R.id.other_author, R.id.editor, R.id.place_of_publication, R.id.publisher, R.id.year_of_publication, R.id.description , R.id.notes, R.id.isbn, R.id.call_number, R.id.accession, R.id.lib_language, R.id.location, R.id.copy, R.id.topical_subject });
//			ListView myList = (ListView) findViewById(android.R.id.list);
//			myList.setAdapter(adapter);
//		}
		// Initialize Progress Dialog properties
//		prgDialog = new ProgressDialog(this);
//        prgDialog.setMessage("Transferring Data. Please wait...");
//        prgDialog.setCancelable(false);
		// BroadCase Receiver Intent Object
//		Intent alarmIntent = new Intent(getApplicationContext(), BCReceiver.class);
//		// Pending Intent Object
//		PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//		// Alarm Manager Object
//		AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//		// Alarm Manager calls BroadCast for every Ten seconds (10 * 1000), BroadCase further calls service to check if new records are inserted in
//		// Remote MySQL DB
//		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + 5000, 10 * 1000, pendingIntent);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        tvcountDown.setText(tvcountDown.getText() + String.valueOf(startTime / 1000));
        if (!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
        }
//            startB.setText("STOP");
//        } else {
//            countDownTimer.cancel();
//            timerHasStarted = false;
//            startB.setText("RESTART");
//        }
    }
	
	// Options Menu (ActionBar Menu)
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

	// When Options Menu is selected
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here.
//		int id = item.getItemId();
//		// When Sync action button is clicked
//		if (id == R.id.refresh) {
//			// Transfer data from remote MySQL DB to SQLite on Android and perform Sync
//			syncSQLiteMySQLDB();
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
	
	// Method to Sync MySQL to SQLite DB
//	public void syncSQLiteMySQLDB() {
//		// Create AsycHttpClient object
//		AsyncHttpClient client = new AsyncHttpClient();
//		// Http Request Params Object
//		RequestParams params = new RequestParams();
//		// Show ProgressBar
//		prgDialog.show();
//		// Make Http call to getusers.php
//		client.post("http://www.stinovalicheslibrary.net16.net/getusers.php", params, new AsyncHttpResponseHandler() {
//				@Override
//				public void onSuccess(String response) {
//					// Hide ProgressBar
//					prgDialog.hide();
//					// Update SQLite DB with response sent by getusers.php
//					updateSQLite(response);
//				}
//				// When error occured
//				@Override
//				public void onFailure(int statusCode, Throwable error, String content) {
//					// TODO Auto-generated method stub
//					// Hide ProgressBar
//					prgDialog.hide();
//					if (statusCode == 404) {
//						Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_SHORT).show();
//					} else if (statusCode == 500) {
//						Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_SHORT).show();
//					} else {
//						Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]",
//								Toast.LENGTH_SHORT).show();
//					}
//				}
//		});
//	}
	
//	public void updateSQLite(String response){
//		ArrayList<HashMap<String, String>> usersynclist;
//		usersynclist = new ArrayList<HashMap<String, String>>();
//		// Create GSON object
//		Gson gson = new GsonBuilder().create();
//		try {
//			// Extract JSON array from the response
//			JSONArray arr = new JSONArray(response);
//			System.out.println(arr.length());
//			// If no of array elements is not zero
//			if(arr.length() != 0){
//				// Loop through each array element, get JSON object which has userid and username
//				for (int i = 0; i < arr.length(); i++) {
//					// Get JSON object
//					JSONObject obj = (JSONObject) arr.get(i);
//					System.out.println(obj.get("ID"));
//					System.out.println(obj.get("TITLE"));
//					System.out.println(obj.get("AUTHOR"));
//					System.out.println(obj.get("OTHER_AUTHOR"));
//					System.out.println(obj.get("EDITOR"));
//					System.out.println(obj.get("PLACE_OF_PUBLICATION"));
//					System.out.println(obj.get("PUBLISHER"));
//					System.out.println(obj.get("YEAR_OF_PUBLICATION"));
//					System.out.println(obj.get("DESCRIPTION"));
//					System.out.println(obj.get("NOTES"));
//					System.out.println(obj.get("ISBN"));
//					System.out.println(obj.get("CALL_NUMBER"));
//					System.out.println(obj.get("ACCESSION"));
//					System.out.println(obj.get("LIB_LANGUAGE"));
//					System.out.println(obj.get("LOCATION"));
//					System.out.println(obj.get("COPY"));
//					System.out.println(obj.get("TOPICAL_SUBJECT"));
//					// DB QueryValues Object to insert into SQLite
//					queryValues = new HashMap<String, String>();
//					// Add userID extracted from Object
//					//queryValues.put("userId", obj.get("userId").toString());
//					// Add userName extracted from Object
//					//queryValues.put("userName", obj.get("userName").toString());
//
//					queryValues.put("ID", obj.get("ID").toString());
//					queryValues.put("TITLE", obj.get("TITLE").toString());
//					queryValues.put("AUTHOR", obj.get("AUTHOR").toString());
//					queryValues.put("OTHER_AUTHOR", obj.get("OTHER_AUTHOR").toString());
//					queryValues.put("EDITOR", obj.get("EDITOR").toString());
//					queryValues.put("PLACE_OF_PUBLICATION", obj.get("PLACE_OF_PUBLICATION").toString());
//					queryValues.put("PUBLISHER", obj.get("PUBLISHER").toString());
//					queryValues.put("YEAR_OF_PUBLICATION", obj.get("YEAR_OF_PUBLICATION").toString());
//					queryValues.put("DESCRIPTION", obj.get("DESCRIPTION").toString());
//					queryValues.put("NOTES", obj.get("NOTES").toString());
//					queryValues.put("ISBN", obj.get("ISBN").toString());
//					queryValues.put("CALL_NUMBER", obj.get("CALL_NUMBER").toString());
//					queryValues.put("ACCESSION", obj.get("ACCESSION").toString());
//					queryValues.put("LIB_LANGUAGE", obj.get("LIB_LANGUAGE").toString());
//					queryValues.put("LOCATION", obj.get("LOCATION").toString());
//					queryValues.put("COPY", obj.get("COPY").toString());
//					queryValues.put("TOPICAL_SUBJECT", obj.get("TOPICAL_SUBJECT").toString());
//
//					// Insert User into SQLite DB
//					controller.insertUser(queryValues);
//					HashMap<String, String> map = new HashMap<String, String>();
//					// Add status for each User in Hashmap
//					map.put("ID", obj.get("ID").toString());
//					map.put("SYNCSTS", "1");
//					usersynclist.add(map);
//				}
//				// Inform Remote MySQL DB about the completion of Sync activity by passing Sync status of Users
//				updateMySQLSyncSts(gson.toJson(usersynclist));
//				// Reload the Main Activity
//				reloadActivity();
//				ShowbookActivity();
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	// Method to inform remote MySQL DB about completion of Sync activity
//	public void updateMySQLSyncSts(String json) {
//		System.out.println(json);
//		AsyncHttpClient client = new AsyncHttpClient();
//		RequestParams params = new RequestParams();
//		params.put("SYNCSTS", json);
//		// Make Http call to updatesyncsts.php with JSON parameter which has Sync statuses of Users
//		client.post("http://www.stinovalicheslibrary.net16.net/updatesyncsts.php", params, new AsyncHttpResponseHandler() {
//			@Override
//			public void onSuccess(String response) {
//				//Toast.makeText(getApplicationContext(),	"MySQL Database has been informed about Sync activity", Toast.LENGTH_SHORT).show();
//			}
//
//			@Override
//			public void onFailure(int statusCode, Throwable error, String content) {
//					Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_LONG).show();
//			}
//		});
//	}
	
	// Reload MainActivity
//	public void reloadActivity() {
//		Intent objIntent = new Intent(getApplicationContext(), SyncActivity.class);
//		startActivity(objIntent);
//	}
//	public void ShowbookActivity() {
//		Intent objIntent = new Intent(getApplicationContext(), SplashScreenActivity.class);
//		startActivity(objIntent);
//	}

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startTime, long interval) {

            super(startTime, interval);

        }

        @Override
        public void onFinish() {

            // text.setText("Time's up!");

        }


        @Override
        public void onTick(long millisUntilFinished) {

            //text.setText("" + millisUntilFinished / 1000);

        }

    }

}
