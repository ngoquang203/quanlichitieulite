package com.example.quanlichitieulite.AdapterManagement;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.quanlichitieulite.Datasqlitemanagement.PlanMonney;
import com.example.quanlichitieulite.Datasqlitemanagement.ServiceSpent;
import com.example.quanlichitieulite.R;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterPlanMoney extends BaseAdapter {
    Context context;
    ArrayList<PlanMonney> arrayList;
    LayoutInflater layoutInflater;
    private List<ServiceSpent> serviceappList;

    public AdapterPlanMoney(Context context,ArrayList<PlanMonney> arrayList,List<ServiceSpent> serviceappList){
        this.context = context;
        this.arrayList = arrayList;
        this.layoutInflater = LayoutInflater.from(context);
        this.serviceappList = serviceappList;
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
        convertView = layoutInflater.inflate(R.layout.item_planmoney,null);
        TextView textNamePlan = convertView.findViewById(R.id.item_planmoney_idselec);
        TextView textMoneyPlan = convertView.findViewById(R.id.item_planmoney_moneyplan);
        TextView textDateStart = convertView.findViewById(R.id.item_planmoney_datestart);
        TextView textDateEnd = convertView.findViewById(R.id.item_planmoney_dateend);
        TextView textMoneyNow = convertView.findViewById(R.id.item_planmoney_moneynow);
        ProgressBar progressBar = convertView.findViewById(R.id.item_planmoney_progressBar);
        TextView supProgressBar = convertView.findViewById(R.id.item_planmoney_supProgressbar);



        PlanMonney planMoney = arrayList.get(position);
        if(planMoney != null){
            DecimalFormat df = new DecimalFormat("###,###,###.## VND");
            String strNameService = null;
                for(int i = 0;i<serviceappList.size();++i){
                    if(serviceappList.get(i).getIDservicespent() == planMoney.getIDservice()){
                        strNameService = serviceappList.get(i).getNameservice();
                        break;
                    }
                }
                textNamePlan.setText(strNameService.toString());
                textNamePlan.setTextColor(convertView.getResources().getColor(R.color.red100));

            textMoneyPlan.setText(df.format(planMoney.getSummoney()));
            textDateStart.setText(planMoney.getDateStart());
            textDateEnd.setText(planMoney.getDateEnd());
            textMoneyNow.setText(df.format(planMoney.getMoneyNow()));
            int value = 0;
            float x = (Float.valueOf(planMoney.getMoneyNow()) / Float.valueOf(planMoney.getSummoney()) * 100);
            if(x<=100){
                value = (int) x;
            }else{
                value = 100;
            }

            progressBar.setProgress(value);
            progressBar.setProgressDrawable(convertView.getResources().getDrawable(R.drawable.custom_spinner));
            supProgressBar.setText(value + "%");
            Log.e("DATA",planMoney.getNamePlanMoney() + " " + planMoney.getIDservice());
        }
        return convertView;
    }
}
