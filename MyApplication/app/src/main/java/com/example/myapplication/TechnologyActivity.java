package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.model.Technology;
import com.example.myapplication.model.TechnologyAdapter;

public class TechnologyActivity extends AppCompatActivity {
    private ListView listView;
    TechnologyAdapter adapter;
    private Technology[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_technology);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView = findViewById(R.id.lview);
        intitData();
        adapter = new TechnologyAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < listView.getAdapter().getCount(); i++) {
                    listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
                listView.getChildAt(position).setBackgroundColor(Color.YELLOW);
                Technology t = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), t.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void intitData() {
        Integer[] images = {R.drawable.baseline_bungalow_24, R.drawable.baseline_calendar_today_24,
                R.drawable.baseline_desktop_windows_24, R.drawable.baseline_android_24};
        String[] names = {"Java", "C++", "Python", "C#"};
        String[] subs = {"Java sub", "C++ sub", "Python sub", "C# sub"};
        String[] describes = {"Java description", "C++ description", "Python description", "C# description"};
        list = new Technology[images.length];
        for (int i = 0; i < list.length; i++) {
            list[i] = new Technology(images[i], names[i], subs[i], describes[i]);
        }
    }

}