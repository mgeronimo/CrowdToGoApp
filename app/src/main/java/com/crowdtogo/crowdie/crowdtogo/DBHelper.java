package com.crowdtogo.crowdie.crowdtogo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alj on 12/16/2014.
 */
public class DBHelper extends SQLiteOpenHelper {



    public DBHelper(Context applicationcontext) {
        super(applicationcontext, "CrowdToGo.db", null, 1);
    }

    public void open() {
        SQLiteDatabase database = this.getWritableDatabase();
    }

    //Creates Table
    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE ORDERS (" +
                "orderId TEXT,"+
                "firstname TEXT,"+
                "lastname  TEXT,"+
                "destination_address  TEXT,"+
                "contact  TEXT,"+
                "size  TEXT,"+
                "status  TEXT,"+
                "destination_latitude  TEXT,"+
                "destination_longitude  TEXT,"+
                "merchantId  TEXT,"+
                "store_name  TEXT,"+
                "store_contact  TEXT,"+
                "pickup_address  TEXT,"+
                "pickup_address_line_2  TEXT,"+
                "pickup_latitude  TEXT,"+
                "pickup_longitude TEXT,"+
                "pickup_date  TEXT,"+
                "pickup_time TEXT,"+
                "groupId  TEXT,"+
                "deliveryStatus TEXT,"+
                "duration TEXT,"+
                "PRIMARY KEY(orderId))";
        String calllogs;
        calllogs = "CREATE TABLE CALL_LOGS (" +
                "Id TEXT,"+
                "cnumber TEXT,"+
                "cduration  TEXT,"+
                "ctype TEXT,"+
                "PRIMARY KEY(Id))";

        database.execSQL(query);
        database.execSQL(calllogs);

    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query,calllogs;
        query = "DROP TABLE IF EXISTS ORDERS";
        calllogs = "DROP TABLE IF EXISTS CAlL_LOGS";
        database.execSQL(query);
        database.execSQL(calllogs);
        onCreate(database);
    }


    public boolean checkDuplicate(String orderId) {
  /* The 3rd parameter is treated as SQL WHERE clause, ? are replaced by strings
   * from the 4th parameter */

        SQLiteDatabase database = this.getReadableDatabase();
        //db = ordersDB.getReadableDatabase();
        String columns[] = {
                "orderId",
//                        "firstname",
//                        "lastname",
                "destination_address",
//                        "contact",
//                        "size",
//                        "status",
//                        "destination_latitude",
//                        "destination_longitude",
//                        "merchantId",
                "store_name",
//                        "store_contact",
                "pickup_address",
//                        "pickup_address_line_2",
//                        "pickup_latitude",
//                        "pickup_longitude",
//                        "pickup_date",
//                        "pickup_time"
//                        "groupId",
                "deliveryStatus"//,
                //"duration"

        };
        Cursor cur = database.query("ORDERS", columns, "orderId=" +orderId, null, null, null, null, null);
        System.out.println("checkDuplicate "+cur.getCount());

        if (cur != null && cur.getCount()>0) {

            // duplicate found
            database.close();
            return true;
        }
        database.close();
        return false;
    }


    public boolean checkCallLogDuplicate(String Id) {
  /* The 3rd parameter is treated as SQL WHERE clause, ? are replaced by strings
   * from the 4th parameter */

        SQLiteDatabase database = this.getReadableDatabase();
        //db = ordersDB.getReadableDatabase();

       String columns[] = {"Id",
                "cnumber",
                "cduration",
                "ctype"
        };

        Cursor cur = database.query("CAlL_LOGS", columns, "Id=" +Id, null, null, null, null, null);
        if (cur != null && cur.getCount()>0) {

            // duplicate found
            database.close();
            return true;
        }
        database.close();
        return false;
    }


