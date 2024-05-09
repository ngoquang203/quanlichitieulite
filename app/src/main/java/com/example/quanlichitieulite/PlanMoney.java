package com.example.quanlichitieulite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.quanlichitieulite.AdapterManagement.AdapterNotificationPlanMoney;
import com.example.quanlichitieulite.Datasqlitemanagement.NotificationPlanMoney;
import com.example.quanlichitieulite.Datasqlitemanagement.PlanMonney;
import com.example.quanlichitieulite.Datasqlitemanagement.ServiceSpent;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;

import java.util.ArrayList;
import java.util.List;

public class PlanMoney extends AppCompatActivity {
    private LinearLayout add,list,notification;
    private ImageButton imageButton;
    private ListView listView;
    private SQLiteManagement sqLiteManagement;
    private ArrayList<PlanMonney> arrayList;
    private AdapterNotificationPlanMoney adapterNotificationPlanMoney;
    private ArrayList<NotificationPlanMoney> notificationPlanMoneyArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_money);
        Init();
        clickBackButton();
        clickAddPlanMoney();
        clickManagementList();
        setNotificationPlanMoney();
    }

    private void clickBackButton() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanMoney.this,Home.class);
                startActivity(intent);
            }
        });
    }

    private void clickManagementList() {
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PlanMoney.this,ManagementPlanMoney.class);
                startActivity(intent);
            }
        });
    }

    private void clickAddPlanMoney() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanMoney.this,AddPlanMoney.class);
                startActivity(intent);
            }
        });
    }
    private void setNotificationPlanMoney(){
        boolean check = checkServiceFinish(arrayList);
        if(check){
            notification.setVisibility(View.VISIBLE);
        }else{
            notification.setVisibility(View.GONE);
        }
    }
    private boolean checkServiceFinish(ArrayList<PlanMonney> arrayList){
        boolean check = false;
        for(int i = 0;i<arrayList.size();++i){
            if(arrayList.get(i).getMoneyNow() >= arrayList.get(i).getSummoney()){
                check = true;
                break;
            }
        }
        return check;
    }
    private void setDataNotification(){
        List<ServiceSpent> serviceappList = sqLiteManagement.getDataServiceSpent();
        for(int i = 0;i<arrayList.size();++i){
            if(arrayList.get(i).getMoneyNow() >= arrayList.get(i).getSummoney()){
                notificationPlanMoneyArrayList.add(new NotificationPlanMoney(
                        serviceappList.get(arrayList.get(i).getIDservice() - 1).getNameservice(),
                        arrayList.get(i).getDateStart(),
                        arrayList.get(i).getDateEnd()

                ));
            }
        }
    }

    private void Init() {
        sqLiteManagement = new SQLiteManagement(this);
        add = findViewById(R.id.planMoney_add);
        list = findViewById(R.id.planMoney_list);
        imageButton = findViewById(R.id.planMoney_back);
        notification = findViewById(R.id.planMoney_notification);
        notificationPlanMoneyArrayList = new ArrayList<>();
        listView = findViewById(R.id.planMoney_listView);
        arrayList = (ArrayList<PlanMonney>) sqLiteManagement.getListDataPlanMoney();
        setDataNotification();
        adapterNotificationPlanMoney = new AdapterNotificationPlanMoney(PlanMoney.this,notificationPlanMoneyArrayList);
        listView.setAdapter(adapterNotificationPlanMoney);
    }
}