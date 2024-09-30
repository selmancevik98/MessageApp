package com.hebun.messageapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.hebun.messageapp.R;
import com.hebun.messageapp.adapters.Adapter_Chats;

public class Fragment_Chats extends Fragment {
    RecyclerView rv_chats;
    Adapter_Chats adapterChats;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_chat = inflater.inflate(R.layout.activity_fragment_chats, container, false);

        rv_chats = view_chat.findViewById(R.id.rv_chats);


        return view_chat;
    }
}
