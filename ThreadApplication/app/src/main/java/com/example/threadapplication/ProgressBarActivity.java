package com.example.threadapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProgressBarActivity extends AppCompatActivity {
    private TextView tv;
    private ProgressBar bar;
    private Button btStart;
    private Handler handler;
    private ProgressThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_progress_bar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv = findViewById(R.id.tv);
        bar = findViewById(R.id.progressBar);
        btStart = findViewById(R.id.btStart);
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                bar.setProgress(msg.arg1);
                tv.setText(msg.arg1 + "%");
            }
        };
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thread != null) {
                    thread.interrupt();
                }
                bar.setProgress(0);
                thread = new ProgressThread();
                thread.start();
            }
        });
    }

    class ProgressThread extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Log.d("Error: ", e.getMessage());
                }
                Message msg = handler.obtainMessage();
                msg.arg1 = i;
                handler.sendMessage(msg);
            }

        }
    }
}