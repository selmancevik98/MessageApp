package com.hebun.messageapp.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hebun.messageapp.R;
import com.hebun.messageapp.messages.MessagePage;
import com.hebun.messageapp.models.Model_Chats;

import java.util.List;

public class Adapter_Chats extends RecyclerView.Adapter<Adapter_Chats.ViewHolderC> {
    List<Model_Chats> list_chats;
    Context context;
    Activity activity;

    public Adapter_Chats(List<Model_Chats> list_chats, Context context, Activity activity) {
        this.list_chats = list_chats;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolderC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_adapter_chats, parent, false);
        return new ViewHolderC(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderC holder, @SuppressLint("RecyclerView") int position) {
        holder.chats_name_surname_textview.setText(list_chats.get(position).getName() + " " + list_chats.get(position).getSurname());
        holder.chats_username_textview.setText(list_chats.get(position).getUsername());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MessagePage.class);
                intent.putExtra("room_id", list_chats.get(position).getSohbet_id());
                intent.putExtra("user_name", list_chats.get(position).getUsername());
                intent.putExtra("user_id", list_chats.get(position).getUser_id());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_chats.size();
    }

    public static class ViewHolderC extends RecyclerView.ViewHolder {
        TextView chats_name_surname_textview, chats_username_textview;
        public ViewHolderC(@NonNull View itemView) {
            super(itemView);
            chats_username_textview = itemView.findViewById(R.id.chats_username_textview);
            chats_name_surname_textview = itemView.findViewById(R.id.chats_name_surname_textview);
        }
    }
}
