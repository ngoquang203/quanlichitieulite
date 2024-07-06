package com.example.quanlichitieulite.Frament;

import static android.content.Context.MODE_PRIVATE;


import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlichitieulite.Datasqlitemanagement.Users;
import com.example.quanlichitieulite.PassCodeView;
import com.example.quanlichitieulite.R;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;
import com.example.quanlichitieulite.SettingApp;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ProfileFragment extends Fragment{
    private View view;
    private TextView userName,changeInfo,nameInfo,sexInfo,emailInfo;
//    private TextView phoneNumberInfo;
    private Users users;
    private SQLiteManagement sqLiteManagement;
    private SharedPreferences sharedPreferences;
    final Calendar myCalendar = Calendar. getInstance () ;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    private boolean changePass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        Init();
        clickChangeInformation();
        changPassListener();
        return view;
    }

    private void changPassListener() {
        if(changePass){
            createDialogSetting();
            sharedPreferences.edit().putBoolean("passCode",false).commit();
        }
    }

    private void clickChangeInformation() {
        changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialogSetting();
            }
        });
    }

    private void createDialogSetting() {
        Intent intent = new Intent(getContext(), SettingApp.class);
        startActivity(intent);
    }





    

    private void Init() {
        sharedPreferences = getActivity().getSharedPreferences("loginData",MODE_PRIVATE);
        changePass = sharedPreferences.getBoolean("passCode",false);
        sqLiteManagement = new SQLiteManagement(getContext());
        userName = view.findViewById(R.id.profile_userName);
        changeInfo = view.findViewById(R.id.profile_changeInformation);
        nameInfo = view.findViewById(R.id.profile_nameInfo);
        sexInfo = view.findViewById(R.id.profile_sexInfo);

//        phoneNumberInfo = view.findViewById(R.id.profile_phoneNumberInfo);
        emailInfo = view.findViewById(R.id.profile_emailInfo);
        getDataInforMation();
    }
    private void getDataInforMation() {
        users = sqLiteManagement.getUsers();
        userName.setText(users.getNames());
        nameInfo.setText(users.getNames());
        if(users.getSex() != null) sexInfo.setText(users.getSex());
        else sexInfo.setText("_ _");
//        if(users.getNumberPhone() != null) phoneNumberInfo.setText(users.getNumberPhone());
//        else phoneNumberInfo.setText("_ _");
        if(users.getEmail() != null) emailInfo.setText(users.getEmail());
        else emailInfo.setText("_ _");
    }




}