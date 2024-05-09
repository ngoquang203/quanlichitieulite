package com.example.quanlichitieulite.AdapterManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlichitieulite.Datasqlitemanagement.NotificationPlanMoney;
import com.example.quanlichitieulite.R;

import java.util.ArrayList;

public class AdapterNotificationPlanMoney extends BaseAdapter{
    Context context;
    ArrayList<NotificationPlanMoney> arrayList;
    LayoutInflater layoutInflater;
    public AdapterNotificationPlanMoney(Context context,ArrayList<NotificationPlanMoney> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.item_notification_planmoney,null);
        TextView nameService = convertView.findViewById(R.id.planMoney_notification_nameService);
        TextView dateStart = convertView.findViewById(R.id.planMoney_notification_dateStart);
        TextView dateEnd = convertView.findViewById(R.id.planMoney_notification_dateEnd);
        NotificationPlanMoney notificationPlanMoney = arrayList.get(position);
        if(notificationPlanMoney != null){
            nameService.setText(notificationPlanMoney.getNameService());
            dateStart.setText(notificationPlanMoney.getDateStart());
            dateEnd.setText(notificationPlanMoney.getDateEnd());
        }
        return convertView;
    }
}
