package com.example.rpag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
                ITEM_NAME + " TEXT, " + ITEM_DATE + " INT, "  + ITEM_PRICE + " DOUBLE)";
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

    public List<Item> viewData(){
        List<Item> itemList = new ArrayList<>();

        String queryString = "SELECT * FROM " + ITEM_TABLE;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                int itemID = cursor.getInt(0);
                String itemName = cursor.getString(1);
                int itemDate = cursor.getInt(2);
                double itemPrice = cursor.getDouble(3);

                Item temp = new Item(itemName, itemPrice, itemDate, itemID);
                itemList.add(temp);
            }while(cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();
        return itemList;
    }

    public boolean deleteData(Item item){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ITEM_TABLE + " WHERE " + ID + " = " + item.getId();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }


    }
}
