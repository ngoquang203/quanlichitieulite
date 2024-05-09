package com.example.quanlichitieulite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class LaunchScreen extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private Boolean selectedActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);
        selectedActivity = sharedPreferences.getBoolean("login",false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(selectedActivity.equals(true)){
                    Intent intent = new Intent(LaunchScreen.this,PassCodeView.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(LaunchScreen.this,Onboarding.class);
                    startActivity(intent);
                }
            }
        },3000);
    }
}