package com.hebun.messageapp.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_Chats extends RecyclerView.Adapter<Adapter_Chats.ViewHolderC> {


    @NonNull
    @Override
    public ViewHolderC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderC holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolderC extends RecyclerView.ViewHolder {
        public ViewHolderC(@NonNull View itemView) {
            super(itemView);
        }
    }
}
