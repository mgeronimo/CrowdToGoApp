package com.crowdtogo.crowdie.crowdtogo;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class DeliveryRequestListViewAdapter extends BaseAdapter{
    // Declare Variables
    Context context;
    String[] name;
    String[] date;
    String[] pickup;
    String[] delivery;
    int[] thumbnail;
    LayoutInflater inflater;

    public DeliveryRequestListViewAdapter(Context context, String[] name, String[] date,
                           String[] pickup, String[] delivery, int[] thumbnail) {
        this.context = context;
        this.name = name;
        this.date = date;
        this.pickup = pickup;
        this.delivery = delivery;
        this.thumbnail = thumbnail;
    }

    public int getCount() {
        return name.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Declare Variables
        TextView txtname;
        TextView txtdate;
        TextView txtpickup;
        TextView txtdelivery;
        ImageView imgprofile;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listview_item_deliveryrequest, parent, false);

        // Locate the TextViews in listview_item.xml
        txtname = (TextView) itemView.findViewById(R.id.name);
        txtdate = (TextView) itemView.findViewById(R.id.date);
        txtpickup = (TextView) itemView.findViewById(R.id.pickupLocation);
        txtdelivery = (TextView) itemView.findViewById(R.id.deliveryLocation);
        // Locate the ImageView in listview_item.xml
        imgprofile = (ImageView) itemView.findViewById(R.id.profilePic);

        // Capture position and set to the TextViews
        txtname.setText(name[position]);
        txtdate.setText(date[position]);
        txtpickup.setText(pickup[position]);
        txtdelivery.setText(delivery[position]);

        // Capture position and set to the ImageView
        imgprofile.setImageResource(thumbnail[position]);

        return itemView;
    }
}

