package com.example.quanlichitieulite.Frament;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlichitieulite.AdapterManagement.AdapterBudget;
import com.example.quanlichitieulite.Datasqlitemanagement.BudgetData;
import com.example.quanlichitieulite.Datasqlitemanagement.ServiceCollect;
import com.example.quanlichitieulite.Datasqlitemanagement.ServiceSpent;
import com.example.quanlichitieulite.R;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class BudgetFragment extends Fragment {

    private View view;
    private SQLiteManagement sqLiteManagement;

    private TextView buttonIncome,buttonExpnece;
    private TextView selectedDate,sumMoneyText,textErron;
    private String dateStartInit,dateEndInit;
    private SharedPreferences sharedPreferences;
    private int IDcollect,IDspent;

    private boolean checkButton;
    private ArrayList<BudgetData> tableData;
    private boolean clickButtonDialog;
    private PieChart pieChart;
    private SimpleDateFormat simpleDateFormat;
    private ListView listView;
    private AdapterBudget adapterBudget;
    private LinearLayout layout;
    private ArrayList<ServiceCollect> serviceCollects;
    private ArrayList<ServiceSpent> serviceSpents;
    private List<String> NameCollect;
    private List<String> NameSpent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_budget, container, false);
        Init();
        SumMoney();
        clickButton();
        clickSelectedDate();
