package com.example.notificationapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //Android < 8
    private Button bt;
    private int notificationId = 1;
    private static final String CHANNEL_ID = "notification_channel";
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
        bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bt = findViewById(R.id.bt);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Notification builder = new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                                .setContentTitle("Thong bao")
                                .setContentText("Noi dung thong bao")
                                .setSmallIcon(R.drawable.baseline_notifications_active_24)
                                .setColor(Color.RED)
                                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(getNotificationId(), builder);
                    }
                });
            }
        });
    }
    private int getNotificationId(){
        int num = (int) new Date().getTime();
        return num;
    }

}