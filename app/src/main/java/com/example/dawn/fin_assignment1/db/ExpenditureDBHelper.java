package com.example.dawn.fin_assignment1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.dawn.fin_assignment1.db.model.ExpenditureModel;

public class ExpenditureDBHelper {

        public DBHelper dbHelper;


        public static final String TABLE_NAME = "expenditure";
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
                        COLUMN_DATE + " text not null, " +
                        COLUMN_CATEGORY + " text not null);";

        public ExpenditureDBHelper(Context context) {
                dbHelper=DBHelper.getInstance(context);
        }


        public void addExpenditure(String label, String date, String type, String amount) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(ExpenditureDBHelper.COLUMN_LABEL,label);
                values.put(ExpenditureDBHelper.COLUMN_DATE,date);
                values.put(ExpenditureDBHelper.COLUMN_CATEGORY,type);
                values.put(ExpenditureDBHelper.COLUMN_AMOUNT,amount);
                db.insert(ExpenditureDBHelper.TABLE_NAME,"",values);
        }

}
