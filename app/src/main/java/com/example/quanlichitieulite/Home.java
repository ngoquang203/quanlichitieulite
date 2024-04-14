package com.example.quanlichitieulite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quanlichitieulite.AdapterManagement.AdapterHome;
import com.example.quanlichitieulite.Datasqlitemanagement.CollectMoney;
import com.example.quanlichitieulite.Datasqlitemanagement.SpentMoney;
import com.example.quanlichitieulite.Datasqlitemanagement.Users;
import com.example.quanlichitieulite.SQLitemanagement.SQLiteManagement;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Init();
        even_Click();
        even_bottomNavigator();
    }



    private void even_bottomNavigator() {
        AdapterHome adapterHome = new AdapterHome(this);
        viewPager.setAdapter(adapterHome);
    }

    private void even_Click() {
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_transaction).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menu_budget).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.menu_profile).setChecked(true);
                        break;
                }
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_home){
                    viewPager.setCurrentItem(0);
                }
                else if(item.getItemId() == R.id.menu_transaction){
                    viewPager.setCurrentItem(1);
                }
                else if(item.getItemId() == R.id.menu_budget){
                    viewPager.setCurrentItem(2);
                }
                else if(item.getItemId() == R.id.menu_profile){
                    viewPager.setCurrentItem(3);
                }
                return true;
            }
        });
    }

    private void Init() {
        viewPager = findViewById(R.id.view_page);
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        sharedPreferences = getSharedPreferences("loginData", MODE_PRIVATE);

    }
}