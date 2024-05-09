package com.example.secondapplication;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FilterActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_filter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv = findViewById(R.id.tv);
        int bt = 5;//3 4 5 - Biến này chỉ dùng để hiển thị kết quả ấn nút nào khi chạy qua bộ lọc
        switch (bt) {
            case 3:
                Uri url = getIntent().getData();
                String s = "Scheme: " + url.getScheme() + "\nHost" + url.getHost();
                int k = 1;
                for (String i : url.getPathSegments()) {
                    s += "\n param" + (k++) + ": " + i;
                }
                s += "\naction: " + getIntent().getAction();

                break;
            case 4:
                String sms = "Content: " + getIntent().getStringExtra("sms_body".toString());
                sms += "\n data: " + getIntent().getDataString();
                tv.setText(sms);
                break;
            case 5:
                String num = getIntent().getDataString().substring(4);//tel:0345...
                tv.setText(num);
                break;
        }

    }
}