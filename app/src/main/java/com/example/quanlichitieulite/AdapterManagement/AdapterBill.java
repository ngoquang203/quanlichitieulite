package com.example.quanlichitieulite.AdapterManagement;

import static java.lang.System.currentTimeMillis;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlichitieulite.Datasqlitemanagement.BillData;
import com.example.quanlichitieulite.R;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdapterBill extends BaseAdapter {
    Context context;
    ArrayList<BillData> arrayList;
    LayoutInflater layoutInflater;

    boolean dateOff;

    public AdapterBill(Context context,ArrayList<BillData> arrayList,boolean dateOff){
        this.context = context;
        this.arrayList = arrayList;
        this.layoutInflater = LayoutInflater.from(context);
        this.dateOff = dateOff;
    }
    @Override
    public int getCount() {
        return arrayList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.item_bill,null);
        TextView title = convertView.findViewById(R.id.bill_title);
        TextView money = convertView.findViewById(R.id.bill_money);
        TextView time = convertView.findViewById(R.id.bill_time);
        TextView date1 = convertView.findViewById(R.id.bill_date1);
        DecimalFormat df = new DecimalFormat("###,###,###.## VND");
        BillData billData = arrayList.get(position);
        Log.e("Test",billData.getNameservice());
        if(billData != null){
            if(billData.getIdDataCategory()%2==0){
                money.setText("-"+df.format(billData.getPrice()));
                money.setTextColor(Color.RED);
            }
            else{

                money.setText("+"+ df.format(billData.getPrice()));
                money.setTextColor(Color.GREEN);
            }
            title.setText(billData.getNameservice());
            time.setText(billData.getTimes());
            if(position!=0 && billData.getDates().equals(arrayList.get(position - 1).getDates()) && dateOff == false){
                date1.setVisibility(View.GONE);
            }
            else if(dateOff == true){
                date1.setVisibility(View.GONE);
            }
            date1.setText(billData.getDates());
        }
        return convertView;
    }
    public void setFilterList(ArrayList<BillData> filterList){
        this.arrayList = filterList;
        notifyDataSetChanged();
    }
}
