package com.example.dawn.fin_assignment1;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class FormActivity extends AppCompatActivity {


    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private IncomeFormFragment incomeFormFragment;
    private ExpenditureFormFragment expenditureFormFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        mMainFrame=(FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        incomeFormFragment =new IncomeFormFragment();
        expenditureFormFragment =new ExpenditureFormFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, incomeFormFragment).commit();

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_income:
                        setFragment(incomeFormFragment);
                        return true;
                    case R.id.nav_expenditure:
                        setFragment(expenditureFormFragment);
                        return true;


                }
                return false;

            }

            public void setFragment(Fragment fragment) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.commit();
            }


        });
    }
}
