package com.crowdtogo.crowdie.crowdtogo;

import android.content.Context;
import com.orm.SugarRecord;

/**
 * Created by User on 12/12/2014.
 */
public class Orders extends SugarRecord<Orders> {

     String orderId;
     String crowdieId;
     String firstname;
     String lastname;
     String street;
     String city;
     String aptNumber;
     String state;
     String zipcode;
     String contact;
     String size;
     String status;
     double destinationlatitude;
     double destinationlongitude;
     String merchantId;
     String store_name;
     String store_contact;
     String pickup_street_address;
     String pickup_address_line_2;
     String pickup_city;
     String pickup_state;
     String pickup_zip;
     double pickuplatitude;
     double pickuplongitude;


    public Orders(Context context){
        //super(context);
        super();
    }

    public Orders(String orderId, String crowdieId, String firstname, String lastname,
                  String street, String city, String aptNumber, String state, String zipcode, String contact, String size,
                  String status, double destinationlatitude, double destinationlongitude, String merchantId,
                  String store_name, String store_contact, String pickup_street_address, String pickup_address_line_2,
                  String pickup_city, String pickup_state, String pickup_zip, double pickuplatitude,
                  double pickuplongitude){

        //super(context);
        this.orderId = orderId;
        this.crowdieId = crowdieId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.street = street;
        this.city = city;
        this.aptNumber = aptNumber;
        this.state = state;
        this.zipcode = zipcode;
        this.contact = contact;
        this.size = size;
        this.status = status;
        this.destinationlatitude = destinationlatitude;
        this.destinationlongitude = destinationlongitude;
        this.merchantId = merchantId;
        this.store_name = store_name;
        this.store_contact = store_contact;
        this.pickup_street_address = pickup_street_address;
        this.pickup_address_line_2 = pickup_address_line_2;
        this.pickup_city = pickup_city;
        this.pickup_state = pickup_state;
        this.pickup_zip = pickup_zip;
        this.pickuplatitude = pickuplatitude;
        this.pickuplongitude = pickuplongitude;

    }

}
