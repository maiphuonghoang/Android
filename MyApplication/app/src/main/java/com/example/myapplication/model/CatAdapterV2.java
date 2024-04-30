package com.example.myapplication.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class CatAdapterV2 extends RecyclerView.Adapter<CatAdapterV2.CatViewHolder>{
    private List<Cat> list;
    private CatItemListener catItemListener;

    public CatAdapterV2(List<Cat> list) {
        this.list = list;
    }

    public void setCatItemListener(CatItemListener catItemListener) {
        this.catItemListener = catItemListener;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //phương thức trả về view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cat, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat c = list.get(position);
        if(c == null) {
            return;
        }
        holder.img.setImageResource(c.getImg());
        holder.tv.setText(c.getName());
    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //gán đối tượng vào item_view
        private ImageView img;
        private TextView tv;

        public CatViewHolder(@NonNull View view){
            super(view);
            img = view.findViewById(R.id.img);
            tv = view.findViewById(R.id.tname);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //khi có sự kiện click vào 1 card
            if(catItemListener != null){
                catItemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface CatItemListener {
        public void onItemClick(View view, int position);
    }
}
