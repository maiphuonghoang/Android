package com.example.threadapplication;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ClipImageActivity extends AppCompatActivity {
    private ImageView img;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clip_image);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        img = findViewById(R.id.img);
        bt = findViewById(R.id.btStart);
        ClipDrawable clip = (ClipDrawable) img.getDrawable();
        img.setImageLevel(0);
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        int currentLevel = clip.getLevel();
                        if (currentLevel > 10000) {
                            currentLevel = 0;
                        }
                        img.setImageLevel(currentLevel + 1000);
                        handler.postDelayed(this, 400);
                    }
                }, 400);
            }
        });
    }
}