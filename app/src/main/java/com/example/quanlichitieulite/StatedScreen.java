package com.example.quanlichitieulite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlichitieulite.Datasqlitemanagement.CollectMoney;
import com.example.quanlichitieulite.Datasqlitemanagement.SpentMoney;
import com.example.quanlichitieulite.Datasqlitemanagement.Users;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;
import com.google.android.material.textfield.TextInputEditText;

public class StatedScreen extends AppCompatActivity {
    private TextInputEditText name;
    private TextView confirm;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stated_screen);
        Init();
        clickConfirm();
    }

    private void clickConfirm() {
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = name.getText().toString();
                if(strName.isEmpty()){
                    Toast.makeText(StatedScreen.this, "Bạn chưa nhập tên", Toast.LENGTH_SHORT).show();
                }else{
                    SQLiteManagement sqLiteManagement = new SQLiteManagement(StatedScreen.this);
                    sqLiteManagement.InsertNameUser(strName);
                    sqLiteManagement.InsertMoneySpent(0);
                    sqLiteManagement.InsertMoneyCollect(0);

                    sqLiteManagement.InsertServiceSpent("EP01","Sinh hoạt","Tiền nhà,nước,điện,...");
                    sqLiteManagement.InsertServiceSpent("EP02","Giải trí","Đi chơi,làm đẹp,...");
                    sqLiteManagement.InsertServiceSpent("EP03","Học tập","Mua sách vở,học thêm");
                    sqLiteManagement.InsertServiceSpent("EP04","Sức khỏe","Khám bệnh, thể thao");
                    sqLiteManagement.InsertServiceSpent("EP05","Quần áo","Mua quần áo,giầy dép,...");
                    sqLiteManagement.InsertServiceSpent("EP06","Ăn uống","Đi chợ,đi ăn ngoài,...");
                    sqLiteManagement.InsertServiceSpent("EP07","Đi lại","Đổ xăng, bảo dưỡng xe");
                    sqLiteManagement.InsertServiceSpent("EP08","Khác","Không trong các mục trên");

                    sqLiteManagement.InsertServiceCollect("IC01","Lương tháng","Tiền lĩnh lương");
                    sqLiteManagement.InsertServiceCollect("IC02","Tiền thưởng","Tiền được thưởng");
                    sqLiteManagement.InsertServiceCollect("IC03","Tiền lãi ngân hàng","Tiền lãi");
                    sqLiteManagement.InsertServiceCollect("IC04","Khác","Không trong các mục trên");
                    sqLiteManagement.createTriggerSpent();
                    sqLiteManagement.createTriggerCollect();

                    sharedPreferences.edit().putBoolean("login",true).apply();
                    Intent intent = new Intent(StatedScreen.this,Home.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void Init() {
        name = findViewById(R.id.stated_name);
        confirm = findViewById(R.id.stated_confirm);
        sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);
    }
}