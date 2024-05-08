package com.example.secondapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.secondapplication.model.Student;

import java.util.Arrays;
import java.util.List;

public class ExplicitIntent2Activity extends AppCompatActivity {
    private Button bt;
    private TextView tvName, tvSub, tvST, tvList;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_explicit_intent2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bt = findViewById(R.id.bt);
        tvName = findViewById(R.id.tvName);
        tvSub = findViewById(R.id.tvSub);
        tvST = findViewById(R.id.tvST);
        img = findViewById(R.id.img);
        tvList = findViewById(R.id.tvList);
        Intent t = getIntent();
        String name = t.getStringExtra("name");
        int age = t.getIntExtra("age", 20);
        tvName.setText(name + ", " + age);
        String sub[] = t.getStringArrayExtra("subject");
        tvSub.setText(Arrays.toString(sub));
        Student s = (Student) t.getSerializableExtra("student");
        img.setImageResource(s.getImg());
        tvST.setText("Name: " + s.getName() + ", age:" + s.getAge());
        List<Student> list = (List<Student>) t.getSerializableExtra("data");
        String tt = "";
        for (Student i : list) {
            tt += i.getName() + " " + i.getAge() + "; ";
        }
        tvList.setText(tt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}