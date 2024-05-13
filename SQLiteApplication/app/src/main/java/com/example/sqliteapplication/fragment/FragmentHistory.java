package com.example.sqliteapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqliteapplication.R;
import com.example.sqliteapplication.UpdateDeleteActivity;
import com.example.sqliteapplication.adapter.RecyclerViewAdapter;
import com.example.sqliteapplication.dal.SQLiteHelper;
import com.example.sqliteapplication.model.Item;

import java.util.List;

public class FragmentHistory extends Fragment implements RecyclerViewAdapter.ItemListener {
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycleView);
        adapter = new RecyclerViewAdapter();
        db = new SQLiteHelper(getContext(), "items", null, 1);
        db.addItem(new Item(1, "Mua quan bo", "Mua sam", "10", "20/3/2024"));
        List<Item> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onItemClick(View v, int position) {
        Item item = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        //từ activity 1 truyền sang activity2
        intent.putExtra("item", item);
        startActivity(intent);
    }

    //mỗi lần thêm thì phải reload
    @Override
    public void onResume() {
        super.onResume();
        List<Item> list = db.getAll();
        adapter.setList(list);
    }
}
