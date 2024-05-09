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
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlichitieulite.Datasqlitemanagement.Users;
import com.example.quanlichitieulite.PassCodeView;
import com.example.quanlichitieulite.R;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ProfileFragment extends Fragment{
    private View view;
    private TextView userName,changeInfo,nameInfo,sexInfo,emailInfo;
//    private TextView phoneNumberInfo;
    private String phone,sex;
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
        Dialog dialogSetting = new Dialog(getContext());
        dialogSetting.setContentView(R.layout.dialog_setting);
        dialogSetting.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogSetting.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        ImageButton imageButtonBack = dialogSetting.findViewById(R.id.dialogSetting_back);
        LinearLayout changeInfo = dialogSetting.findViewById(R.id.dialogSetting_changeInfo);
        LinearLayout changePass = dialogSetting.findViewById(R.id.dialogSetting_changePass);
        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSetting.dismiss();
            }
        });
        changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickChangeInfo();
            }
        });
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickChangePass();
            }
        });
        dialogSetting.show();
    }


    private void clickChangePass(){
        sharedPreferences.edit().putBoolean("passCode",true).commit();
        Intent intent = new Intent(getContext(),PassCodeView.class);
        startActivity(intent);
    }


    private void clickChangeInfo() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_infor);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        ImageButton imageButton = dialog.findViewById(R.id.dialog_changeInfo_back);
        TextInputEditText userName = dialog.findViewById(R.id.dialog_changeInfo_userName);
        TextInputEditText emailInput = dialog.findViewById(R.id.dialog_changeInfo_email);
        CheckBox male = dialog.findViewById(R.id.dialog_changeInfo_checkBoxMale);
        CheckBox female = dialog.findViewById(R.id.dialog_changeInfo_checkBoxFemale);
        TextView confirm = dialog.findViewById(R.id.dialog_changeInfo_confirm);

        clickButtonBack(imageButton,dialog);

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (buttonView == male) {
                        sex = "Nam";
                        female.setChecked(false);
                    } else if (buttonView == female) {
                        sex = "Nữ";
                        male.setChecked(false);
                    }
                }
            }
        };

        male.setOnCheckedChangeListener(listener);
        female.setOnCheckedChangeListener(listener);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String email = emailInput.getText().toString();
                if(name.length() > 6 && sex.length() >= 2 && email.length() >= 8){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Bạn có muốn thay đổi không?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            sqLiteManagement.InsertUser(name,sex,phone,email);
                            getDataInforMation();
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogs, int which) {
                            dialog.show();
                        }
                    });
                    // Hiển thị AlertDialog
                    AlertDialog alert = builder.create();
                    alert.show();
                    dialog.dismiss();
                }else{
                    Log.e("Data",name + " " + sex + " " + phone + " " + email);
                    Toast.makeText(getContext(), "Bạn chưa điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    private void clickButtonBack(ImageButton imageButton,Dialog dialog) {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

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