package com.example.quanlichitieulite.Frament;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlichitieulite.Datasqlitemanagement.Users;
import com.example.quanlichitieulite.R;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;

public class ProfileFragment extends Fragment {
    private View view;
    private TextView userName,changeInfo,nameInfo,sexInfo,emailInfo;
//    private TextView phoneNumberInfo;
    private String phone,sex;
    private Users users;
    private SQLiteManagement sqLiteManagement;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        Init();
        clickChangeInformation();
        return view;
    }

    private void clickChangeInformation() {
        changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
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