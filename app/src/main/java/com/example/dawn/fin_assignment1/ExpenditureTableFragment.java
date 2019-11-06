package com.example.dawn.fin_assignment1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dawn.fin_assignment1.db.DBHelper;
import com.example.dawn.fin_assignment1.db.ExpenditureDBHelper;

import java.util.ArrayList;


public class ExpenditureTableFragment extends Fragment {
    ExpenditureDBHelper expenditureDBHelper;
    SQLiteDatabase database;
    DBHelper dbHelper;

    private ArrayList<String> Date = new ArrayList<String>();
    private ArrayList<String> Amount= new ArrayList<String>();
    private ArrayList<String> Type = new ArrayList<String>();
    private ArrayList<String> Label = new ArrayList<String>();

    private ListView listView;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_expenditure_table, container, false);

        initData();

        return view;
    }

    private void initData() {
        listView = (ListView) view.findViewById(R.id.displayListView);
    }

    private void displayData() {
        dbHelper = new DBHelper(this.getActivity());
        database = dbHelper.getReadableDatabase();


        Cursor cursor = database.rawQuery("SELECT * FROM expenditure",null);
        Date.clear();
        Amount.clear();
        Type.clear();
        Label.clear();
        if (cursor.moveToFirst()) {
            do {
                Date.add(cursor.getString(cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_DATE)));

                Type.add(cursor.getString(cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_CATEGORY)));
                Amount.add(cursor.getString(cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_AMOUNT)));
                Label.add(cursor.getString(cursor.getColumnIndex(ExpenditureDBHelper.COLUMN_LABEL)));
            } while (cursor.moveToNext());
        }
        CustomAdapter ca = new CustomAdapter(ExpenditureTableFragment.this.getActivity(),Date,
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
