package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ViewPagerTabLayoutCRUDActivity;
import com.example.myapplication.adapter.SearchAdapter;
import com.example.myapplication.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {
    private SearchAdapter adapter;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Cat> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    //chạy phần search và đưa vào recycler view
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.search);
        recyclerView = view.findViewById(R.id.reViewSearch);
        adapter = new SearchAdapter();
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
                filter(searchText);
                return false;
            }

            private void filter(String searchText) {
                List<Cat> filterList = new ArrayList<>();
                //mỗi lần gõ sinh ra 1 danh sách mới và được add vào adapter để thay đổi
                for (Cat c : list) {
                    if (c.getName().toLowerCase().contains(searchText.toLowerCase())) {
                        filterList.add(c);
                    }
                }
                if (filterList.isEmpty()) {
                    Toast.makeText(getContext(), "No results found", Toast.LENGTH_SHORT).show();
                } else {
                    adapter.setListCat(filterList);
                }
            }
        });
    }

    //mỗi lần thêm sửa xóa đều đẩy vào list của CatAdapter
    //ở trong main cần chứa 1 danh sách tạm thời để mỗi lần thay đổi ở cat thì search được cập nhật

    //chuyển từ fragment cat sang fragment seach thì phải làm tươi lại danh sách
    @Override
    public void onResume() {
        super.onResume();
        list = ((ViewPagerTabLayoutCRUDActivity) getActivity()).sharedList;
    }
}
