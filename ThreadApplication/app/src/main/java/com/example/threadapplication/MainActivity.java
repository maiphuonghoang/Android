package com.example.threadapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button btCount;
    private static final int MESSAGE_COUNT_DOWN = 100;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv = findViewById(R.id.tv);
        btCount = findViewById(R.id.btCount);
        btCount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new CountDown().start();
            }
        });
        //cập nhật số down vào Main Thread thông qua tv thì dùng handler để gửi msg
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                //ktra còn đến hay hết đếm'
                switch (msg.what) {
                    case MESSAGE_COUNT_DOWN:
                        tv.setText(msg.arg1 + "");
                        break;
                    case 234:
                        Toast.makeText(MainActivity.this, "Finish", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, HPNYActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };
    }

    class CountDown extends Thread {
        @Override
        public void run() {
            int count = 10;
            while (count > 0) {
                count--;
                //cập nhật giá trị count vào tv
                Message msg = new Message();
                msg.what = MESSAGE_COUNT_DOWN;
                msg.arg1 = count;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.d("Error: ", e.getMessage());
                }
            }
            //kết thúc việc đếm
            handler.sendEmptyMessage(234);
        }
    }
}