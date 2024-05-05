package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.model.FragmentAdapter;
import com.example.myapplication.model.HorizontalFlipTransformation;
import com.google.android.material.tabs.TabLayout;

public class ViewPagerTabLayoutActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button btPre, btNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_pager_tab_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabLayout);
        btPre = findViewById(R.id.btPre);
        btNext = findViewById(R.id.btNext);
        btPre.setOnClickListener(this);
        btNext.setOnClickListener(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentAdapter adapter = new FragmentAdapter(manager, 3);
        viewPager.setPageTransformer(true, new HorizontalFlipTransformation());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setTabLayoutTitleColor();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorPink));
                        btPre.setBackgroundColor(getResources().getColor(R.color.colorPink));
                        btNext.setBackgroundColor(getResources().getColor(R.color.colorPink));
                        break;
                    case 1:
                        tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorGreen));
                        btPre.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        btNext.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        break;
                    case 2:
                        tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorBlue));
                        btPre.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                        btNext.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btNext:
                if (viewPager.getCurrentItem() == 2) {
                    return;
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    setTabLayoutTitleColor();
                }
                break;
            case R.id.btPre:
                if (viewPager.getCurrentItem() == 0) {
                    return;
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                    setTabLayoutTitleColor();
                }
        }
    }

    private void setTabLayoutTitleColor() {
        switch (viewPager.getCurrentItem()) {
            case 0:
                tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorPink));
                break;
            case 1:
                tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorGreen));
                break;
            case 2:
                tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorBlue));
        }
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
}