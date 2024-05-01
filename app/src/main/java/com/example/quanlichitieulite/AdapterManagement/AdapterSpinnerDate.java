package com.example.quanlichitieulite.AdapterManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlichitieulite.R;

import java.util.List;

public class AdapterSpinnerDate extends ArrayAdapter<String> {
    public AdapterSpinnerDate(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected,parent,false);

        TextView date = convertView.findViewById(R.id.selected_text);

        String textDate = this.getItem(position);

        date.setText(textDate);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date,null);

        String textDate = this.getItem(position);
        TextView date = convertView.findViewById(R.id.item_textDate);
        date.setText(textDate);
        return convertView;
    }
}
