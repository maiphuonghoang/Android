package com.example.notificationapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {
    final String CHANNEL_ID = "201";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("MyAction")) {
            String time = intent.getStringExtra("time");
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel 1", NotificationManager.IMPORTANCE_HIGH);
                channel.setDescription("Description for channel 1");
                notificationManager.createNotificationChannel(channel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle("Bao thuc " + time)
                    .setContentText("Day di den gio " + time + " roi")
                    .setSmallIcon(R.drawable.baseline_notifications_active_24)
                    .setColor(Color.RED)
                    .setDefaults(NotificationCompat.DEFAULT_SOUND)
                    .setCategory(NotificationCompat.CATEGORY_ALARM);
            notificationManager.notify(getNotificationId(), builder.build());
        }
    }

    private int getNotificationId() {
        int time = (int) new Date().getTime();
        return time;
    }

}
