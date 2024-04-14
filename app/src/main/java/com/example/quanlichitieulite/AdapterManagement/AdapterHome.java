package com.example.quanlichitieulite.AdapterManagement;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.quanlichitieulite.Frament.BudgetFragment;
import com.example.quanlichitieulite.Frament.MainFragment;
import com.example.quanlichitieulite.Frament.ProfileFragment;
import com.example.quanlichitieulite.Frament.TransactionFragment;

public class AdapterHome extends FragmentStateAdapter {
    public AdapterHome(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MainFragment();
            case 1:
                return new TransactionFragment();
            case 2:
                return new BudgetFragment();
            case 3:
                return new ProfileFragment();
            default:
                return new MainFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
