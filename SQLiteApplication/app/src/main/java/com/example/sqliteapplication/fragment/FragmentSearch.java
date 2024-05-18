package com.example.sqliteapplication.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqliteapplication.R;
import com.example.sqliteapplication.adapter.RecyclerViewAdapter;
import com.example.sqliteapplication.dal.SQLiteHelper;
import com.example.sqliteapplication.model.Item;

import java.util.Calendar;
import java.util.List;

public class FragmentSearch extends Fragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private TextView tvTong;
    private EditText eFrom, eTo;
    private Button btSearch;
    private SearchView searchView;
    private Spinner spCategory;
    private RecyclerViewAdapter adapter;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycleView);
        eFrom = view.findViewById(R.id.eFrom);
        eTo = view.findViewById(R.id.eTo);
        spCategory = view.findViewById(R.id.spCategory);
        searchView = view.findViewById(R.id.search);
        tvTong = view.findViewById(R.id.tvTong);
        btSearch = view.findViewById(R.id.btSearch);
        String[] arr = getResources().getStringArray(R.array.category);
        String[] arrAll = new String[arr.length+1];
        arrAll[0] = "All";
        for (int i=0; i<arr.length; i++){
            arrAll[i+1] = arr[i];
        }
        spCategory.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.item_spinner, arrAll));
        adapter = new RecyclerViewAdapter();
        db=new SQLiteHelper(getContext(), "items", null, 1);
        List<Item> list = db.getAll();
        adapter.setList(list);
        tvTong.setText("Tong tien: " + tong(list) + " K");
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchText) {
                List<Item> list= db.searchByTitle(searchText);
                tvTong.setText("Tong tien: " + tong(list) + " K");
                adapter.setList(list);
                return true;
            }
        });
        eFrom.setOnClickListener(this);
        eTo.setOnClickListener(this);
        btSearch.setOnClickListener(this);
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cate = spCategory.getItemAtPosition(position).toString();
                List<Item> list;
                if(!cate.equalsIgnoreCase("all")){
                    list= db.searchByCategory(cate);
                }
                else{
                    list = db.getAll();
                }
                adapter.setList(list);
                tvTong.setText("Tong tien: " + tong(list) + " K");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private int tong(List<Item> list){
        int t = 0;
        for(Item item : list){
            t += Integer.parseInt(item.getPrice());
        }
        return t;
    }

    @Override
    public void onClick(View view) {
        if(view == eFrom || view == eTo) {
            showDatePickerDialog(view);
        }
        if(view == btSearch){
            String from = eFrom.getText().toString();
            String to = eTo.getText().toString();
            if(!from.isEmpty() && !to.isEmpty()){
                List<Item> list = db.searchByDateFromTo(from, to);
                adapter.setList(list);
                tvTong.setText("Tong tien: " + tong(list) + " K");
            }
        }
    }
    private void showDatePickerDialog(View targetView) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String date = String.format("%02d/%02d/%d", day, month + 1, year);
                if (targetView == eFrom) {
                    eFrom.setText(date);
                } else if (targetView == eTo) {
                    eTo.setText(date);
                }
            }
        }, year, month, day);
        dialog.show();
    }
}
