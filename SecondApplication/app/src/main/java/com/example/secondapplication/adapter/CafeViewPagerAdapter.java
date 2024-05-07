package com.example.secondapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.secondapplication.cafe.FragmentCuli;
import com.example.secondapplication.cafe.FragmentMoka;
import com.example.secondapplication.cafe.FragmentRobusta;

public class CafeViewPagerAdapter extends FragmentStatePagerAdapter {

    public CafeViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentCuli();
            case 1:
                return new FragmentMoka();
            case 2:
                return new FragmentRobusta();
            default:
                return new FragmentCuli();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Culi";
            case 1:
                return "Moka";
            case 2:
                return "Robusta";
            default:
                return "Culi";
        }
    }
}
