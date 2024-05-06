package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ViewPagerTabLayoutCRUDActivity;
import com.example.myapplication.adapter.CatAdapter;
import com.example.myapplication.adapter.SpinnerAdapter;
import com.example.myapplication.model.Cat;

public class FragmentSave extends Fragment implements CatAdapter.CatItemListener {
    private CatAdapter adapter;
    private Spinner spinner;
    private EditText editName, editDesc, editPrice;
    private Button btAdd, btUpdate;
    private RecyclerView recyclerView;
    private int currentPos;
    private int[] imgs = {R.drawable.cat, R.drawable.baseline_delete_outline_24, R.drawable.baseline_bungalow_24,
            R.drawable.baseline_calendar_today_24, R.drawable.baseline_desktop_windows_24, R.drawable.baseline_android_24};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_save, container, false);
    }

    //thao tác với fragment
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //gán các đối tượng với references của nó
        initView(view);
        adapter = new CatAdapter((ViewPagerTabLayoutCRUDActivity) getActivity());
        //layout cho recycler view
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        //mỗi adapter bắt sự kiện lấy ra phần tử ở FragmentAdd
        adapter.setItemListener(this);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i = spinner.getSelectedItem().toString();
                int img;
                try {
                    img = imgs[Integer.parseInt(i)];
                    double price = Double.parseDouble(editPrice.getText().toString());
                    Cat cat = new Cat(img, editName.getText().toString(), editDesc.getText().toString(), price);
                    adapter.add(cat);
                } catch (Exception e) {

                }
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i = spinner.getSelectedItem().toString();
                int img;
                try {
                    img = imgs[Integer.parseInt(i)];
                    double price = Double.parseDouble(editPrice.getText().toString());
                    Cat cat = new Cat(img, editName.getText().toString(), editDesc.getText().toString(), price);
                    adapter.update(currentPos, cat);
                    //sau khi update thành công
                    btUpdate.setVisibility(View.INVISIBLE);
                    btAdd.setVisibility(View.VISIBLE);
                } catch (Exception e) {

                }
            }
        });


    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(getContext(), imgs);
        spinner.setAdapter(adapter);
        editName = view.findViewById(R.id.editName);
        editDesc = view.findViewById(R.id.editDesc);
        editPrice = view.findViewById(R.id.editPrice);
        btAdd = view.findViewById(R.id.btAdd);
        btUpdate = view.findViewById(R.id.btUpdate);
        //lúc đầu chưa có nút Update
        btUpdate.setVisibility(View.INVISIBLE);
        recyclerView = view.findViewById(R.id.reView);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(view.getContext(), "Click", Toast.LENGTH_SHORT).show();

        //bắt sự kiện click vào item thì lấy được cat và đẩy vào phần edit
        btAdd.setVisibility(View.INVISIBLE);
        btUpdate.setVisibility(View.VISIBLE);
        currentPos = position;
        Cat cat = adapter.getItem(position);
        int img = cat.getImg();
        int p = 0;
        for (int i = 0; i < imgs.length; i++) {
            if (img == imgs[i]) {
                p = i;//lấy vị trí ảnh để set vào spinner
                break;
            }
        }
        spinner.setSelection(p);//đặt vào vị trí thứ p
        editName.setText(cat.getName());
        editDesc.setText(cat.getDesc());
        editPrice.setText(cat.getPrice() + "");
    }
}
