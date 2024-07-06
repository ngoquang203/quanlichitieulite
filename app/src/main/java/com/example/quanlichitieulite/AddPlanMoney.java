package com.example.quanlichitieulite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlichitieulite.AdapterManagement.AdapterSpinnerDate;
import com.example.quanlichitieulite.AdapterManagement.CategoryAdapterExpence;
import com.example.quanlichitieulite.Datasqlitemanagement.ServiceSpent;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddPlanMoney extends AppCompatActivity {
    private TextView buttonIncome;
    private TextView buttonExpence;
    private TextView buttonAdd,supMoney;
    private Spinner spinner,spinnerService;
    private TextInputEditText money;
    private TextInputEditText content;
    private ImageView imageButton;
    private String strSpinner;
    private SharedPreferences sharedPreferences;
    private ArrayList<String> listSpiner;
    private List<ServiceSpent> serviceappList;
    private SQLiteManagement sqLiteManagement;
    private AdapterSpinnerDate adapterSpinnerDate;
    private DecimalFormat df = new DecimalFormat("###,###,###.## VND");
    private CategoryAdapterExpence categoryAdapterExpence;
    private int IDservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan_money);
        Init();
        clickButtonBack();
        clickAddNewPlanMoney();
        getSelectTedSpinner();
        updateSupMoney();
    }

    private void updateSupMoney() {
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

    private void clickButtonBack() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPlanMoney.this,PlanMoney.class);
                startActivity(intent);
            }
        });
    }

    private void clickAddNewPlanMoney() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                Date dateStart = calendar.getTime();
                Date dateEnd = calendar.getTime();
                if(strSpinner == listSpiner.get(0)){
                    calendar.add(Calendar.DATE,7);
                    dateEnd = calendar.getTime();
                }else if(strSpinner == listSpiner.get(1)){
                    calendar.add(Calendar.MONTH,1);
                    dateEnd = calendar.getTime();
                }else if(strSpinner == listSpiner.get(2)){
                    calendar.add(Calendar.MONTH,3);
                    dateEnd = calendar.getTime();
                }else if(strSpinner == listSpiner.get(3)){
                    calendar.add(Calendar.MONTH,6);
                    dateEnd = calendar.getTime();
                }else if(strSpinner == listSpiner.get(4)){
                    calendar.add(Calendar.YEAR,1);
                    dateEnd = calendar.getTime();
                }else if(strSpinner == listSpiner.get(5)){
                    calendar.add(Calendar.MONTH,2);
                    dateEnd = calendar.getTime();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formatDateStart = sdf.format(dateStart);
                String formatDateEnd = sdf.format(dateEnd);
                String strMoney = money.getText().toString();
                String strContent = content.getText().toString();
                if(strMoney.length() > 0){
                    sqLiteManagement.InsertPlanMoney(2,IDservice,Long.valueOf(strMoney),formatDateStart,formatDateEnd,strContent);
                    Toast.makeText(AddPlanMoney.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddPlanMoney.this,PlanMoney.class);
                    startActivity(intent);
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddPlanMoney.this);
                    builder.setMessage("Bạn chưa nhập đầy đủ thông tin");
                    builder.setNegativeButton("Ok", null);
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }

    private void getSelectTedSpinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strSpinner = listSpiner.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IDservice = serviceappList.get(position).getIDservicespent();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void Init() {
        sqLiteManagement = new SQLiteManagement(this);
//        buttonIncome = findViewById(R.id.addPlanMoney_buttonIncome);
//        buttonExpence = findViewById(R.id.addPlanMoney_Expence);
        buttonAdd = findViewById(R.id.addPlanMoney_buttonAdd);
        spinner = findViewById(R.id.addPlaneMoney_spinner);
        spinnerService = findViewById(R.id.addPlaneMoney_spinnerService);
        money = findViewById(R.id.addPlanMoney_money);
        content = findViewById(R.id.addPlanMoney_content);
        imageButton = findViewById(R.id.addPlaneMoney_back);
        supMoney = findViewById(R.id.addPlanMoney_Supmoney);

        serviceappList = sqLiteManagement.getDataServiceSpent();
        categoryAdapterExpence = new CategoryAdapterExpence(this,R.layout.item_selected,serviceappList);
        spinnerService.setAdapter(categoryAdapterExpence);

        listSpiner = new ArrayList<>();
        listSpiner.add("1 tuần");
        listSpiner.add("1 tháng");
        listSpiner.add("3 tháng");
        listSpiner.add("6 tháng");
        listSpiner.add("1 năm");
        listSpiner.add("2 năm");
        adapterSpinnerDate = new AdapterSpinnerDate(this,R.layout.item_selected,listSpiner);
        spinner.setAdapter(adapterSpinnerDate);
    }
}