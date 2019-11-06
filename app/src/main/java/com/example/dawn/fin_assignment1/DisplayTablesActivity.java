package com.example.dawn.fin_assignment1;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class DisplayTablesActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private IncomeTableFragment incomeTableFragment;
    private ExpenditureTableFragment expenditureTableFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tables);

        mMainFrame=(FrameLayout) findViewById(R.id.main_frame_tables);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav_tables);

        incomeTableFragment=new IncomeTableFragment();
        expenditureTableFragment =new ExpenditureTableFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_tables, incomeTableFragment).commit();
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_income_table:
                        setFragment(incomeTableFragment);
                        return true;
                    case R.id.nav_expenditure_table:
                        setFragment(expenditureTableFragment);
                        return true;
                }
                return false;

            }

            private void setFragment(Fragment fragment) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame_tables, fragment);
                fragmentTransaction.commit();
            }


        });

    }
}
