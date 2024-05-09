package com.example.secondapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntentFilterActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intent_filter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        intent = new Intent();
    }
    public void email(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "maiphuonghoangmpk@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Welcome to Android class");
        startActivity(intent.createChooser(intent, "Choose Email"));
    }
    public void send(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        startActivity(intent);
    }
    //phần dưới yêu cầu physical device
    public void openWeb(View view){
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.google.com"));
        startActivity(intent);
    }
    public void sms(View view){
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:0345007258"));
        intent.putExtra("sms_body", "Do you want to join?");
        startActivity(intent);

    }
    public void call(View view){
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:0345007258"));
        startActivity(intent);
    }
}