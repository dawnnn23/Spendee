package com.example.dawn.fin_assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dawn.fin_assignment1.db.DBHelper;
import com.example.dawn.fin_assignment1.db.ExpenditureDBHelper;
import com.example.dawn.fin_assignment1.db.IncomeDBHelper;

public class CurrentStateActivity extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    Button buttonSeeAll;
    Button buttonAdd;

    ExpenditureDBHelper expenditureDBHelper;
    IncomeDBHelper incomeDBHelper;
    SQLiteDatabase database;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_state);

        tv1=(TextView) findViewById(R.id.greetingsUser);
        tv2=(TextView) findViewById(R.id.currentState);
        buttonSeeAll=(Button)findViewById(R.id.seeAllButton);
        buttonAdd=(Button) findViewById(R.id.addButton);

        SharedPreferences sharedPreferences=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name=sharedPreferences.getString("userNameInfo","");
        String surname=sharedPreferences.getString("surnameInfo","");

        tv1.setText("Hello, "+name+" "+surname+"!");

        buttonSeeAll.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //intent for listViews
                openListView();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //intent for forms
                openForm();

            }
        });

        int currentState = summary();
        String state = Integer.toString(currentState);
        tv2.setText(state +" EUR");

    }

    public void openForm(){
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }
    public void openListView(){
        Intent intent = new Intent(this, DisplayTablesActivity.class);
        startActivity(intent);
    }
    public int summary(){
        dbHelper = new DBHelper(this);
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT amount FROM expenditure",null);
        Cursor cursor1 = database.rawQuery("SELECT amount FROM income",null);
        int sumExpenditures=0;
        int sumIncomes=0;
        if (cursor.moveToFirst()) {
            do {
                sumExpenditures+=Integer.parseInt(cursor.getString(cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_AMOUNT)));

            } while(cursor.moveToNext());
        }

        if(cursor1.moveToFirst()){
            do{
                sumIncomes+=Integer.parseInt(cursor1.getString(cursor1.getColumnIndex(IncomeDBHelper.COLUMN_AMOUNT)));
            } while (cursor1.moveToNext());
        }

        return sumIncomes-sumExpenditures;

    }

    @Override
    protected void onSaveInstanceState(Bundle state){
        String name = tv1.getText().toString();
        String surname = tv2.getText().toString();


        super.onSaveInstanceState(state);
        state.putString("name",name);
        state.putString("surname",surname);

    }

    @Override
    protected void onRestoreInstanceState(Bundle state){
        super.onRestoreInstanceState(state);
        tv1.setText(state.getString("name"));
        tv2.setText(state.getString("surname"));
    }

}
