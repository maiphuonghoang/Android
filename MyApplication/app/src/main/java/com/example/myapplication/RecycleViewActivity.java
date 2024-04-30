package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Cat;
import com.example.myapplication.model.CatAdapter;
import com.example.myapplication.model.CatAdapterV2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity implements CatAdapterV2.CatItemListener{
    private RecyclerView recyclerView;
    private CatAdapter adapter;
    private CatAdapterV2 adapterV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycle_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.rview);
        //C1
        adapter = new CatAdapter(this, getCatList());
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
//        recyclerView.setAdapter(adapter);

        //C2
        adapterV2 = new CatAdapterV2(getCatList());
        recyclerView.setAdapter(adapterV2);
        adapterV2.setCatItemListener(this);

    }
    private List<Cat> getCatList() {
        List<Cat> list = new ArrayList<>();
        list.add(new Cat(R.drawable.cat, "Cat 1"));
        list.add(new Cat(R.drawable.cat, "Cat 2"));
        list.add(new Cat(R.drawable.cat, "Cat 3"));
        list.add(new Cat(R.drawable.cat, "Cat 4"));
        list.add(new Cat(R.drawable.cat, "Cat 5"));
        list.add(new Cat(R.drawable.cat, "Cat 6"));
        return list;
    }

    @Override
    public void onItemClick(View view, int position) {
        Cat c = getCatList().get(position);
        Toast.makeText(this, c.getName(), Toast.LENGTH_SHORT).show();
    }
}