package com.example.rpag;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Category.db";
    public static final String TABLE_NAME = "Category_Table";
    public static final String ID = "ID";
    public static final String ITEM_NAME = "ITEM_NAME";
    public static final String ITEM_PRICE = "ITEM_PRICE";

    private static final String CREATE_TABLE = "CREATE TABLE "+ DATABASE_NAME+" ("+
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            ITEM_NAME+ " TEXT, "+ ITEM_PRICE+ " TEXT)";

    public DatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
        //        ITEM_NAME + " TEXT, " + ITEM_PRICE + " TEXT)";
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT ,ITEM_NAME TEXT, ITEM_PRICE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String itemName, String itemPrice){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_NAME, itemName);
        contentValues.put(ITEM_PRICE, itemPrice);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

}