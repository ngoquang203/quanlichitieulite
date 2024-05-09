package com.example.quanlichitieulite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.Calendar;

public class StatedScreen extends AppCompatActivity {
    private TextInputEditText name,money;
    private TextView confirm,supMoney;
    private SharedPreferences sharedPreferences;
    private DecimalFormat df = new DecimalFormat("###,###,###.## VND");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stated_screen);
        Init();
        clickConfirm();
        setListenTextInput();
    }

    private void clickConfirm() {
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = name.getText().toString();

                long Money = Long.valueOf(money.getText().toString());
                if(strName.isEmpty() && Money >=0){
                    Toast.makeText(StatedScreen.this, "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    SQLiteManagement sqLiteManagement = new SQLiteManagement(StatedScreen.this);

                    sqLiteManagement.InsertStarted(strName);
                    sqLiteManagement.InsertMoneySpent(0);
                    sqLiteManagement.InsertMoneyCollect(0);

                    sqLiteManagement.InsertServiceSpent("Sinh hoạt","Tiền nhà,nước,điện,...");
                    sqLiteManagement.InsertServiceSpent("Giải trí","Đi chơi,làm đẹp,...");
                    sqLiteManagement.InsertServiceSpent("Học tập","Mua sách vở,học thêm");
                    sqLiteManagement.InsertServiceSpent("Sức khỏe","Khám bệnh, thể thao");
                    sqLiteManagement.InsertServiceSpent("Quần áo","Mua quần áo,giầy dép,...");
                    sqLiteManagement.InsertServiceSpent("Ăn uống","Đi chợ,đi ăn ngoài,...");
                    sqLiteManagement.InsertServiceSpent("Đi lại","Đổ xăng, bảo dưỡng xe");
                    sqLiteManagement.InsertServiceSpent("Khác","Không trong các mục trên");

                    sqLiteManagement.InsertServiceCollect("Lương tháng","Tiền lĩnh lương");
                    sqLiteManagement.InsertServiceCollect("Tiền thưởng","Tiền được thưởng");
                    sqLiteManagement.InsertServiceCollect("Tiền lãi ngân hàng","Tiền lãi");
                    sqLiteManagement.InsertServiceCollect("Tiền bắt đầu","Tiền bắt đầu khi tạo ứng dụng");
                    sqLiteManagement.InsertServiceCollect("Khác","Không trong các mục trên");
                    sqLiteManagement.createTriggerSumSpent();
                    sqLiteManagement.createTriggerSumCollect();
                    sqLiteManagement.createTriggerSpent();
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);
                    String formattedTime = String.format("%02d:%02d", hour, minute);
                    String formattedDate = String.format("%d-%02d-%02d", year, month + 1, day);
                    String strDate = "julianday('"+formattedDate + "')";

                    sqLiteManagement.InsertDetailCollect(4,"Tiền bắt đầu",Money,"Tiền thêm mới khi vào ứng dụng",strDate,formattedTime,0);
                    sqLiteManagement.updateSumCollect();


                    Intent intent = new Intent(StatedScreen.this,PassCodeView.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void setListenTextInput(){
        money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() > 0){
                    supMoney.setText(df.format(Long.valueOf(s.toString())));
                }
                else{
                    supMoney.setText("0 VND");
                }
            }



            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void Init() {
        name = findViewById(R.id.stated_name);
        money = findViewById(R.id.stated_money);
        supMoney = findViewById(R.id.stated_supMoney);
        confirm = findViewById(R.id.stated_confirm);
        sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);
    }

}