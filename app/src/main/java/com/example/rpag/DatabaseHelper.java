package com.example.rpag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    // the data base name will be the name of that month
    public static final String DATABASE_NAME = "June.db";

    // the first table in the database is general overview
    public static final String OVERVIEW = "overview_table";

    // general overview contains budget, spending, and all the categories
    public static final String Budget_COL = "Budget_and_spent";
    public static final String CAT_1 = "Hosuing";
    public static final String CAT_2 = "Transportation";
    public static final String CAT_3 = "Food";
    public static final String CAT_4 = "Utilities";
    public static final String CAT_5 = "Healthcare";
    public static final String CAT_6 = "Insurance";
    public static final String CAT_7 = "Save_Invest_Loan";
    public static final String CAT_8 = "Entertainment";
    public static final String CAT_9 = "Personal_Spending";
    public static final String CAT_10 = "Miscellaneous";

    // generic column names
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PRICE";
    public static final String COL_4 = "DATE";

    // Overview table create statement
    private static final String CREATE_OVERVIEW_TABLE = "CREATE TABLE " +
            OVERVIEW + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + Budget_COL + " DOUBLE," +
            CAT_1 + " DOUBLE," + CAT_2 + " DOUBLE," + CAT_3 + " DOUBLE," + CAT_4 + " DOUBLE," +
            CAT_5 + " DOUBLE," + CAT_6 + " DOUBLE," + CAT_7 + " DOUBLE," + CAT_8 + " DOUBLE," +
            CAT_9 + " DOUBLE," + CAT_10 + " DOUBLE" + ")";

    // new table for house category
    public static final String HOUSING = "housing_table";
    public static final String TRANS = "transportation_table";
    public static final String FOOD = "food_table";
    public static final String UTIL = "utilities_table";
    public static final String HEALTH = "healthcare_table";
    public static final String INS = "insurance_table";
    public static final String SIL = "sil_table";
    public static final String ENT = "entertainment_table";
    public static final String PER = "personal_spending_table";
    public static final String MIS = "miscellaneous_table";


    private static final String CREATE_HOUSING_TABLE = "CREATE TABLE " + HOUSING + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " DOUBLE," + COL_4 + " TEXT" + ")";
    private static final String CREATE_TRANS_TABLE = "CREATE TABLE " + TRANS + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " DOUBLE," + COL_4 + " TEXT" + ")";
    private static final String CREATE_FOOD_TABLE = "CREATE TABLE " + FOOD + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " DOUBLE," + COL_4 + " TEXT" + ")";
    private static final String CREATE_UTIL_TABLE = "CREATE TABLE " + UTIL + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " DOUBLE," + COL_4 + " TEXT" + ")";
    private static final String CREATE_HEALTH_TABLE = "CREATE TABLE " + HEALTH + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " DOUBLE," + COL_4 + " TEXT" + ")";
    private static final String CREATE_INS_TABLE = "CREATE TABLE " + INS + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " DOUBLE," + COL_4 + " TEXT" + ")";
    private static final String CREATE_SIL_TABLE = "CREATE TABLE " + SIL + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " DOUBLE," + COL_4 + " TEXT" + ")";
    private static final String CREATE_ENT_TABLE = "CREATE TABLE " + ENT + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " DOUBLE," + COL_4 + " TEXT" + ")";
    private static final String CREATE_PER_TABLE = "CREATE TABLE " + PER + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " DOUBLE," + COL_4 + " TEXT" + ")";
    private static final String CREATE_MIS_TABLE = "CREATE TABLE " + MIS + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " DOUBLE," + COL_4 + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    //
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_OVERVIEW_TABLE);
        db.execSQL(CREATE_HOUSING_TABLE);
        db.execSQL(CREATE_TRANS_TABLE);
        db.execSQL(CREATE_FOOD_TABLE);
        db.execSQL(CREATE_UTIL_TABLE);
        db.execSQL(CREATE_HEALTH_TABLE);
        db.execSQL(CREATE_INS_TABLE);
        db.execSQL(CREATE_SIL_TABLE);
        db.execSQL(CREATE_ENT_TABLE);
        db.execSQL(CREATE_PER_TABLE);
        db.execSQL(CREATE_MIS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ OVERVIEW);
        db.execSQL("DROP TABLE IF EXISTS "+ HOUSING);
        db.execSQL("DROP TABLE IF EXISTS "+ TRANS);
        db.execSQL("DROP TABLE IF EXISTS "+ FOOD);
        db.execSQL("DROP TABLE IF EXISTS "+ UTIL);
        db.execSQL("DROP TABLE IF EXISTS "+ HEALTH);
        db.execSQL("DROP TABLE IF EXISTS "+ INS);
        db.execSQL("DROP TABLE IF EXISTS "+ SIL);
        db.execSQL("DROP TABLE IF EXISTS "+ ENT);
        db.execSQL("DROP TABLE IF EXISTS "+ PER);
        db.execSQL("DROP TABLE IF EXISTS "+ MIS);
        onCreate(db);
    }

    public boolean insertData(String name,Double price,String date, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,price);
        contentValues.put(COL_4,date);
        long result = db.insert(tableMatcher(category),null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ tableMatcher(category),null);
        return res;
    }

    public Cursor getDatabyId(String id, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ tableMatcher(category) + " WHERE " + COL_1 + "=" + id,null);
        return res;
    }

    public void initializeBudget(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Budget_COL,0);
        contentValues.put(CAT_1,0);
        contentValues.put(CAT_2,0);
        contentValues.put(CAT_3,0);
        contentValues.put(CAT_4,0);
        contentValues.put(CAT_5,0);
        contentValues.put(CAT_6,0);
        contentValues.put(CAT_7,0);
        contentValues.put(CAT_8,0);
        contentValues.put(CAT_9,0);
        contentValues.put(CAT_10,0);
        // initialize spending row
        long insertSpent = db.insert(OVERVIEW,null ,contentValues);
        if(insertSpent == -1)
            System.out.println("Spent failed to initialize");
        else
            System.out.println("Spent initialized");
        // initialize budget row
        long insertBudget = db.insert(OVERVIEW,null ,contentValues);
        if(insertBudget == -1)
            System.out.println("Budget failed to initialize");
        else
            System.out.println("Budget initialized");
    }

    public boolean updateBudget(ArrayList<Double> list, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(Budget_COL,list.get(0));
        System.out.println(list.get(0));
        contentValues.put(CAT_1,list.get(1));
        contentValues.put(CAT_2,list.get(2));
        contentValues.put(CAT_3,list.get(3));
        contentValues.put(CAT_4,list.get(4));
        contentValues.put(CAT_5,list.get(5));
        contentValues.put(CAT_6,list.get(6));
        contentValues.put(CAT_7,list.get(7));
        contentValues.put(CAT_8,list.get(8));
        contentValues.put(CAT_9,list.get(9));
        contentValues.put(CAT_10,list.get(10));
        // id here should be 2, budget is stored at 2nd row
        long result = db.update(OVERVIEW, contentValues, "ID = ?",new String[] { id });
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getBudgetData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ OVERVIEW,null);
        return res;
    }

    public boolean updateData(String id,String name,Double price,String date, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,price);
        contentValues.put(COL_4,date);
        db.update(tableMatcher(category), contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(HOUSING, "ID = ?",new String[] {id});
    }

    public String tableMatcher(String Category){
        //System.out.println("\n" + Category + "\n" );
        if (Category.equals("Hosuing") ){
            return HOUSING;
        }else if (Category.equals("Transportation") ){
            return TRANS;
        }else if (Category.equals("Food") ){
            return FOOD;
        }else if (Category.equals("Utilities") ){
            return UTIL;
        }else if (Category.equals("Healthcare") ){
            return HEALTH;
        }else if (Category.equals("Insurance") ){
            return INS;
        }else if (Category.equals("Saving, Invest, Loan") ){
            return SIL;
        }else if (Category.equals("Entertainment") ){
            return ENT;
        }else if (Category.equals("Personal Spending") ){
            return PER;
        }else {
            return MIS;
        }
    }
}
