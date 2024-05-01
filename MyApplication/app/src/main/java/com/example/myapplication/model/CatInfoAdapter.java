package com.example.myapplication.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CatInfoAdapter extends RecyclerView.Adapter<CatInfoAdapter.CatInfoViewHolder>{
    private Context context;
    private List<Cat> list;

    public CatInfoAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public CatInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false);
        return new CatInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatInfoViewHolder holder, int position) {
        Cat c = list.get(position);
        if (c == null) {
            return;
        }
        holder.img.setImageResource(c.getImg());
        holder.tvName.setText(c.getName());
        holder.tvDesc.setText(c.getDesc());
        holder.tvPrice.setText(c.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list != null? list.size():0;
    }

    public class CatInfoViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tvName, tvDesc, tvPrice;
        private Button btRemove;
        public CatInfoViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.img);
            tvName = view.findViewById(R.id.txtName);
            tvDesc = view.findViewById(R.id.txtDesc);
            tvPrice = view.findViewById(R.id.txtPrice);
            btRemove = view.findViewById(R.id.btDelete);
        }
    }
}
