package com.example.quanlichitieulite.AdapterManagement;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlichitieulite.Datasqlitemanagement.ServiceCollect;
import com.example.quanlichitieulite.Datasqlitemanagement.ServiceSpent;
import com.example.quanlichitieulite.R;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class CategoryAdapterExpence extends ArrayAdapter<ServiceSpent> {

    public CategoryAdapterExpence(@NonNull Context context, int resource, @NonNull List<ServiceSpent> objects) {
        super(context, resource, objects);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected,parent,false);

        TextView title = convertView.findViewById(R.id.selected_text);
        SQLiteManagement sqLiteManagement = new SQLiteManagement(convertView.getContext());

        ServiceSpent serviceapp = this.getItem(position);
        if(serviceapp != null){
            if(serviceapp.getNameservice().equals("Khác")){
                Dialog dialog = new Dialog(getContext());
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.layout_dialog_insertsevice);
                TextInputEditText serviceText = (TextInputEditText) dialog.findViewById(R.id.dialog_service);
                TextInputEditText contentText = (TextInputEditText) dialog.findViewById(R.id.dialog_content);
                Button button = (Button) dialog.findViewById(R.id.dialog_buttonAddService);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        serviceapp.setNameservice(serviceText.getText().toString());
                        serviceapp.setExplain(contentText.getText().toString());
                        title.setText(serviceText.getText().toString());
                        sqLiteManagement.updateServiceSpent(serviceapp.getIDservicespent(),serviceText.getText().toString(),contentText.getText().toString());
                        sqLiteManagement.InsertServiceSpent("Khác","Không trong các mục trên");
                        add(new ServiceSpent(serviceapp.getIDservicespent() + 1,"Khác","Không trong các mục trên"));
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }else{
                title.setText(serviceapp.getNameservice());
            }
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);

        TextView title = convertView.findViewById(R.id.item_titleCategory);
        TextView suptext = convertView.findViewById(R.id.item_suptextCategory);

        ServiceSpent serviceapp = this.getItem(position);
        if(serviceapp != null){
            title.setText(serviceapp.getNameservice());
            suptext.setText(serviceapp.getExplain());
        }
        return convertView;
    }
}
