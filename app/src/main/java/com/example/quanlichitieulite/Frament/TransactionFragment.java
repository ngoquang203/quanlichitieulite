package com.example.quanlichitieulite.Frament;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quanlichitieulite.AdapterManagement.AdapterBill;
import com.example.quanlichitieulite.Datasqlitemanagement.BillData;
import com.example.quanlichitieulite.R;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class TransactionFragment extends Fragment {

    private View view;
    private SQLiteManagement sqLiteManagement;
    private ListView viewPager2;
    private TextView textView;
    private AdapterBill adapterBill;
    private ArrayList<BillData> listData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_transaction, container, false);
        Init();
        setTextView();
        clickItemListView();
        return view;
    }

    private void clickItemListView() {
        viewPager2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BillData billData = listData.get(position);
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.item_supbill);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                ImageButton button = dialog.findViewById(R.id.detialCategory_button);
                TextView money = dialog.findViewById(R.id.detailCategory_money);
                TextView title = dialog.findViewById(R.id.detailCategory_title);
                TextView content = dialog.findViewById(R.id.detailCategory_content);
                TextView date = dialog.findViewById(R.id.detailCategory_date);
                TextView moneyNow = dialog.findViewById(R.id.detailCategory_moneyNow);
                DecimalFormat df = new DecimalFormat("###,###,###.## VND");

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                if(billData.getIdDataCategory()%2==0){
                    money.setText("-"+df.format(billData.getPrice()));
                    money.setTextColor(Color.RED);
                }
                else{

                    money.setText("+"+ df.format(billData.getPrice()));
                    money.setTextColor(Color.GREEN);
                }
                title.setText(billData.getNameservice());
                content.setText(billData.getContent());
                date.setText(billData.getDates() + ", " + billData.getTimes());
                moneyNow.setText(df.format(billData.getMoneyNow()));
                dialog.show();
            }
        });
    }

    private void setTextView() {
        if(listData.isEmpty()){
            textView.setVisibility(View.VISIBLE);
            viewPager2.setVisibility(View.GONE);
        }
        else{
            textView.setVisibility(View.GONE);
            viewPager2.setVisibility(View.VISIBLE);
        }
    }

    private void Init() {
        sqLiteManagement = new SQLiteManagement(getContext());
        viewPager2 = view.findViewById(R.id.transiton_viewpager2);
        viewPager2.setDivider(null);
        textView = view.findViewById(R.id.transiton_textViewErron);
        listData = (ArrayList<BillData>) sqLiteManagement.getListDataSpentAndCollect();

        for(int i = 0;i<listData.size();++i){
            Log.e("DATA",String.valueOf(listData.get(i).getDates()));
        }
        adapterBill = new AdapterBill(getContext(),listData);
        viewPager2.setAdapter(adapterBill);
    }
}