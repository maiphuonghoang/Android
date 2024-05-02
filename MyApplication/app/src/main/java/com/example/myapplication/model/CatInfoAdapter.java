package com.example.myapplication.model;

import android.app.AlertDialog;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

public class CatInfoAdapter extends RecyclerView.Adapter<CatInfoAdapter.CatInfoViewHolder>{
    private Context context;
    private List<Cat> list;
    private List<Cat> listBackup;
    private CatItemListener catItem;
    public CatInfoAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
        listBackup = new ArrayList<>();
    }
    public List<Cat> getBackup(){
        return listBackup;
    }
    public void setClickListener(CatItemListener catItem){
        this.catItem = catItem;
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
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete alert");
                builder.setMessage("Are you sure you want to delete " + c.getName() + "?");
                builder.setIcon(R.drawable.baseline_delete_outline_24);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listBackup.remove(position);
                        list.remove(position);
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
        return list != null? list.size():0;
    }

    public class CatInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(catItem != null){//nếu xh interface
                catItem.onItemClick(v, getAdapterPosition());
            }
        }
    }
    public void filterList(List<Cat> filterList){
        list = filterList;
        notifyDataSetChanged();
    }
    public void add(Cat cat) {
        listBackup.add(cat);
        list.add(cat);
        notifyDataSetChanged();//làm mới lại recyclerview
    }
    public void update(int position, Cat cat) {
        listBackup.set(position, cat);
        list.set(position, cat);
        notifyDataSetChanged();
    }
    public Cat getItem(int position) {
        return list.get(position);
    }
    public interface CatItemListener {
        public void onItemClick(View view, int position);
    }
}
