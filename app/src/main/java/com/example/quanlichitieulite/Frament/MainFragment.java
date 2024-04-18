package com.example.quanlichitieulite.Frament;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quanlichitieulite.AdapterManagement.AdapterBill;
import com.example.quanlichitieulite.Datasqlitemanagement.BillData;
import com.example.quanlichitieulite.Datasqlitemanagement.BudgetData;
import com.example.quanlichitieulite.Datasqlitemanagement.CollectMoney;
import com.example.quanlichitieulite.Datasqlitemanagement.DetailColect;
import com.example.quanlichitieulite.Datasqlitemanagement.SpentMoney;
import com.example.quanlichitieulite.Datasqlitemanagement.Users;
import com.example.quanlichitieulite.Expence;
import com.example.quanlichitieulite.Income;
import com.example.quanlichitieulite.PlanMoney;
import com.example.quanlichitieulite.R;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainFragment extends Fragment {

    private View view;
    private SharedPreferences sharedPreferences;
    private long sumCollect,sumSpent;
    private Button addIncomeButton,addExpneceButton,buttonPlanMoney;
    private TextView textName,SumCollect,SumSpent,SumNow,textErron;
    private SQLiteManagement sqLiteManagement;
    private Users users;
    private ArrayList<BillData> listData;
    private ArrayList<BudgetData> tableSpent;
    private ArrayList<BudgetData> tableCollect;
    private String dateStartInit,dateEndInit;
    private ListView listView;
    private ArrayList<BillData> listViewData;

    private SimpleDateFormat simpleDateFormat;
    private AdapterBill adapterBill;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        Init();
        setData();
        clickButton();
        return view;
    }

    private void Init() {
        sharedPreferences = getActivity().getSharedPreferences("loginData",MODE_PRIVATE);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        sqLiteManagement = new SQLiteManagement(getContext());
        users = sqLiteManagement.getDataUser();
        SpentMoney spentMoney = sqLiteManagement.getDataSpentMoney();
        CollectMoney collectMoney = sqLiteManagement.getDataCollectMoney();
        setDateInit();
        Log.e("DATE",dateStartInit + " " + dateEndInit);
        tableSpent = (ArrayList<BudgetData>) sqLiteManagement.getListDataBudgetSpent(changDate(dateStartInit),changDate(dateEndInit));
        tableCollect = (ArrayList<BudgetData>) sqLiteManagement.getListDataBudgetCollect(changDate(dateStartInit),changDate(dateEndInit));
        sumCollect = getSum(tableCollect);
        sumSpent = getSum(tableSpent);
        sharedPreferences.edit().putLong("SumCollect",collectMoney.getSumCollect()).apply();
        sharedPreferences.edit().putLong("SumSpent",spentMoney.getSumSpent()).apply();
        SumCollect = view.findViewById(R.id.main_sumCollect);
        SumSpent = view.findViewById(R.id.main_sumSpent);
        SumNow = view.findViewById(R.id.main_sumNow);
        addIncomeButton = view.findViewById(R.id.main_addIncomeButton);
        addExpneceButton = view.findViewById(R.id.main_addExpenceButton);
        buttonPlanMoney = view.findViewById(R.id.main_buttonPlanMoney);
        textName = view.findViewById(R.id.main_name);
        listData = (ArrayList<BillData>) sqLiteManagement.getListDataSpentAndCollect();
        textErron = view.findViewById(R.id.main_textViewErron);
        listView = view.findViewById(R.id.main_viewpager2);
        listViewData = (ArrayList<BillData>) sqLiteManagement.getListDataSpentAndCollectInDay(changDate(simpleDateFormat.format(Calendar.getInstance().getTime())));
        setTextViewErron();
        adapterBill = new AdapterBill(getContext(),listViewData,true);
        listView.setAdapter(adapterBill);

        listView.setDivider(null);
    }
    private void setTextViewErron(){
        if(listViewData.isEmpty()){
            textErron.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }else{
            textErron.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }

    private long getSum(ArrayList<BudgetData> table){
        long x = 0;
        for(int i = 0;i<table.size();++i){
            x+=table.get(i).getPrice();
        }
        return x;
    }
    private void setDateInit() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        dateStartInit = simpleDateFormat.format(calendar.getTime());
        int lastday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH,lastday);
        dateEndInit = simpleDateFormat.format(calendar.getTime());
    }
    public String changDate(String strDate){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(date).toString();
    }

    private void setData() {
        DecimalFormat df = new DecimalFormat("###,###,###.## VND");
        SumCollect.setText(df.format(sumCollect));
        SumSpent.setText(df.format(sumSpent));
        SumNow.setText(df.format(sumCollect - sumSpent));
        textName.setText(users.getNames());
    }


    private void clickButton() {
        addIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Income.class);
                startActivity(intent);
            }
        });
        addExpneceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Expence.class);
                startActivity(intent);
            }
        });
        buttonPlanMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlanMoney.class);
                startActivity(intent);
            }
        });
    }
}