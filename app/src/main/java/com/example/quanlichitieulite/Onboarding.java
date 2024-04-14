package com.example.quanlichitieulite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlichitieulite.AdapterManagement.AdapterOnboarding;


public class Onboarding extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private TextView button;
    private ImageView indicator1,indicator2,indicator3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        Init();
        setViewPage2();
        clickStarted();
    }

    private void clickStarted() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Onboarding.this,StatedScreen.class);
                startActivity(intent);
            }
        });
    }

    private void setViewPage2() {
        AdapterOnboarding adapterOnboarding = new AdapterOnboarding(this);
        viewPager2.setAdapter(adapterOnboarding);

        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (viewPager2.getCurrentItem() < adapterOnboarding.getItemCount() - 1) {
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                    if(viewPager2.getCurrentItem() == 0){
                        indicator1.setImageResource(R.color.blue100);
                        indicator2.setImageResource(R.color.dark20);
                        indicator3.setImageResource(R.color.dark20);
                    }else if(viewPager2.getCurrentItem() == 1){
                        indicator1.setImageResource(R.color.dark20);
                        indicator2.setImageResource(R.color.blue100);
                        indicator3.setImageResource(R.color.dark20);
                    }else if(viewPager2.getCurrentItem() == 2){
                        indicator1.setImageResource(R.color.dark20);
                        indicator2.setImageResource(R.color.dark20);
                        indicator3.setImageResource(R.color.blue100);
                    }

                } else {
                    viewPager2.setCurrentItem(0);
                    indicator1.setImageResource(R.color.blue100);
                    indicator2.setImageResource(R.color.dark20);
                    indicator3.setImageResource(R.color.dark20);
                }
                handler.postDelayed(this, 5000);
            }
        };
        handler.postDelayed(runnable, 5000);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        indicator1.setImageResource(R.color.blue100);
                        indicator2.setImageResource(R.color.dark20);
                        indicator3.setImageResource(R.color.dark20);
                        break;
                    case 1:
                        indicator1.setImageResource(R.color.dark20);
                        indicator2.setImageResource(R.color.blue100);
                        indicator3.setImageResource(R.color.dark20);
                        break;
                    case 2:
                        indicator1.setImageResource(R.color.dark20);
                        indicator2.setImageResource(R.color.dark20);
                        indicator3.setImageResource(R.color.blue100);
                        break;
                }
            }
        });

    }

    private void Init() {
        viewPager2 = findViewById(R.id.onboarding_viewpage2);
        button = findViewById(R.id.onboarding_button);
        indicator1 = findViewById(R.id.indicator1);
        indicator2 = findViewById(R.id.indicator2);
        indicator3 = findViewById(R.id.indicator3);
    }
}