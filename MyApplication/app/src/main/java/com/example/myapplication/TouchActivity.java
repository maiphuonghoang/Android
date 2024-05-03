package com.example.myapplication;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TouchActivity extends AppCompatActivity {
    private EditText e1, e2, e3, e4;
    private ImageView img;
    private float xDown=0, yDown=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_touch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        xDown = event.getX();
                        yDown = event.getY();
                        e1.setText(xDown+"");
                        e2.setText(yDown+"");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveX = event.getX();
                        float moveY = event.getY();
                        e3.setText(moveX+"");
                        e4.setText(moveY+"");
                        float deltaX = moveX - xDown;
                        float deltaY = moveY - yDown;
                        img.setX(img.getX() + deltaX);
                        img.setY(img.getY() + deltaY);
                        break;
                }
                return true;
            }
        });
    }
    private void initView(){
        e1 = findViewById(R.id.txt1);
        e2 = findViewById(R.id.txt2);
        e3 = findViewById(R.id.txt3);
        e4 = findViewById(R.id.txt4);
        img = findViewById(R.id.img);
    }
}