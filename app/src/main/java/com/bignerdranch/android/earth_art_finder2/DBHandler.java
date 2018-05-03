package com.bignerdranch.android.earth_art_finder2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 3537 on 06-11-2015.
 */
public class DBHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "earthArt";

    // Items table name
    private static final String TABLE_ITEM_DETAIL = "dataItems";

    // Items Table Columns names
    private static final String KEY_ID = "resId";
    private static final String KEY_NAME = "name";

    public DBHandler(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_ITEM_DETAIL_TABLE = "CREATE TABLE " + TABLE_ITEM_DETAIL + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT)";

        db.execSQL(CREATE_ITEM_DETAIL_TABLE);

        String INSERT_ITEMS = "INSERT INTO " + TABLE_ITEM_DETAIL + "(" + KEY_ID + "," + KEY_NAME + ") " +
                "(" + R.drawable.spiraljetty +  ",Spiral Jetty), " + "(" +
        R.drawable.amarilloramp + "Amarillo Ramp), " + "(" +
        R.drawable.lightingfield + "Lighting Field), " + "(" +
        R.drawable.suntunnels + "Sun Tunnels), " + "(" +
        R.drawable.doublenegative + "Double Negative), " + "(" +
        R.drawable.brokencircle + "Broken Circle) " + "(" +
        R.drawable.city + "City)";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM_DETAIL);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Student Information
    void addNewItem(DataItem item) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, item.resIdThumbnail);
        values.put(KEY_NAME, item.ArtName);


        // Inserting Row
        db.insert(TABLE_ITEM_DETAIL, null, values);
        db.close(); // Closing database connection
    }


//    public boolean updateItemInfo(int updId, int updEnrolNo, String updName, String updPhoneNo) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues args = new ContentValues();
//
//        args.put(KEY_NAME, updName);
//
//        return db.update(TABLE_ITEM_DETAIL, args, KEY_ID + "=" + updId, null) > 0;
//    }


//    public boolean deleteItem(int delID){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        return db.delete(TABLE_ITEM_DETAIL, KEY_ID + "=" + delID, null) > 0;
//
//    }



    // Getting All Items
    public ArrayList<DataItem> getAllItemsList() {


        ArrayList<DataItem> itemList = new ArrayList<DataItem>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ITEM_DETAIL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                DataItem item = new DataItem();
                item.setResIdThumbnail(Integer.parseInt(cursor.getString(0)));
                item.setArtName(cursor.getString(1));

                // Adding contact to list
                itemList.add(item);

            } while (cursor.moveToNext());
        }

        // return item list
        return itemList;
    }


}
