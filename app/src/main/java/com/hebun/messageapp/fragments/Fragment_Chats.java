package com.hebun.messageapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hebun.messageapp.R;
import com.hebun.messageapp.adapters.Adapter_Chats;
import com.hebun.messageapp.models.Model_Chats;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Chats extends Fragment {
    RecyclerView rv_chats;
    Adapter_Chats adapterChats;
    List<Model_Chats> list_chats = new ArrayList<>();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_chat = inflater.inflate(R.layout.activity_fragment_chats, container, false);

        rv_chats = view_chat.findViewById(R.id.rv_chats);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rv_chats.setLayoutManager(manager);
        adapterChats = new Adapter_Chats(list_chats, getContext(), getActivity());
        rv_chats.setAdapter(adapterChats);

        getData();
        return view_chat;
    }

    private void getData() {
        reference.child("users").child(user.getUid()).child("chatkeys").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String user_id = snapshot.getKey();
                String room_id = snapshot.getValue().toString();

                reference.child("users").child(user_id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Model_Chats modelChats = snapshot.getValue(Model_Chats.class);
                        modelChats.setSohbet_id(room_id);
                        modelChats.setUser_id(user_id);
                        list_chats.add(modelChats);
                        adapterChats.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
