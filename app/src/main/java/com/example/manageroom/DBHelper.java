package com.example.manageroom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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
                COLUMN_AREA_CODE + " INTEGER );";

        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addRoom(int area, int rentPrice, int electricityBill, int waterBill, int areaCode) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_AREA, area);
        contentValues.put(COLUMN_RENT_PRICE, rentPrice);
        contentValues.put(COLUMN_ELECTRICITY_BILL, electricityBill);
        contentValues.put(COLUMN_WATER_BILL, waterBill);
        contentValues.put(COLUMN_AREA_CODE, areaCode);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            Toast.makeText(context, "Failded", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = null;
        if(sqLiteDatabase!=null){
            cursor = sqLiteDatabase.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String roomId, String area, String rentPrice, String area_code){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_AREA, Integer.valueOf(area));
        contentValues.put(COLUMN_RENT_PRICE, Integer.valueOf(rentPrice));
        contentValues.put(COLUMN_AREA_CODE, Integer.valueOf(area_code));

        long result = db.update(TABLE_NAME, contentValues, "id=?", new String[]{String.valueOf(roomId)});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
