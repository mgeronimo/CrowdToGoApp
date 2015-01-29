package com.crowdtogo.crowdie.crowdtogo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

import org.w3c.dom.Text;

public class UserProfileFragment extends SherlockFragment {

	RelativeLayout rlCover;
	ImageView ivUserImage;
    ImageView ivUserVehicle;
    ImageView ivUserFastestTime;
	RoundedImage roundedImage;
    TextView tvUserName;
    TextView tvRating;
    TextView tvDeliveryCount;
    TextView tvFastestTime;
    TextView tvVehicle;
    TextView txtuserCurrentLocation;
    TextView tvRatingCount;
    TextView tvSummaryRating;
    RatingBar rbRating;
    TextView tvRating5;
    TextView tvRating4;
    TextView tvRating3;
    TextView tvRating2;
    TextView tvRating1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_user_temp);
		roundedImage = new RoundedImage(bm);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.user_profile, container, false);

		init(v);

		return v;
	}

	private void init(View v) {

		//rlCover = (RelativeLayout) v.findViewById(R.id.ivCover);
		ivUserImage   = (ImageView) v.findViewById(R.id.ivImage);
        ivUserImage.setImageDrawable(roundedImage);

        ivUserVehicle = (ImageView) v.findViewById(R.id.iconVehicle);
        //setUserVehicle(getUserProfileData("vehicle",getActivity()));
// May Start
        setUserVehicle(getUserProfileData("vehicle",getActivity()));
// May End

        tvUserName    = (TextView) v.findViewById(R.id.userProfileName);
        tvUserName.setText(getUserProfileData("name",getActivity()));

        ivUserFastestTime = (ImageView) v.findViewById(R.id.iconFastestTime);

        tvRating = (TextView) v.findViewById(R.id.tvRating);
        tvRating.setText(getUserProfileData("overall_rating",getActivity()) + " rating " );

        tvDeliveryCount = (TextView) v.findViewById(R.id.tvDeliveryCount);
        tvDeliveryCount.setText(getUserProfileData("completed_orders",getActivity())+" deliveries");

        tvFastestTime = (TextView) v.findViewById(R.id.tvFastestTime);
        String fastest = getUserProfileData("fastest_time",getActivity());

        if (fastest==null || fastest.equalsIgnoreCase("null")){
            fastest = "0 Delivery";
        }
        tvFastestTime.setText(fastest);

        tvVehicle = (TextView) v.findViewById(R.id.tvVehicle);
        tvVehicle.setText(getUserProfileData("vehicle",getActivity()));

        txtuserCurrentLocation = (TextView) v.findViewById(R.id.txtuserCurrentLocation);
        String address = getUserProfileData("address",getActivity());
        if (address==null){
            address = "Current Location Unavailable";
        }
        txtuserCurrentLocation.setText(address);

        tvRatingCount = (TextView) v.findViewById(R.id.tvRatingCount);
        float completed_orders = 0;
        completed_orders = Float.parseFloat(getUserProfileData("completed_orders",getActivity()));
        tvRatingCount.setText(""+completed_orders);

        tvSummaryRating = (TextView) v.findViewById(R.id.tvSummaryRating);
        float summary_rating = 0;
        summary_rating = Float.parseFloat(getUserProfileData("overall_rating",getActivity()));
        tvSummaryRating.setText(""+summary_rating);

        rbRating = (RatingBar) v.findViewById(R.id.rbRating);
        rbRating.setRating(summary_rating);

        tvRating1 = (TextView) v.findViewById(R.id.tvRating1);
        tvRating2 = (TextView) v.findViewById(R.id.tvRating2);
        tvRating3 = (TextView) v.findViewById(R.id.tvRating3);
        tvRating4 = (TextView) v.findViewById(R.id.tvRating4);
        tvRating5 = (TextView) v.findViewById(R.id.tvRating5);

        tvRating1.setText(getUserProfileData("rating_1",getActivity()));
        tvRating2.setText(getUserProfileData("rating_2",getActivity()));
        tvRating3.setText(getUserProfileData("rating_3",getActivity()));
        tvRating4.setText(getUserProfileData("rating_4",getActivity()));
        tvRating5.setText(getUserProfileData("rating_5",getActivity()));


    }

    //get stored Name
    public static String getUserProfileData(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public void setUserVehicle(String vehicle){
<<<<<<< Updated upstream
        if( vehicle.equalsIgnoreCase("Bicycle") ){
=======
        if( vehicle.equalsIgnoreCase("bike") ){
>>>>>>> Stashed changes
            ivUserVehicle.setImageDrawable(getResources().getDrawable(R.drawable.bike));
        }else if( vehicle.equalsIgnoreCase("motorcycle") ){
            ivUserVehicle.setImageDrawable(getResources().getDrawable(R.drawable.motor));
        }
        else if( vehicle.equalsIgnoreCase("car") ){
            ivUserVehicle.setImageDrawable(getResources().getDrawable(R.drawable.car));
        }
<<<<<<< Updated upstream
        else if( vehicle.equalsIgnoreCase("van") ){
=======
        else if( vehicle.equalsIgnoreCase("mini van") ){
>>>>>>> Stashed changes
            ivUserVehicle.setImageDrawable(getResources().getDrawable(R.drawable.van));
        }else{
            ivUserVehicle.setImageDrawable(getResources().getDrawable(R.drawable.van));
        }
    }



}