//        setInitDataPieChart();
        getDataOfCollect();
        return view;
    }

    private void getDataOfCollect(){
        List<PieEntry> entries = new ArrayList<>();
        long[] dataCollect = new long[NameCollect.size()];

        Arrays.fill(dataCollect,0);
        for(int i = 0;i<tableData.size();++i){
            String str = tableData.get(i).getIDservice();
            Log.e("DATA",str);
            dataCollect[Integer.valueOf(str.charAt(str.length() - 1)) -48] += tableData.get(i).getPrice();
            Log.e("dataCollect[" + String.valueOf(Integer.valueOf(str.charAt(str.length() - 1))-48) + "]",String.valueOf(tableData.get(i).getPrice()) );
        }
        for(int i = 0;i < dataCollect.length;++i){
            if(dataCollect[i] != 0){
                entries.add(new PieEntry(dataCollect[i],NameCollect.get(i)));
            }
        }
        adapterBudget = new AdapterBudget(getContext(), (ArrayList<PieEntry>) entries);
        listView.setAdapter(adapterBudget);
        PieDataSet pieDataSet = new PieDataSet(entries, "Pie Chart");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setDrawValues(false);

        PieData pieData = new PieData(pieDataSet);
        pieChart.getLegend().setTextColor(getResources().getColor(R.color.light));
        pieChart.setDrawEntryLabels(false);
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
    private void getDataOfSpent(){
        List<PieEntry> entries = new ArrayList<>();
        long[] dataSpent = new long[NameSpent.size()];

        Arrays.fill(dataSpent,0);
        for(int i = 0;i<tableData.size();++i){
            String str = tableData.get(i).getIDservice().toString();
            dataSpent[Integer.valueOf(str.charAt(str.length() - 1)) -48] += tableData.get(i).getPrice();
            Log.e("dataCollect[" + String.valueOf(Integer.valueOf(str.charAt(str.length() - 1))-48) + "]",String.valueOf(tableData.get(i).getPrice()) );
        }
        for(int i = 0;i < dataSpent.length;++i){
            if(dataSpent[i] != 0){
                entries.add(new PieEntry(dataSpent[i],NameSpent.get(i)));
            }
        }
        adapterBudget = new AdapterBudget(getContext(), (ArrayList<PieEntry>) entries);
        listView.setAdapter(adapterBudget);
        PieDataSet pieDataSet = new PieDataSet(entries, "Pie Chart");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextSize(16);
        pieDataSet.setValueLineColor(getResources().getColor(R.color.light));
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setSliceSpace(5f);
        PieData pieData = new PieData(pieDataSet);
        pieChart.getLegend().setTextColor(getResources().getColor(R.color.light));
        pieChart.setDrawEntryLabels(false);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }


    private void setTextViewErron(){
        if(tableData.isEmpty()){
            textErron.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
        }else{
            textErron.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
        }
    }

    private void updateDataClickButtonDialog(boolean clickButtonDialog) {
        if(clickButtonDialog == true){
            if(checkButton == true){

                tableData = (ArrayList<BudgetData>) sqLiteManagement.getListDataBudgetCollect(changDate(dateStartInit),changDate(dateEndInit));

                getDataOfCollect();
                setTextViewErron();
                clickButtonDialog = false;
            }
            else{
                tableData = (ArrayList<BudgetData>) sqLiteManagement.getListDataBudgetSpent(changDate(dateStartInit),changDate(dateEndInit));
                getDataOfSpent();
                setTextViewErron();
                clickButtonDialog = false;
            }
        }
        SumMoney();
    }

    private void clickButton() {

        buttonIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButton = true;
                buttonIncome.setBackgroundColor(getResources().getColor(R.color.blue80));
                buttonExpnece.setBackgroundColor(getResources().getColor(R.color.dark));
                tableData = (ArrayList<BudgetData>) sqLiteManagement.getListDataBudgetCollect(changDate(dateStartInit),changDate(dateEndInit));
                getDataOfCollect();
                setTextViewErron();
                SumMoney();
            }
        });
        buttonExpnece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButton = false;
                buttonIncome.setBackgroundColor(getResources().getColor(R.color.dark));
                buttonExpnece.setBackgroundColor(getResources().getColor(R.color.blue80));
                tableData = (ArrayList<BudgetData>) sqLiteManagement.getListDataBudgetSpent(changDate(dateStartInit),changDate(dateEndInit));
                getDataOfSpent();
                setTextViewErron();
                SumMoney();
            }
        });


    }
    private void SumMoney(){
        DecimalFormat df = new DecimalFormat("###,###,###.## VND");
        long sum = 0;
        for(int i = 0;i<tableData.size();++i){
            sum+=tableData.get(i).getPrice();
        }
        sumMoneyText.setText(df.format(sum));
    }

    private void setDateInit() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        dateStartInit = simpleDateFormat.format(calendar.getTime());
        int lastday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH,lastday);
        dateEndInit = simpleDateFormat.format(calendar.getTime());
        selectedDate.setText(dateStartInit + " - " + dateEndInit);
    }

    private void clickSelectedDate() {
        selectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.selected_date);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(true);
                ImageButton imageButton = dialog.findViewById(R.id.selected_date_buttonClose);
                TextView dateStart = dialog.findViewById(R.id.selected_date_textDateStart);
                TextView dateEnd = dialog.findViewById(R.id.selected_date_textDateEnd);
                Button button = dialog.findViewById(R.id.selected_date_buttonInsert);
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                clickEditText(dateStart,dateEnd);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedDate.setText(dateStart.getText().toString() + " - " + dateEnd.getText().toString());
                        dateStartInit = dateStart.getText().toString();
                        dateEndInit = dateEnd.getText().toString();
                        dialog.dismiss();
                        clickButtonDialog = true;
                        updateDataClickButtonDialog(clickButtonDialog);
                    }
                });
                dialog.show();
            }
        });

    }

    private void clickEditText(TextView dateStart,TextView dateEnd){

        Date datetimeStart,datetimeEnd;
        try {
            datetimeStart = simpleDateFormat.parse(dateStartInit);
            datetimeEnd = simpleDateFormat.parse(dateEndInit);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarStart.setTime(datetimeStart);
        calendarEnd.setTime(datetimeEnd);

        dateStart.setText(dateStartInit);
        dateEnd.setText(dateEndInit);


        dateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDateStart(dateStart,calendarStart,simpleDateFormat);
            }
        });
        dateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDateEnd(dateEnd,calendarEnd,simpleDateFormat);
            }
        });

    }
    private void selectedDateStart(TextView date,Calendar calendar,SimpleDateFormat simpleDateFormat){

        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);

                date.setText(simpleDateFormat.format(calendar.getTime()));
                dateStartInit = date.getText().toString();
            }
        }, year,month,day);
        datePickerDialog.show();
    }
    private void selectedDateEnd(TextView date,Calendar calendar,SimpleDateFormat simpleDateFormat){

        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);

                date.setText(simpleDateFormat.format(calendar.getTime()));
                dateEndInit = date.getText().toString();
            }
        }, year,month,day);
        datePickerDialog.show();
    }

    public String changDate(String strDate){
        Date date = null;
        try {
            date = simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(date).toString();
    }





    private void Init() {
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        sqLiteManagement = new SQLiteManagement(getContext());
        serviceCollects = (ArrayList<ServiceCollect>) sqLiteManagement.getDataServiceCollect();
        serviceSpents = (ArrayList<ServiceSpent>) sqLiteManagement.getDataServiceSpent();
        getDataService();
        buttonIncome = view.findViewById(R.id.budget_textIncome);
        buttonExpnece = view.findViewById(R.id.budget_textExpence);
        selectedDate = view.findViewById(R.id.budget_textSelectedDate);
        sumMoneyText = view.findViewById(R.id.budget_sumMoneyText);
        layout = view.findViewById(R.id.budget_linerlayout);
        setDateInit();
        Log.e("DATA",dateStartInit + " " + dateEndInit);
        tableData = new ArrayList<>();
        tableData = (ArrayList<BudgetData>) sqLiteManagement.getListDataBudgetCollect(changDate(dateStartInit),changDate(dateEndInit));
        clickButtonDialog = false;
        textErron = view.findViewById(R.id.budget_textViewErron);
        pieChart = view.findViewById(R.id.budget_pieChart);
        listView = view.findViewById(R.id.budget_listview);
        buttonIncome.setBackgroundColor(getResources().getColor(R.color.blue80));
        checkButton = true;

    }
    private void getDataService(){
        NameCollect = new ArrayList<>();
        NameSpent = new ArrayList<>();
        NameCollect.add("");
        NameSpent.add("");
        for(int i = 0;i<serviceCollects.size();++i){
            NameCollect.add(serviceCollects.get(i).getNameservice().toString());
        }
        for(int i = 0;i<serviceSpents.size();++i){
            NameSpent.add(serviceSpents.get(i).getNameservice().toString());
        }
        for(int i = 0;i<NameCollect.size();++i){
            Log.e("SERVICE",NameCollect.get(i) + " ");
        }
    }
}