package com.example.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.model.FragmentA;
import com.example.myapplication.model.FragmentB;
import com.example.myapplication.model.FragmentC;

public class BackStackFragementActivity extends AppCompatActivity {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_back_stack_fragement);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        manager = getSupportFragmentManager();
    }
    private void add(Fragment fg, String tag, String name){
        transaction = manager.beginTransaction();
        transaction.add(R.id.frame, fg, tag);
        transaction.addToBackStack(name);
        transaction.commit();
    }
    public void addA(View view){
        FragmentA fg = new FragmentA();
        add(fg, "fragA", "fa");
    }
    public void addB(View view){
        FragmentB fg = new FragmentB();
        add(fg, "fragB", "fb");
    }
    public void addC(View view){
        FragmentC fg = new FragmentC();
        add(fg, "fragC", "fc");
    }
    private void remove(Fragment fg, String tag){
        transaction = manager.beginTransaction();
        fg = manager.findFragmentByTag(tag);
        transaction.remove(fg);
        transaction.commit();
    }
    public void removeA(View view){
        FragmentA fg = new FragmentA();
        remove(fg, "fragA");
    }
    public void removeB(View view){
        FragmentB fg = new FragmentB();
        remove(fg, "fragB");
    }
    public void removeC(View view){
        FragmentC fg = new FragmentC();
        remove(fg, "fragC");
    }
    //gọi back
    public void back(View view){
        manager.popBackStack();
    }
    public void popA(View view){
        manager.popBackStack("fa", 0);
    }

    @Override
    public void onBackPressed() { //back stack của điện thoại
        if(manager.getBackStackEntryCount() > 0)
            manager.popBackStack();
        super.onBackPressed();
    }
}