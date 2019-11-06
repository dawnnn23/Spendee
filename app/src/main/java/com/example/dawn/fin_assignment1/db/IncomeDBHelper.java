package com.example.dawn.fin_assignment1.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dawn.fin_assignment1.db.model.IncomeModel;

import java.util.ArrayList;
import java.util.List;

public class IncomeDBHelper {
    private DBHelper dbHelper;

    public static final String TABLE_NAME = "income";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LABEL = "label";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_AMOUNT = "amount";


    public static final String TABLE_CREATE =
            "create table " + TABLE_NAME + "(" +
                    COLUMN_ID + " integer primary key autoincrement not null, " +
                    COLUMN_LABEL + " text not null, " +
                    COLUMN_AMOUNT + " integer, " +
                    COLUMN_DATE + " date not null, " +
                    COLUMN_CATEGORY + " text not null);";

    public IncomeDBHelper(Context context) {
        dbHelper=DBHelper.getInstance(context);
    }



    public void addIncome(String label, String date, String type, String amount) {
        String[] dayArray = date.split("-");
        String correctDate = dayArray[0] + dayArray[1] + dayArray[2];

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IncomeDBHelper.COLUMN_LABEL,label);
        values.put(IncomeDBHelper.COLUMN_DATE,correctDate);
        values.put(IncomeDBHelper.COLUMN_CATEGORY,type);
        values.put(IncomeDBHelper.COLUMN_AMOUNT,amount);
        db.insert(IncomeDBHelper.TABLE_NAME,"",values);
    }

}
