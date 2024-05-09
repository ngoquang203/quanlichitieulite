package com.example.quanlichitieulite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PassCodeView extends AppCompatActivity implements View.OnClickListener {
    private View view01,view02,view03,view04,view05;
    private Button button00,button01,button02,button03,button04,button05,button06,button07,button08,button09,buttonDel;
    private ArrayList<String> numberList;
    private String passCode = "";
    private String number01,number02,number03,number04,number05;
    private SharedPreferences sharedPreferences;
    private boolean selectedActivity;
    private String PasscodeData;
    private TextView supText;
    private boolean changPassCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_code_view);
        Init();
        setSupText();
    }

    private void Init() {
        sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);
        selectedActivity = sharedPreferences.getBoolean("login",false);
        PasscodeData = sharedPreferences.getString("passcode","000000");
        changPassCode = sharedPreferences.getBoolean("passCode",false);
        numberList = new ArrayList<>();
        view01 = findViewById(R.id.passcode_view01);
        view02 = findViewById(R.id.passcode_view02);
        view03 = findViewById(R.id.passcode_view03);
        view04 = findViewById(R.id.passcode_view04);
        view05 = findViewById(R.id.passcode_view05);

        button00 = findViewById(R.id.passcode_number0);
        button01 = findViewById(R.id.passcode_number01);
        button02 = findViewById(R.id.passcode_number02);
        button03 = findViewById(R.id.passcode_number03);
        button04 = findViewById(R.id.passcode_number04);
        button05 = findViewById(R.id.passcode_number05);
        button06 = findViewById(R.id.passcode_number06);
        button07 = findViewById(R.id.passcode_number07);
        button08 = findViewById(R.id.passcode_number08);
        button09 = findViewById(R.id.passcode_number09);
        buttonDel = findViewById(R.id.passcode_numberDelete);

        button00.setOnClickListener(this);
        button01.setOnClickListener(this);
        button02.setOnClickListener(this);
        button03.setOnClickListener(this);
        button04.setOnClickListener(this);
        button05.setOnClickListener(this);
        button06.setOnClickListener(this);
        button07.setOnClickListener(this);
        button08.setOnClickListener(this);
        button09.setOnClickListener(this);
        buttonDel.setOnClickListener(this);

        supText = findViewById(R.id.passcode_supText);
    }
    private void setSupText(){
        if(changPassCode){
            supText.setText("Hãy nhập để thay đổi mật khẩu");
        }else{
            if(selectedActivity){
                supText.setText("Hãy nhập mật khẩu để tiếp tục");
            }else{
                supText.setText("Hãy tạo mật khẩu để bảo mật");
            }
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.passcode_number0){
            numberList.add("0");
            passNumber(numberList);
        }else if(v.getId() == R.id.passcode_number01){
            numberList.add("1");
            passNumber(numberList);
        }else if(v.getId() == R.id.passcode_number02){
            numberList.add("2");
            passNumber(numberList);
        }else if(v.getId() == R.id.passcode_number03){
            numberList.add("3");
            passNumber(numberList);
        }else if(v.getId() == R.id.passcode_number04){
            numberList.add("4");
            passNumber(numberList);
        }else if(v.getId() == R.id.passcode_number05){
            numberList.add("5");
            passNumber(numberList);
        }else if(v.getId() == R.id.passcode_number06){
            numberList.add("6");
            passNumber(numberList);
        }else if(v.getId() == R.id.passcode_number07){
            numberList.add("7");
            passNumber(numberList);
        }else if(v.getId() == R.id.passcode_number08){
            numberList.add("8");
            passNumber(numberList);
        }else if(v.getId() == R.id.passcode_number09){
            numberList.add("9");
            passNumber(numberList);
        }else if(v.getId() == R.id.passcode_numberDelete){
            numberList.clear();
            passNumber(numberList);
        }
    }

    private void passNumber(ArrayList<String> numberList) {
        if(numberList.size() == 0){
            view01.setBackgroundResource(R.drawable.circle_passcode_dark);
            view02.setBackgroundResource(R.drawable.circle_passcode_dark);
            view03.setBackgroundResource(R.drawable.circle_passcode_dark);
            view04.setBackgroundResource(R.drawable.circle_passcode_dark);
            view05.setBackgroundResource(R.drawable.circle_passcode_dark);
        }else{
            if(numberList.size() == 1){
                number01 = numberList.get(0);
                view01.setBackgroundResource(R.drawable.circle_passcode);
            }else if(numberList.size() == 2){
                number02 = numberList.get(1);
                view02.setBackgroundResource(R.drawable.circle_passcode);
            }else if(numberList.size() == 3){
                number03 = numberList.get(2);
                view03.setBackgroundResource(R.drawable.circle_passcode);
            }else if(numberList.size() == 4){
                number04 = numberList.get(3);
                view04.setBackgroundResource(R.drawable.circle_passcode);
            }else if(numberList.size() == 5){
                number05 = numberList.get(4);
                view05.setBackgroundResource(R.drawable.circle_passcode);
                passCode = number01 + number02 + number03 + number04 + number05;
                if(changPassCode){
                    savePasscode(passCode);

                }else{
                    if(selectedActivity == true){
                        changeHomePage(passCode);
                    }else{
                        savePasscode(passCode);
                    }
                }
            }
        }
    }

    private void changeHomePage(String passCode) {
        if(passCode.equals(PasscodeData)){
            Intent intent = new Intent(this,Home.class);
            startActivity(intent);
        }else{
            numberList.clear();
            passNumber(numberList);
            Toast.makeText(this, "Mật khẩu của bạn không chính xác", Toast.LENGTH_SHORT).show();
        }
    }


    private void savePasscode(String passCode){
        sharedPreferences.edit().putString("passcode",passCode).commit();
        sharedPreferences.edit().putBoolean("login",true).apply();
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }
}