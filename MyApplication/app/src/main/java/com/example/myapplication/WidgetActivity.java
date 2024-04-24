package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WidgetActivity extends AppCompatActivity {
    private Spinner sp2, sp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.example_widgets);
        sp2 = findViewById(R.id.sp2);
        String[] list2 = {"PTIT", "NEU", "HUST", "FPT"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.item, list2);
//                hiện chồng lên layout này với layout ntn và cho cái gì vào đấy
        sp2.setAdapter(adapter2);

        sp3 = findViewById(R.id.sp3);
        String[] list1 = getResources().getStringArray(R.array.country);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, R.layout.item, list1);
        sp3.setAdapter(adapter3);

    }
}