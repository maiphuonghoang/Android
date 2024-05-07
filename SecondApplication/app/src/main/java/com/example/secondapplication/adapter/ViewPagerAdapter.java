package com.example.secondapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.secondapplication.fragment.FragmentCafe;
import com.example.secondapplication.fragment.FragmentHome;
import com.example.secondapplication.fragment.FragmentNotification;
import com.example.secondapplication.fragment.FragmentSearch;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int pageNumber = 4;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int pageNumber) {
        super(fm, pageNumber);
        this.pageNumber = pageNumber;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentNotification();
            case 2:
                return new FragmentSearch();
            case 3:
                return new FragmentCafe();
            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return pageNumber;
    }
}
