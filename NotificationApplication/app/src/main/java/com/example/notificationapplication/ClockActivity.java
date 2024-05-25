package com.example.notificationapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class ClockActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private Button btDat, btDung;
    private TextView tv;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clock);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        timePicker = findViewById(R.id.timePicker);
        btDat = findViewById(R.id.btDat);
        btDung = findViewById(R.id.btDung);
        tv = findViewById(R.id.tv);
        btDung.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tv.setText("Dung dat gio");
                pendingIntent.cancel();
            }
        });
        btDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                int gio = timePicker.getCurrentHour();
                int phut = timePicker.getCurrentMinute();
                calendar.set(Calendar.HOUR_OF_DAY, gio);
                calendar.set(Calendar.MINUTE, phut);
                String sgio = gio > 12 ? String.valueOf(gio - 12) : String.valueOf(gio);
                String sphut = phut < 10 ? "0" + phut : String.valueOf(phut);
                tv.setText("Thoi gian hen gio " + sgio + ":" + sphut);
                Intent intent = new Intent(ClockActivity.this, AlarmReceiver.class);
                intent.setAction("MyAction");
                intent.putExtra("time", sgio + ":" + sphut);
                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                pendingIntent = PendingIntent.getBroadcast(ClockActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            }
        });
    }
}
