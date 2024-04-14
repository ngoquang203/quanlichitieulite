package com.example.quanlichitieulite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.quanlichitieulite.AdapterManagement.CategoryAdapterExpence;
import com.example.quanlichitieulite.Datasqlitemanagement.ServiceSpent;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Expence extends AppCompatActivity {
    private List<ServiceSpent> serviceappList;
    private Spinner spinner;
    private CategoryAdapterExpence categoryAdapterExpence;
    private TextView dateTextInputEditText,timeTextInputEditText,supMoney;
    private TextInputEditText expence_money;

    private TextInputEditText expence_description;
    private Button expence_button;
    private ImageButton expence_back;
    private String Money,Description,IDservice,dates,times,Nameservice;
    private SharedPreferences sharedPreferences;
    private int IDspent;
    private long SumCollect,SumSpent,SumNow;
    private DecimalFormat df = new DecimalFormat("###,###,###.## VND");
    private SQLiteManagement sqLiteManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expence);

        Init();
        clickEditText();
        clickSaveDateExpence();
        clickBackHome();
        updateSupMoney();

    }
    private void Init(){
        sqLiteManagement = new SQLiteManagement(Expence.this);
        sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);
        spinner = findViewById(R.id.expence_spinner);
        serviceappList = sqLiteManagement.getDataServiceSpent();
        categoryAdapterExpence = new CategoryAdapterExpence(this,R.layout.item_selected,serviceappList);
        spinner.setAdapter(categoryAdapterExpence);

        dateTextInputEditText = findViewById(R.id.expence_date);
        timeTextInputEditText = findViewById(R.id.expence_time);

        IDservice = serviceappList.get(spinner.getSelectedItemPosition()).getNameservice();
        expence_back = findViewById(R.id.expence_back);
        expence_money = findViewById(R.id.expence_money);
        expence_description = findViewById(R.id.expence_description);
        expence_button = findViewById(R.id.expence_button);
        supMoney = findViewById(R.id.expence_supMoney);

        SumCollect = sharedPreferences.getLong("SumCollect",0);
        SumSpent = sharedPreferences.getLong("SumSpent",0);
        SumNow = SumCollect - SumSpent;
    }
    private void updateSupMoney() {
        expence_money.addTextChangedListener(new TextWatcher() {
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

    private void clickSaveDateExpence(){
        expence_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Money = expence_money.getText().toString();
                Description = expence_description.getText().toString();

                IDservice = serviceappList.get(spinner.getSelectedItemPosition()).getIDservicespent();
                Nameservice = serviceappList.get(spinner.getSelectedItemPosition()).getNameservice();
                dates = dateTextInputEditText.getText().toString();
                times = timeTextInputEditText.getText().toString();
                // Định dạng pattern đầu vào
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

                // Định dạng pattern đầu ra
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

                // Chuyển đổi chuỗi sang Date
                Date date = null;
                try {
                    date = sdf1.parse(dates);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                if(SumNow - Float.valueOf(Money) < 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Expence.this);
                    builder.setMessage("Số tiền chi của bạn vượt số tiền hiện có");
                    builder.setNegativeButton("Ok", null);
                    AlertDialog alert = builder.create();
                    alert.show();
                }else{
                    // Định dạng Date sang chuỗi mới
                    String formattedString = sdf2.format(date);
                    if(Money.length() != 0 && Description.length() != 0 ){
                        sqLiteManagement.InsertDetailSpent(IDservice,Nameservice,Long.valueOf(Money),Description,"julianday('"+formattedString + "')",times,SumNow);
                        sqLiteManagement.updateSumSpent();
                        Log.e("TEST","julianday('"+formattedString + "')");
                        Intent intent = new Intent(Expence.this,Home.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    private void clickBackHome(){
        expence_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Expence.this, Home.class);
                startActivity(intent);
            }
        });
    }
}