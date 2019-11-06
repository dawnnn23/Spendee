package com.example.dawn.fin_assignment1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dawn.fin_assignment1.db.ExpenditureDBHelper;
import com.example.dawn.fin_assignment1.db.IncomeDBHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFormFragment extends Fragment {

    private EditText amountEdit;
    private EditText dateEdit;
    private EditText labelEdit;
    private RadioGroup typeEdit;
    private Button buttonIncome;
    private RadioButton typeRadio;



    private String amount;
    private String date;
    private String label;
    private String type;

    private IncomeDBHelper incomeDBHelper;
    public IncomeFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_income_form, container, false);
        incomeDBHelper = new IncomeDBHelper(getContext());
        initializeComponents(view);
        registerListeners(view);
        return view;
    }
    public void initializeComponents(View view) {
        amountEdit = view.findViewById(R.id.field_amountIncome);
        dateEdit = view.findViewById(R.id.field_dataIncome);
        labelEdit = view.findViewById(R.id.field_labelIncome);
        typeEdit = view.findViewById(R.id.radioGroupIncomeType);
        buttonIncome = view.findViewById(R.id.buttonSubmitIncome);

    }
    public void registerListeners(View view) {
        buttonIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = amountEdit.getText().toString();
                date = dateEdit.getText().toString();
                label = labelEdit.getText().toString();
                int selectedId = typeEdit.getCheckedRadioButtonId();

                typeRadio = typeEdit.findViewById(selectedId);

                type = typeRadio.getText().toString();

                incomeDBHelper.addIncome(label,date,type,amount);

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle state){
        String amount = amountEdit.getText().toString();
        String date = dateEdit.getText().toString();
        String label = labelEdit.getText().toString();
        int type= typeEdit.getCheckedRadioButtonId();

        super.onSaveInstanceState(state);
        state.putString("amount",amount);
        state.putString("date",date);
        state.putString("label",label);
        state.putInt("type",type);
    }
    @Override
    public void onViewStateRestored(Bundle state){
        super.onViewStateRestored(state);
        if(state!=null){
            amountEdit.setText(state.getString("amount"));
            dateEdit.setText(state.getString("date"));
            labelEdit.setText(state.getString("label"));
            typeEdit.check(state.getInt("type"));
        }
    }



}
