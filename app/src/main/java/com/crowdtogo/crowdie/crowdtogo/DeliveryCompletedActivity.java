package com.crowdtogo.crowdie.crowdtogo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crowdtogo.crowdie.model.ErrorMessage;
import com.crowdtogo.crowdie.model.SuccessResponse;
import com.crowdtogo.crowdie.network.requests.CheckOrderCodeRequest;
import com.crowdtogo.crowdie.network.requests.DeliveryStatusRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.HashMap;

import retrofit.RetrofitError;

public class DeliveryCompletedActivity extends OrdersSpiceActivity {
    Context context;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.delivery_completed);

        ok = (Button)findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                    Intent refresh = new Intent(DeliveryCompletedActivity.this,MainActivity.class);
                    DeliveryCompletedActivity.this.startActivity(refresh);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent refresh = new Intent(DeliveryCompletedActivity.this,MainActivity.class);
                DeliveryCompletedActivity.this.startActivity(refresh);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
