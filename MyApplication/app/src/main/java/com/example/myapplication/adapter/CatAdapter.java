package com.example.myapplication.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ViewPagerTabLayoutCRUDActivity;
import com.example.myapplication.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder>{

    private List<Cat> list;
    private CatItemListener itemListener;

    //ý định gán vào main activity
    private ViewPagerTabLayoutCRUDActivity mainActivity;
    public CatAdapter(ViewPagerTabLayoutCRUDActivity mainActivity) {
        this.mainActivity = mainActivity;
        list = new ArrayList<Cat>();
    }

    public void setItemListener(CatItemListener itemListener) {
        this.itemListener = itemListener;
    }
    public Cat getItem(int position) {
        return list.get(position);
    }
    public List<Cat> getListCat() {
        return list;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info_save, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat c = list.get(position);
        holder.img.setImageResource(c.getImg());
        holder.name.setText(c.getName());
        holder.desc.setText(c.getDesc());
        holder.price.setText(c.getPrice()+"");
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Delete warning");
                builder.setMessage("Are you sure you want to delete " + c.getName() + "?");
                builder.setIcon(R.drawable.baseline_delete_outline_24);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        mainActivity.sharedList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        TextView name, desc, price;
        Button btRemove;
        public CatViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.item_img);
            name = view.findViewById(R.id.item_name);
            desc = view.findViewById(R.id.item_desc);
            price = view.findViewById(R.id.item_price);
            btRemove = view.findViewById(R.id.item_btRemove);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener != null) {
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
    //interface bắt sự kiện lấy item để sửa, cập nhật
    public interface CatItemListener {
        void onItemClick(View view, int position);
    }
    public void add(Cat cat) {
        list.add(cat);
        mainActivity.sharedList.add(cat);
        notifyDataSetChanged();
    }
    public void update(int position, Cat cat) {
        list.set(position, cat);
        mainActivity.sharedList.set(position, cat);
        notifyDataSetChanged();
    }


}
