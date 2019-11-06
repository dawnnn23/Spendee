package com.example.dawn.fin_assignment1.db.model;


public class ExpenditureModel {

    public String date;
    public String amount;
    public String type;
    public String label;

    public ExpenditureModel(String date, String amount, String type, String label) {
        this.date=date;
        this.amount=amount;
        this.type=type;
        this.label=label;
    }


}

