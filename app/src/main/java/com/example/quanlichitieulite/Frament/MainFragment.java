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
import android.widget.TextView;

import com.example.quanlichitieulite.Datasqlitemanagement.CollectMoney;
import com.example.quanlichitieulite.Datasqlitemanagement.DetailColect;
import com.example.quanlichitieulite.Datasqlitemanagement.SpentMoney;
import com.example.quanlichitieulite.Datasqlitemanagement.Users;
import com.example.quanlichitieulite.Expence;
import com.example.quanlichitieulite.Income;
import com.example.quanlichitieulite.PlanMoney;
import com.example.quanlichitieulite.R;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;


public class MainFragment extends Fragment {

    private View view;
    private SharedPreferences sharedPreferences;
    private long sumCollect,sumSpent;
    private Button addIncomeButton,addExpneceButton,buttonPlanMoney;
    private TextView textMonth,SumCollect,SumSpent,SumNow;
    private SQLiteManagement sqLiteManagement;


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
        sqLiteManagement = new SQLiteManagement(getContext());
        Users users = sqLiteManagement.getDataUser();
        SpentMoney spentMoney = sqLiteManagement.getDataSpentMoney();
        CollectMoney collectMoney = sqLiteManagement.getDataCollectMoney();
        sumCollect = collectMoney.getSumCollect();
        sumSpent = spentMoney.getSumSpent();
        sharedPreferences.edit().putLong("SumCollect",collectMoney.getSumCollect()).apply();
        sharedPreferences.edit().putLong("SumSpent",spentMoney.getSumSpent()).apply();
        SumCollect = view.findViewById(R.id.main_sumCollect);
        SumSpent = view.findViewById(R.id.main_sumSpent);
        SumNow = view.findViewById(R.id.main_sumNow);
        addIncomeButton = view.findViewById(R.id.main_addIncomeButton);
        addExpneceButton = view.findViewById(R.id.main_addExpenceButton);
        buttonPlanMoney = view.findViewById(R.id.main_buttonPlanMoney);

    }

    private void setData() {
        DecimalFormat df = new DecimalFormat("###,###,###.## VND");
        LocalDate today = LocalDate.now();
        textMonth = view.findViewById(R.id.main_month);
        textMonth.setText("Tháng "+ today.getMonthValue());
        SumCollect.setText(df.format(sumCollect));
        SumSpent.setText(df.format(sumSpent));
        SumNow.setText(df.format(sumCollect - sumSpent));
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