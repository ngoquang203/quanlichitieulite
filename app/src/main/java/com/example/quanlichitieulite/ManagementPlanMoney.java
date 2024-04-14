package com.example.quanlichitieulite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quanlichitieulite.AdapterManagement.AdapterPlanMoney;
import com.example.quanlichitieulite.Datasqlitemanagement.PlanMonney;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManagementPlanMoney extends AppCompatActivity {
    private SQLiteManagement sqLiteManagement;
    private ImageButton imageButton;
    private ListView listView;
    private ArrayList<PlanMonney> arrayList;
    private SharedPreferences sharedPreferences;
    private AdapterPlanMoney adapterPlanMoney;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_plan_money);
        Init();
        clickBackButton();
        clickLongItemListView();
    }
    private void clickLongItemListView() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                // Tạo AlertDialog và thiết lập nút xóa
                AlertDialog.Builder builder = new AlertDialog.Builder(ManagementPlanMoney.this);
                builder.setMessage("Bạn có muốn xóa item này không?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý xóa item ở đây
//                        try {
//                            PlanMonney.deletePlanMoneyInTable(arrayList.get(position).getIDplan());
//                        } catch (Exception e) {
//                            throw new RuntimeException(e);
//                        }
//                        arrayList.remove(position);
//                        adapterPlanMoney.notifyDataSetChanged();
                        sqLiteManagement.deletePlanMoney(arrayList.get(position).getIDplan());
                        arrayList.remove(position);
                        adapterPlanMoney.notifyDataSetChanged();
                        listViewIsNull();
                    }
                });
                builder.setNegativeButton("Hủy", null);

                // Hiển thị AlertDialog
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });
    }

    private void clickBackButton() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagementPlanMoney.this,PlanMoney.class);
                startActivity(intent);
            }
        });
    }


    private void Init() {
        sqLiteManagement = new SQLiteManagement(this);
        imageButton = findViewById(R.id.managementPlanMoney_back);
        listView = findViewById(R.id.managementPlanMoney_listview);
        sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);
        arrayList = new ArrayList<>();
        textView = findViewById(R.id.managementPlanMoney_textview);
        arrayList = (ArrayList<PlanMonney>) sqLiteManagement.getListDataPlanMoney();


            adapterPlanMoney = new AdapterPlanMoney(this,arrayList);
            listView.setAdapter(adapterPlanMoney);

        listViewIsNull();
    }

    private void listViewIsNull() {
        if(arrayList.isEmpty()){
            textView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }
        else{
            textView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }
}