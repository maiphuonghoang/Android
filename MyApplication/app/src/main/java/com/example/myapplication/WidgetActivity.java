package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class WidgetActivity extends AppCompatActivity {
    private Spinner sp2, sp3, sp1;
    private CheckBox ck1, ck2, ck3;
    private RadioButton g1, g2;
    private RatingBar rt;
    private TextView kq;
    private Button btSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.example_widgets);
        initViews();
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss = "Checkbox: ";
                if(ck1.isChecked()) ss += ck1.getText() + ",";
                if(ck2.isChecked()) ss += ck2.getText() + ",";
                if(ck3.isChecked()) ss += ck3.getText() + ",";
                if(ss.endsWith(",")) ss = ss.substring(0, ss.length() - 1);
                ss += "\nSex: ";
                if(g1.isChecked()) ss += g1.getText();
                if(g2.isChecked()) ss += g2.getText();
                ss += "\nRating: " + rt.getRating();
                ss += "\nCountry: " + sp1.getSelectedItem().toString();
                ss += "\nUniversity: " + sp2.getSelectedItem().toString();
                ss += "\nCountry: " + sp3.getSelectedItem().toString();
                kq.setText(ss);

            }
        });
    }

    private void initViews() {
        ck1 = findViewById(R.id.ck1);
        ck2 = findViewById(R.id.ck2);
        ck3 = findViewById(R.id.ck3);
        rt = findViewById(R.id.rating);
        g1 = findViewById(R.id.rbmale);
        g2 = findViewById(R.id.rbfemale);
        btSave = findViewById(R.id.btSave);
        kq = findViewById(R.id.kq);
        sp1 = findViewById(R.id.sp1);
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