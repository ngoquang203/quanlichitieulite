package com.example.quanlichitieulite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.quanlichitieulite.AdapterManagement.CategoryAdapterIncome;
import com.example.quanlichitieulite.Datasqlitemanagement.ServiceCollect;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Income extends AppCompatActivity {
    private List<ServiceCollect> serviceappList;
    private Spinner spinner;
    private CategoryAdapterIncome categoryAdapter;
    private TextView dateTextInputEditText,timeTextInputEditText,supMoney;
    private TextInputEditText income_money;

    private TextInputEditText income_description;
    private Button income_button;
    private ImageView income_back;
    private String Money,Description,dates,times,Nameservice;
    private int IDservice;
    private SharedPreferences sharedPreferences;
    private int IDcollect;
    private long SumCollect,SumSpent,SumNow;
    private DecimalFormat df = new DecimalFormat("###,###,###.## VND");
    private SQLiteManagement sqLiteManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        Init();
        clickEditText();
        clickBackHome();
        clickSaveDateIncome();
        updateSupMoney();

    }
    private void Init(){
        sqLiteManagement = new SQLiteManagement(Income.this);

        spinner = findViewById(R.id.income_spinner);
        serviceappList = sqLiteManagement.getDataServiceCollect();

        categoryAdapter = new CategoryAdapterIncome(this,R.layout.item_selected,serviceappList);
        spinner.setAdapter(categoryAdapter);

        dateTextInputEditText = findViewById(R.id.income_date);
        timeTextInputEditText = findViewById(R.id.income_time);

        IDservice = serviceappList.get(spinner.getSelectedItemPosition()).getIDservicecollect();

        income_back = findViewById(R.id.income_back);
        income_money = findViewById(R.id.income_money);
        income_description = findViewById(R.id.income_description);
        income_button = findViewById(R.id.income_button);
        supMoney = findViewById(R.id.income_supMoney);
        sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);
        IDcollect = sharedPreferences.getInt("IDcollect",0);
        SumCollect = sharedPreferences.getLong("SumCollect",0);
        SumSpent = sharedPreferences.getLong("SumSpent",0);
        SumNow = SumCollect - SumSpent;
    }

    private void updateSupMoney() {

        income_money.addTextChangedListener(new TextWatcher() {
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


    private void clickEditText(){
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");


        timeTextInputEditText.setText(simpleTimeFormat.format(calendar.getTime()));
        dateTextInputEditText.setText(simpleDateFormat.format(calendar.getTime()));

        dateTextInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate(calendar,simpleDateFormat);
            }
        });
        timeTextInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTime(calendar,simpleTimeFormat);
            }
        });
    }
    private void selectedTime(Calendar calendar,SimpleDateFormat simpleTimeFormat){
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(0,0,0,hourOfDay,minute);

                timeTextInputEditText.setText(simpleTimeFormat.format(calendar.getTime()));
            }
        },hour,minute,true);
        timePickerDialog.show();
    }
    private void selectedDate(Calendar calendar,SimpleDateFormat simpleDateFormat){

        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);

                dateTextInputEditText.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year,month,day);
        datePickerDialog.show();

    }
    private void clickSaveDateIncome(){
        income_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Money = income_money.getText().toString();
                Description = income_description.getText().toString();

                IDservice = serviceappList.get(spinner.getSelectedItemPosition()).getIDservicecollect();
                Nameservice = serviceappList.get(spinner.getSelectedItemPosition()).getNameservice();
                dates = dateTextInputEditText.getText().toString();
                times = timeTextInputEditText.getText().toString();
                // Định dạng pattern đầu vào
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

                // Định dạng pattern đầu ra
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

                // Chuyển đổi chuỗi sang Date
                Date date = null;
                Time time = null;
                try {
                    date = sdf1.parse(dates);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                // Định dạng Date sang chuỗi mới
                String formattedString = sdf2.format(date);
                if(!Money.isEmpty() && !Description.isEmpty()){
                    if(Long.valueOf(Money) > 0){
                        sqLiteManagement.InsertDetailCollect(IDservice,Nameservice,Long.valueOf(Money),Description,"julianday('"+formattedString + "')",times,SumNow);
                        sqLiteManagement.updateSumCollect();
                        Intent intent = new Intent(Income.this,Home.class);
                        startActivity(intent);
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Income.this);
                        builder.setMessage("Số tiền bạn nhập không lớn hơn 0");
                        builder.setNegativeButton("Ok", null);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(Income.this);
                    builder.setMessage("Bạn chưa nhập đầy đủ thông tin");
                    builder.setNegativeButton("Ok", null);
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }
    private void clickBackHome(){
        income_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Income.this,Home.class);
                startActivity(intent);
            }
        });
    }
}