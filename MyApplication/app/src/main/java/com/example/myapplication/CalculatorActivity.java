package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculatorActivity extends AppCompatActivity {
    private TextView kq;
    private EditText edit1, edit2;
    private Button btAdd;
    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        btAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nn1 = edit1.getText().toString();
                String nn2 = edit2.getText().toString();
                double n1, n2;
                try {
                    n1 = Double.parseDouble(nn1);
                    n2 = Double.parseDouble(nn2);
                    String result = calculate(n1, n2, "+");
                    kq.setText(result);
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Nhap 2 so", Toast.LENGTH_LONG).show();
                }
            }
        });
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nn1 = edit1.getText().toString();
                String nn2 = edit2.getText().toString();
                double n1, n2;
                try {
                    n1 = Double.parseDouble(nn1);
                    n2 = Double.parseDouble(nn2);
                    String p = sp.getSelectedItem().toString();
                    String result = calculate(n1, n2, p);
                    kq.setText(result);
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Nhap 2 so", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView() {
        kq = findViewById(R.id.kq);
        edit1 = findViewById(R.id.e1);
        edit2 = findViewById(R.id.e2);
        btAdd = findViewById(R.id.btAdd);
        sp = findViewById(R.id.sp);
    }

    public void add(View v) {
        String nn1 = edit1.getText().toString();
        String nn2 = edit2.getText().toString();
        double n1, n2;
        try {
            n1 = Double.parseDouble(nn1);
            n2 = Double.parseDouble(nn2);
            String result = calculate(n1, n2, "+");
            kq.setText(result);
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }catch (Exception e) {

        }
    }

    private String calculate(double x, double y, String p) {
        String s = "";
        switch (p) {
            case "+":
                s = "Tong " + (x + y);
                break;
            case "-":
                s = "Hieu " + (x - y);
                break;
            case "*":
                s = "Tich " + (x * y);
                break;
            case "/":
                if (y == 0)
                    s = "Khong chia cho 0";
                else s = "Thuong " + (x / y);
                break;
            //Ctrl Alt
        }
        return s;
    }
}