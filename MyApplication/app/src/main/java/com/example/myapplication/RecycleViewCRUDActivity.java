package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Cat;
import com.example.myapplication.model.CatInfoAdapter;
import com.example.myapplication.model.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewCRUDActivity extends AppCompatActivity
        implements CatInfoAdapter.CatItemListener, SearchView.OnQueryTextListener {
    private int[] imgs = {R.drawable.cat, R.drawable.img, R.drawable.baseline_bungalow_24,
            R.drawable.baseline_calendar_today_24, R.drawable.baseline_desktop_windows_24, R.drawable.ic_launcher_foreground};
    private Spinner sp;
    private RecyclerView recyclerView;
    private CatInfoAdapter adapter;
    private EditText eName, eDesc, ePrice;
    private Button btAdd, btUpdate;
    private int currentPos;
    private SearchView searchView;

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
        initView();
        adapter = new CatInfoAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);//hiện lên ở context này
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        searchView.setOnQueryTextListener(this);
        btAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Cat cat = new Cat();
                String i = sp.getSelectedItem().toString();
                String name = eName.getText().toString();
                String desc = eDesc.getText().toString();
                String p = ePrice.getText().toString();
                int img = R.drawable.cat;
                double price = 0;
                try{
                    img = imgs[Integer.parseInt(i)];
                    price = Double.parseDouble(p);
                    cat.setImg(img);
                    cat.setName(name);
                    cat.setDesc(desc);
                    cat.setPrice(price);
                    adapter.add(cat);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Input again", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Cat cat = new Cat();
                String i = sp.getSelectedItem().toString();
                String name = eName.getText().toString();
                String desc = eDesc.getText().toString();
                String p = ePrice.getText().toString();
                int img = R.drawable.cat;
                double price = 0;
                try{
                    img = imgs[Integer.parseInt(i)];
                    price = Double.parseDouble(p);
                    cat.setImg(img);
                    cat.setName(name);
                    cat.setDesc(desc);
                    cat.setPrice(price);
                    adapter.update(currentPos, cat);
                    btAdd.setEnabled(true);
                    btUpdate.setEnabled(false);
                }catch (Exception e){
                }
            }
        });
    }

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
        searchView = findViewById(R.id.search);
    }

    @Override
    public void onItemClick(View view, int position) {
        btAdd.setEnabled(false);
        btUpdate.setEnabled(true);
        currentPos = position;
        Cat cat = adapter.getItem(position);
        int img = cat.getImg();
        int p = 0; //vị trí của ảnh
        for (int i = 0; i < imgs.length; i++) {
            if(img == imgs[i]){
                p = i;
                break;
            }
        }
        sp.setSelection(p);
        eName.setText(cat.getName());
        eDesc.setText(cat.getDesc());
        ePrice.setText(cat.getPrice() + "");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String searchText) {
        filter(searchText);
        return false;
    }
    private void filter(String searchText) {
        List<Cat> filterList = new ArrayList<>();
        for(Cat i : adapter.getBackup()){
            if(i.getName().toLowerCase().contains(searchText.toLowerCase())){
                filterList.add(i);
            }
        }
        if(filterList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            adapter.filterList(filterList);
        }
    }

}