package com.example.rpag;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String ITEM_TABLE = "ITEM_TABLE";
    public static final String ITEM_NAME = "ITEM_NAME";
    public static final String ITEM_PRICE = "ITEM_PRICE";
    public static final String ITEM_DATE = "ITEM_DATE";
    public static final String ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "item.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + ITEM_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ITEM_NAME + " TEXT, " + ITEM_DATE + " DOUBLE, "  + ITEM_PRICE + " DOUBLE)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertData(Item item){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ITEM_NAME, item.getItemName());
        contentValues.put(ITEM_PRICE, item.getItemPrice());
        contentValues.put(ITEM_DATE, item.getItemDate());

        long insert = sqLiteDatabase.insert(ITEM_TABLE, null, contentValues);
        if(insert == -1){
            return false;
        } else {
            return true;
        }
    }
}
