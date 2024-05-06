package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.fragment.FragmentSave;
import com.example.myapplication.fragment.FragmentSearch;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private int pageNumber = 2;

    public FragmentAdapter(@NonNull FragmentManager fm, int pageNumber) {
        super(fm, pageNumber);
        this.pageNumber = pageNumber;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentSave();
            case 1:
                return new FragmentSearch();
            default:
                return new FragmentSave();
        }
    }

    @Override
    public int getCount() {
        return pageNumber;
    }
}
