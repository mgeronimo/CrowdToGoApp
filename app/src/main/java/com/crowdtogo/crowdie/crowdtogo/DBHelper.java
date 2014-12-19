package com.crowdtogo.crowdie.crowdtogo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 12/16/2014.
 */
public class DBHelper extends SQLiteOpenHelper {



    public DBHelper(Context applicationcontext) {
        super(applicationcontext, "CrowdToGo.db", null, 1);
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
                "PRIMARY KEY(orderId))";

        database.execSQL(query);

    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS ORDERS";
        database.execSQL(query);
        onCreate(database);
    }


    /**
     * Inserts ORDERS into SQLite DB
     * @param queryValues
     */
    public void insertOrders(HashMap<String, String> queryValues) {

        try{
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("orderId",queryValues.get("orderId"));
            values.put("firstname", queryValues.get("firstname"));
            values.put("lastname", queryValues.get("lastname"));
            values.put("destination_address", queryValues.get("destination_address"));
            values.put("contact", queryValues.get("contact"));
            values.put("size",queryValues.get("size"));
            values.put("status", queryValues.get("status"));
            values.put("destination_latitude", queryValues.get("destination_latitude"));
            values.put("destination_longitude", queryValues.get("destination_longitude"));
            values.put("merchantId", queryValues.get("merchantId"));
            values.put("store_name", queryValues.get("store_name"));
            values.put("store_contact", queryValues.get("store_contact"));
            values.put("pickup_address", queryValues.get("pickup_address"));
            values.put("pickup_address_line_2",queryValues.get("pickup_address_line_2"));
            values.put("pickup_latitude", queryValues.get("pickup_latitude"));
            values.put("pickup_longitude", queryValues.get("pickup_longitude"));

            database.insert("ORDERS", null, values);
            System.out.println("Orders Request Saved");
            database.close();
        }catch(Exception exception){
           System.out.println(exception);
        }
//
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
}
