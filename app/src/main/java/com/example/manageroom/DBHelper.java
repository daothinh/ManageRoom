package com.example.manageroom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "HomeTown.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Room";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_AREA = "area";
    private static final String COLUMN_RENT_PRICE = "rentPrice";
    private static final String COLUMN_ELECTRICITY_BILL = "electricityBill";
    private static final String COLUMN_WATER_BILL = "waterBill";
    private static final String COLUMN_AREA_CODE = "areaCode";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_AREA + " INTEGER, " +
                COLUMN_RENT_PRICE + " INTEGER, " +
                COLUMN_ELECTRICITY_BILL + " INTEGER, " +
                COLUMN_WATER_BILL + " INTEGER," +
                COLUMN_AREA_CODE + " INTEGER );" ;

        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
