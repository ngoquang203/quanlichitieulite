package com.example.quanlichitieulite;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlichitieulite.Datasqlitemanagement.Users;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;
import com.google.android.material.textfield.TextInputEditText;

public class SettingApp extends AppCompatActivity {
    private ImageView imageButtonBack;
    private LinearLayout changeInfo;
    private LinearLayout changePass;
    private Switch changeTheme;
    private Boolean checkTheme;
    private SharedPreferences sharedPreferences;
    private String phone,sex;
    private Users users;
    private SQLiteManagement sqLiteManagement;
    private UiModeManager uiModeManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_app);
        Init();
        setCLickListenerButton();

    }
    private void NotificationChangeTheme(boolean CheckTheme){
        // Tạo AlertDialog và thiết lập nút xóa
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa chuyển chủ đề này không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(CheckTheme){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sharedPreferences.edit().putBoolean("theme",false).commit();
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sharedPreferences.edit().putBoolean("theme",true).commit();
                }
            }
        });
        builder.setNegativeButton("Không", null);

        // Hiển thị AlertDialog
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void setCLickListenerButton() {
        changeTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationChangeTheme(checkTheme);
            }
        });
        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingApp.this,Home.class);
                startActivity(intent);
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
    }
    private void clickChangePass(){
        sharedPreferences.edit().putBoolean("passCode",true).commit();
        Intent intent = new Intent(this,PassCodeView.class);
        startActivity(intent);
    }

    private void clickButtonBack(ImageButton imageButton,Dialog dialog) {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void clickChangeInfo() {
        Dialog dialog = new Dialog(this);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(SettingApp.this);
                    builder.setMessage("Bạn có muốn thay đổi không?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            sqLiteManagement.InsertUser(name,sex,phone,email);
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
                    Toast.makeText(SettingApp.this, "Bạn chưa điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    private void Init() {
        sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);
        imageButtonBack = findViewById(R.id.dialogSetting_back);
        changeInfo = findViewById(R.id.dialogSetting_changeInfo);
        changePass = findViewById(R.id.dialogSetting_changePass);
        changeTheme = findViewById(R.id.dialogSetting_theme);
        checkTheme = sharedPreferences.getBoolean("theme",false);
        uiModeManager = getSystemService(UiModeManager.class);
        if(checkTheme){
            changeTheme.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        sqLiteManagement = new SQLiteManagement(this);

    }
}