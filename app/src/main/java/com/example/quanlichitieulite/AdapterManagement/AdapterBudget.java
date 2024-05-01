package com.example.quanlichitieulite.AdapterManagement;

import android.content.Context;
import android.security.identity.CredentialDataResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlichitieulite.Datasqlitemanagement.BillData;
import com.example.quanlichitieulite.R;
import com.github.mikephil.charting.data.PieEntry;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterBudget extends BaseAdapter {
    Context context;
    ArrayList<PieEntry> arrayList;
    LayoutInflater layoutInflater;

    boolean dateOff;

    public AdapterBudget(Context context,ArrayList<PieEntry> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        this.layoutInflater = LayoutInflater.from(context);
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

        convertView = layoutInflater.inflate(R.layout.item_service,null);
        DecimalFormat df = new DecimalFormat("###,###,###.## VND");
        TextView title = convertView.findViewById(R.id.item_service_title);
        TextView money = convertView.findViewById(R.id.item_service_money);
        title.setText(arrayList.get(position).getLabel());
        money.setText(df.format(arrayList.get(position).getValue()));
        return convertView;
    }
}
