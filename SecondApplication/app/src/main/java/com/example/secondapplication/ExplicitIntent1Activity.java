package com.example.secondapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.secondapplication.model.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExplicitIntent1Activity extends AppCompatActivity {
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_explicit_intent1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent t = new Intent(ExplicitIntent1Activity.this, ExplicitIntent2Activity.class);
                t.putExtra("name", "To An An");
                t.putExtra("age", 17);
                String[] subject = {"Java", "Android", "C#"};
                t.putExtra("subject", subject);
                Student s = new Student(R.drawable.cat, "Vu Cat Tuong", 40);
                t.putExtra("student", s);
                List<Student> list = new ArrayList<Student>();
                list.add(s);
                list.add(new Student(R.drawable.blackcat, "Tran Van Tuan", 20));
                list.add(new Student(R.drawable.blackcat, "Bui Van Thao", 27));
                t.putExtra("data", (Serializable) list);
                startActivity(t);
            }
        });
    }
}