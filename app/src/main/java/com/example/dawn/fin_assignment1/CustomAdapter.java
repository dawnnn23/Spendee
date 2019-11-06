package com.example.dawn.fin_assignment1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dawn.fin_assignment1.db.DBHelper;
import com.example.dawn.fin_assignment1.db.ExpenditureDBHelper;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class CustomAdapter extends BaseAdapter {

    private Context mContext;

    DBHelper dbHelper;

    private ArrayList<String> Date = new ArrayList<String>();
    private ArrayList<String> Amount= new ArrayList<String>();
    private ArrayList<String> Type = new ArrayList<String>();
    private ArrayList<String> Label = new ArrayList<String>();

    public CustomAdapter(Context context, ArrayList<String>
            Date, ArrayList<String> Amount, ArrayList<String> Type, ArrayList<String>
                                 Label
    )
    {
        this.mContext = context;
        this.Date = Date;
        this.Amount = Amount;
        this.Type = Type;
        this.Label=Label;
    }

    @Override
    public int getCount() {
        return Label.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final viewHolder holder;
//        ExpenditureDBHelper expenditureDBHelper=new ExpenditureDBHelper(mContext);
        dbHelper = new DBHelper(mContext);

        LayoutInflater layoutInflater;
        if (convertView == null) {
            layoutInflater = (LayoutInflater)
                    mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.display_expenditure_row, null);
            holder = new viewHolder();
            holder.date = (TextView) convertView.findViewById(R.id.t_date);
            holder.label = (TextView) convertView.findViewById(R.id.t_label);
            holder.amount = (TextView) convertView.findViewById(R.id.t_amount);
            holder.type = (TextView) convertView.findViewById(R.id.t_category);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.date.setText(Date.get(position));
        holder.label.setText(Label.get(position));
        holder.amount.setText(Amount.get(position));
        holder.type.setText(Type.get(position));
        return convertView;
    }
    public class viewHolder {
        TextView date;
        TextView label;
        TextView amount;
        TextView type;
    }
}
