package com.example.secondapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondapplication.R;
import com.example.secondapplication.adapter.MessageAdapter;
import com.example.secondapplication.model.Message;

import java.util.ArrayList;
import java.util.List;

public class FragmentNotification extends Fragment {
    MessageAdapter messageAdapter;
    RecyclerView recyclerView;
    List<Message> list;

    public FragmentNotification() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_notification, container, false);
        list = new ArrayList<>();
        list.add(new Message(R.drawable.blackcat, "Thanh", "Em an com chua", "16:20"));
        list.add(new Message(R.drawable.cat, "Binh", "Em an com chua", "14:20"));
        list.add(new Message(R.drawable.blackcat, "Thanh", "Em an com chua", "12:20"));
        list.add(new Message(R.drawable.blackcat, "Vinh", "Em an com chua", "10:20"));
        list.add(new Message(R.drawable.blackcat, "Thanh", "Em an com chua", "8:20"));
        list.add(new Message(R.drawable.blackcat, "Vinh", "Em an com chua", "5:20"));
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        messageAdapter = new MessageAdapter(view.getContext(), list);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(messageAdapter);
        return view;
    }
}
