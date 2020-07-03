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
    public static final String CATEGORY_TABLE = "CATEGORY_TABLE";

    //Category Table fields
    public static final String CATEGORY_NAME = "CATEGORY_NAME";
    public static final String CATEGORY_BUDGET = "CATEGORY_BUDGET";
    public static final String CATEGORY_ID = "CATEGORY_ID";


    //Item Table fields
    public static final String ITEM_NAME = "ITEM_NAME";
    public static final String ITEM_PRICE = "ITEM_PRICE";
    public static final String ITEM_DATE = "ITEM_DATE";
    public static final String ITEM_ID = "ITEM_ID";

    public DataBaseHelper(@Nullable Context context) {

        super(context, "test1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createItemTable = "CREATE TABLE " + ITEM_TABLE + " (" + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ITEM_NAME + " TEXT, " + ITEM_DATE + " TEXT, "  + ITEM_PRICE + " DOUBLE)";
        sqLiteDatabase.execSQL(createItemTable);

        String createCategoryTable = "CREATE TABLE " + CATEGORY_TABLE + " (" + CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CATEGORY_NAME + " TEXT, " + CATEGORY_BUDGET + " DOUBLE)";
        sqLiteDatabase.execSQL(createCategoryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF IT EXISTS " + ITEM_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF IT EXISTS " + CATEGORY_TABLE);
        onCreate(sqLiteDatabase);
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

    public boolean insertData(Category category){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CATEGORY_NAME, category.getCategoryName());
        contentValues.put(CATEGORY_BUDGET, category.getCategoryBudget());

        long insert = sqLiteDatabase.insert(CATEGORY_TABLE, null, contentValues);
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
                String itemDate = cursor.getString(2);
                double itemPrice = cursor.getDouble(3);

                Item temp = new Item(itemName, itemPrice, itemDate, itemID);
                itemList.add(temp);
            }while(cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();
        return itemList;
    }

    public List<Category> viewCategoryData(){
        List<Category> categoryList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CATEGORY_TABLE;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                double budget = cursor.getDouble(2);

                Category temp = new Category(name, budget, id);
                categoryList.add(temp);
            }while(cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();
        return categoryList;
    }

    public boolean deleteData(Item item){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ITEM_TABLE + " WHERE " + ITEM_ID + " = " + item.getItemId();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
}
