package com.example.dawn.fin_assignment1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ExpandableListView listView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    private FloatingActionButton button;
    EditText userName;
    EditText userSurname;
    Button loginButton;
    //DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        listView =(ExpandableListView) findViewById(R.id.lvExp);
//        initData();
//        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
//        listView.setAdapter(listAdapter);
//        button=(FloatingActionButton) findViewById(R.id.floatingActionButton);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openFormActivity();
//            }
//        });

       // mDatabaseHelper = new DatabaseHelper();

        userName =(EditText)findViewById(R.id.nameField);
        userSurname=(EditText)findViewById(R.id.surnameField);
        loginButton =(Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                saveInfo();
                //intent for summary and name
                openStateActivity();
            }
        });

    }

    public void saveInfo(){
        SharedPreferences sharedPreferences=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("userNameInfo",userName.getText().toString());
        editor.putString("surnameInfo", userSurname.getText().toString());
        editor.apply();

    }



    public void openStateActivity(){
        Intent intent = new Intent(this, CurrentStateActivity.class);
       // intent.putExtra("ID", id);
        startActivity(intent);
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("FOOD");
        listDataHeader.add("LEISURE");
        listDataHeader.add("TRAVEL");
        listDataHeader.add("ACCOMODATION");
        listDataHeader.add("OTHER");

        List<String> food = new ArrayList<>();

        food.add("Falafel");
        food.add("Burek");

        List<String> leisure = new ArrayList<>();
        leisure.add("Drinks");
        leisure.add("Club entrance");

        List<String> travel = new ArrayList<>();
        travel.add("Train ticket to Cph");
        travel.add("Bus ticket to Malmo airport");

        List<String> accomodation = new ArrayList<>();
        accomodation.add("Rent");

        List<String> other = new ArrayList<>();
        other.add("Lipstick");
        other.add("Cosmetics");


        listHash.put(listDataHeader.get(0),food);
        listHash.put(listDataHeader.get(1),leisure);
        listHash.put(listDataHeader.get(2),travel);
        listHash.put(listDataHeader.get(3),accomodation);
        listHash.put(listDataHeader.get(4),other);
    }




}
