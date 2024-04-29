package com.example.myapplication.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class TechnologyAdapter extends ArrayAdapter<Technology> {
    public Context context;
    private Technology[] list;
    public TechnologyAdapter(@NonNull Context context,  Technology[] list) {
        super(context, R.layout.item_tech, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //vị trí của thành phần của listview
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_tech, null, true);
        ImageView img = view.findViewById(R.id.img);
        TextView tname = view.findViewById(R.id.tname);
        TextView tsub = view.findViewById(R.id.tsub);
        TextView tdescribe = view.findViewById(R.id.tdescribe);
        //gán dữ liệu
        Technology t = list[position];
        img.setImageResource(t.getImg());
        tname.setText(t.getName());
        tsub.setText(t.getSub());
        tdescribe.setText(t.getDescribe());
        return view;
    }
    public Technology getItem(int position) {
        return list[position];
    }
}
