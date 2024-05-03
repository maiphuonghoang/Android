package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

public class DynamicFragmentActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btA, btB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dynamic_fragment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btA = findViewById(R.id.btA);
        btB = findViewById(R.id.btB);
        btA.setOnClickListener(this);
        btB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fg;
        switch(v.getId()) {
            case R.id.btA:
                fg = new FragmentA();
                //add vào frame
                transaction.add(R.id.frame, fg);
                break;
            case R.id.btB:
                fg = new FragmentB();
                //add vào frame
                transaction.add(R.id.frame, fg);
                break;
        }
        transaction.commit();
    }
}