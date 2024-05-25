package com.example.notificationapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class ChannelNotiActivity extends AppCompatActivity {
    //    Android >= 8
    private Button bt1, bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_channel_noti);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(ChannelNotiActivity.this, MyNotification.CHANNEL_ID1)
                        .setContentTitle("Thong bao 1")
                        .setContentText("Noi dung thong bao Channel 1")
                        .setSmallIcon(R.drawable.baseline_back_hand_24)
                        .setColor(Color.RED)
                        .setDefaults(NotificationCompat.DEFAULT_SOUND)
                        .setCategory(NotificationCompat.CATEGORY_ALARM);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
                managerCompat.notify(getNotificationId(), builder.build());
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(ChannelNotiActivity.this, MyNotification.CHANNEL_ID2)
                        .setContentTitle("Thong bao 2")
                        .setContentText("Noi dung thong bao Channel 2")
                        .setSmallIcon(R.drawable.baseline_notifications_active_24)
                        .setColor(Color.BLUE)
                        .setDefaults(NotificationCompat.DEFAULT_SOUND)
                        .setCategory(NotificationCompat.CATEGORY_ALARM);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
                managerCompat.notify(getNotificationId(), builder.build());
            }
        });
    }

    private int getNotificationId() {
        int time = (int) new Date().getTime();
        return time;
    }
}