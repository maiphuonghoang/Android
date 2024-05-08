package com.example.secondapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ImplicitIntentActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iWeb, iSms, iPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_implicit_intent);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
    }

    private void initView() {
        iWeb = findViewById(R.id.web);
        iSms = findViewById(R.id.sms);
        iPhone = findViewById(R.id.phone);
        iWeb.setOnClickListener(this);
        iSms.setOnClickListener(this);
        iPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.web:
                Intent w = new Intent(Intent.ACTION_VIEW);
                w.setData(Uri.parse("https://www.youtube.com/watch?v=YSEDXvvlnbc"));
                startActivity(w);
                break;
            case R.id.sms:
                Intent m = new Intent(Intent.ACTION_VIEW);
                m.setData(Uri.parse("sms:" + "0345007258"));
                m.putExtra("sms_body", "");
                startActivity(m);
                break;
            case R.id.phone:
                Intent p = new Intent(Intent.ACTION_DIAL);
                p.setData(Uri.parse("tel: 0345007258"));
                startActivity(p);
                break;
        }
    }
}