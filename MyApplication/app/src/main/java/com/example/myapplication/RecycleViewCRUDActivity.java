package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.CatInfoAdapter;
import com.example.myapplication.model.SpinnerAdapter;

public class RecycleViewCRUDActivity extends AppCompatActivity {
    private int[] imgs = {R.drawable.cat, R.drawable.img, R.drawable.baseline_bungalow_24,
            R.drawable.baseline_calendar_today_24, R.drawable.baseline_desktop_windows_24, R.drawable.ic_launcher_foreground};
    private Spinner sp;
    private RecyclerView recyclerView;
    private CatInfoAdapter adapter;
    private EditText eName, eDesc, ePrice;
    private Button btAdd, btUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycle_view_crudactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();    }

    private void initView() {
        sp = findViewById(R.id.img);
        SpinnerAdapter adapter = new SpinnerAdapter(this);
        sp.setAdapter(adapter);
        recyclerView = findViewById(R.id.recyclerview);
        eName = findViewById(R.id.name);
        eDesc = findViewById(R.id.desc);
        ePrice = findViewById(R.id.price);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        btUpdate.setEnabled(false);
    }
}