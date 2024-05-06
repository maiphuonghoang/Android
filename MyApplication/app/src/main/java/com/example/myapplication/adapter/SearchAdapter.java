package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{
    private List<Cat> listSearch;

    public SearchAdapter() {
        listSearch = new ArrayList<>();
    }

    public void setListCat(List<Cat> listSearch) {
        this.listSearch = listSearch;
        notifyDataSetChanged();
    }
    public List<Cat> getListSearch() {
        return listSearch;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info_search, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Cat cat = listSearch.get(position);
        holder.img.setImageResource(cat.getImg());
        holder.name.setText(cat.getName());
        holder.desc.setText(cat.getDesc());
        holder.price.setText(cat.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return listSearch.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        TextView name, desc, price;
        public SearchViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.item_search_img);
            name = view.findViewById(R.id.item_search_name);
            desc = view.findViewById(R.id.item_search_desc);
            price = view.findViewById(R.id.item_search_price);
        }
    }
}
