package com.example.dawn.fin_assignment1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "main.db";
    private static final int DATABASE_VERSION = 2;
    private static DBHelper ourInstance = null;

    public static DBHelper getInstance(Context context) {
        if(ourInstance == null) {
            ourInstance = new DBHelper(context);
        }
        return ourInstance;
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ExpenditureDBHelper.TABLE_CREATE);
        db.execSQL(IncomeDBHelper.TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + ExpenditureDBHelper.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + IncomeDBHelper.TABLE_NAME);
        onCreate(db);
    }
}
