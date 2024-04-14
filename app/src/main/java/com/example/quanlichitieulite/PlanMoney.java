package com.example.quanlichitieulite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class PlanMoney extends AppCompatActivity {
    private LinearLayout add,list;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_money);
        Init();
        clickBackButton();
        clickAddPlanMoney();
        clickManagementList();
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

    private void Init() {
        add = findViewById(R.id.planMoney_add);
        list = findViewById(R.id.planMoney_list);
        imageButton = findViewById(R.id.planMoney_back);
    }
}