package com.example.dawn.fin_assignment1;


import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import com.example.dawn.fin_assignment1.db.DBHelper;
import com.example.dawn.fin_assignment1.db.ExpenditureDBHelper;
import com.example.dawn.fin_assignment1.db.IncomeDBHelper;
import com.example.dawn.fin_assignment1.db.model.IncomeModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeTableFragment extends Fragment {

    IncomeDBHelper incomeDBHelper;
    SQLiteDatabase database;
    DBHelper dbHelper;

    private ArrayList<String> Date = new ArrayList<String>();
    private ArrayList<String> Amount= new ArrayList<String>();
    private ArrayList<String> Type = new ArrayList<String>();
    private ArrayList<String> Label = new ArrayList<String>();

    private ListView listView;

    View view;

    Button buttonStartDate;
    Button buttonEndDate;

    private DatePickerDialog.OnDateSetListener dateSetListenerFrom;
    private DatePickerDialog.OnDateSetListener dateSetListenerTo;

    private String dateStart="";
    private String dateEnd="";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_income_table, container, false);

        initData();
        displayData();
        registerListeners();

        return view;
    }

    public void registerListeners(){

            Calendar calender = Calendar.getInstance();
            final int year = calender.get(Calendar.YEAR);
            final int month = calender.get(Calendar.MONTH);
            final int day = calender.get(Calendar.DAY_OF_MONTH);

            buttonStartDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    DatePickerDialog dialog = new DatePickerDialog(getContext(),
                            android.R.style.Theme_Material_Dialog_MinWidth,
                            dateSetListenerFrom,
                            year, month, day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                    dialog.show();
                }
            });


            dateSetListenerFrom = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month + 1;

                    dateStart = "" + year + month + dayOfMonth;
                    buttonStartDate.setText("End Date: " + (String.format("%02d", dayOfMonth)) + "/" + (String.format("%02d", month)) + "/" + year);
                    showFilteredEntries();
                }
            };


            buttonEndDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog dialog = new DatePickerDialog(getContext(),
                            android.R.style.Theme_Material_Dialog_MinWidth,
                            dateSetListenerTo,
                            year, month, day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                    dialog.show();
                }
            });

            dateSetListenerTo = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month + 1;
                    dateEnd = "" + year + String.format("%02d", month) + String.format("%02d", dayOfMonth);
                    buttonEndDate.setText("End Date: " + (String.format("%02d", dayOfMonth)) + "/" + (String.format("%02d", month)) + "/" + year);
                    showFilteredEntries();
                }
            };
    }

    private void initData() {
        listView = (ListView) view.findViewById(R.id.displayListViewIncome);
        buttonStartDate=(Button)view.findViewById(R.id.button_date1);
        buttonEndDate=(Button)view.findViewById(R.id.button_date2);


    }

    private void displayData() {
        dbHelper = new DBHelper(this.getActivity());
        database = dbHelper.getReadableDatabase();


        Cursor cursor = database.rawQuery("SELECT * FROM income",null);
        Date.clear();
        Amount.clear();
        Type.clear();
        Label.clear();
        if (cursor.moveToFirst()) {
            do {
                Date.add(cursor.getString(cursor.getColumnIndex(IncomeDBHelper.COLUMN_DATE)));

                Type.add(cursor.getString(cursor.getColumnIndex(IncomeDBHelper.COLUMN_CATEGORY)));
                Amount.add(cursor.getString(cursor.getColumnIndex(IncomeDBHelper.COLUMN_AMOUNT)));
                Label.add(cursor.getString(cursor.getColumnIndex(IncomeDBHelper.COLUMN_LABEL)));
            } while (cursor.moveToNext());
        }
        CustomAdapter ca = new CustomAdapter(IncomeTableFragment.this.getActivity(),Date,
                Amount,Type,Label);
        listView.setAdapter(ca);
        //code to set adapter to populate list
        cursor.close();
    }

    public void showFilteredEntries(){
        Log.d("BANANA FORM ", "showFilteredEntries: "+ dateStart);
        Log.d("BANANA TO ", "showFilteredEntries: "+ dateEnd);
        if (dateStart.equals("") || dateEnd.equals("")){
            return;
        }

        Log.d("BANANA", "showFilteredEntries: "+"SELECT * FROM income WHERE date"  +
                " BETWEEN '" + dateStart + "' AND '" + dateEnd + "'");

        Cursor cursor = database.rawQuery("SELECT * FROM income WHERE date"  +
                " BETWEEN '" + dateStart + "' AND '" + dateEnd + "'",null);
        Date.clear();
        Amount.clear();
        Type.clear();
        Label.clear();
        if (cursor.moveToFirst()) {
            do {
                Log.d("KARKOLI ", "showFilteredEntries: ");
                Date.add(cursor.getString(cursor.getColumnIndex(IncomeDBHelper.COLUMN_DATE)));

                Type.add(cursor.getString(cursor.getColumnIndex(IncomeDBHelper.COLUMN_CATEGORY)));
                Amount.add(cursor.getString(cursor.getColumnIndex(IncomeDBHelper.COLUMN_AMOUNT)));
                Label.add(cursor.getString(cursor.getColumnIndex(IncomeDBHelper.COLUMN_LABEL)));
            } while (cursor.moveToNext());
        }
        CustomAdapter ca = new CustomAdapter(IncomeTableFragment.this.getActivity(),Date,
                Amount,Type,Label);
        listView.setAdapter(ca);
        //code to set adapter to populate list
        cursor.close();

    }
    @Override
    public void onResume(){
        displayData();
        super.onResume();
    }


}