    /**
         * Inserts ORDERS into SQLite DB
         * @param queryValues
         */
    public void insertOrders(HashMap<String, String> queryValues) {

        try {
            //check for duplicate else insert the data to sqlite
            if(checkDuplicate(queryValues.get("orderId"))) {
                System.out.println("Duplicate orderId: " + queryValues.get("orderId"));
            }else{

            this.open();
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("orderId", queryValues.get("orderId"));
            values.put("firstname", queryValues.get("firstname"));
            values.put("lastname", queryValues.get("lastname"));
            values.put("destination_address", queryValues.get("destination_address"));
            values.put("contact", queryValues.get("contact"));
            values.put("size", queryValues.get("size"));
            values.put("status", queryValues.get("status"));
            values.put("destination_latitude", queryValues.get("destination_latitude"));
            values.put("destination_longitude", queryValues.get("destination_longitude"));
            values.put("merchantId", queryValues.get("merchantId"));
            values.put("store_name", queryValues.get("store_name"));
            values.put("store_contact", queryValues.get("store_contact"));
            values.put("pickup_address", queryValues.get("pickup_address"));
            values.put("pickup_address_line_2", queryValues.get("pickup_address_line_2"));
            values.put("pickup_latitude", queryValues.get("pickup_latitude"));
            values.put("pickup_longitude", queryValues.get("pickup_longitude"));
            values.put("pickup_date", queryValues.get("pickup_date"));
            values.put("pickup_time", queryValues.get("pickup_time"));
            values.put("groupId", queryValues.get("groupId"));
            values.put("deliveryStatus", queryValues.get("deliveryStatus"));
            values.put("duration", queryValues.get("duration"));

            database.insert("ORDERS", null, values);
            System.out.println("Orders Request Saved");
            database.close();
            }
        } catch (Exception exception) {
        System.out.println("orderId "+queryValues.get("orderId"));
        System.out.println("SQLite "+exception);
    }

    }


    public void UpdateDeliveryStatus(String orderId, String deliveryStatus){
        try {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("deliveryStatus", deliveryStatus);
        database.update("ORDERS", values, "orderId="+orderId, null);
        System.out.println("Orders status updated");
        Log.v("Delivery Done", "YEYE!");

    } catch (Exception exception) {
        System.out.println(exception);
        Log.e("Failed updated", deliveryStatus);
    }
    }

    /**
     * Get list of Orders from SQLite DB as Array List
     * @return
     */
    public ArrayList<HashMap<String, String>> getAllOrders() {
        ArrayList<HashMap<String, String>> ordersList;
        ordersList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  *  FROM ORDERS";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("orderId",cursor.getString(0));
                map.put("firstname",cursor.getString(1));
                map.put("lastname", cursor.getString(2));
                map.put("destination_address", cursor.getString(3));
                map.put("contact", cursor.getString(4));
                map.put("size",cursor.getString(5));
                map.put("status", cursor.getString(6));
                map.put("destination_latitude", cursor.getString(7));
                map.put("destination_longitude", cursor.getString(8));
                map.put("merchantId", cursor.getString(9));
                map.put("store_name",cursor.getString(10));
                map.put("store_contact", cursor.getString(11));
                map.put("pickup_address",cursor.getString(12));
                map.put("pickup_address_line_2",cursor.getString(13));
                map.put("pickup_latitude", cursor.getString(14));
                map.put("pickup_longitude", cursor.getString(15));
                map.put("pickup_date", cursor.getString(16));
                map.put("pickup_time", cursor.getString(17));
                map.put("groupId", cursor.getString(18));
                map.put("deliveryStatus", cursor.getString(19));
                map.put("duration", cursor.getString(20));

                ordersList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return ordersList;
    }

    /**
     * Get list of Ongoing Orders from SQLite DB as Array List
     * @return
     */
    public ArrayList<HashMap<String, String>> getAllOngoingOrders() {
        ArrayList<HashMap<String, String>> ordersList;
        ordersList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  *  FROM ORDERS WHERE deliveryStatus='0'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("orderId",cursor.getString(0));
                map.put("firstname",cursor.getString(1));
                map.put("lastname", cursor.getString(2));
                map.put("destination_address", cursor.getString(3));
                map.put("contact", cursor.getString(4));
                map.put("size",cursor.getString(5));
                map.put("status", cursor.getString(6));
                map.put("destination_latitude", cursor.getString(7));
                map.put("destination_longitude", cursor.getString(8));
                map.put("merchantId", cursor.getString(9));
                map.put("store_name",cursor.getString(10));
                map.put("store_contact", cursor.getString(11));
                map.put("pickup_address",cursor.getString(12));
                map.put("pickup_address_line_2",cursor.getString(13));
                map.put("pickup_latitude", cursor.getString(14));
                map.put("pickup_longitude", cursor.getString(15));
                map.put("pickup_date", cursor.getString(16));
                map.put("pickup_time", cursor.getString(17));
                map.put("groupId", cursor.getString(18));
                map.put("deliveryStatus", cursor.getString(19));

                map.put("duration", cursor.getString(20));

                ordersList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return ordersList;
    }


    public int countOrder(){
        int count = 0;
        String selectQuery = "SELECT  count(*) FROM ORDERS";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
    }

    public void DeleteOrders()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM ORDERS");
        db.close();
        System.out.println("Orders Deleted!");
    }
}
