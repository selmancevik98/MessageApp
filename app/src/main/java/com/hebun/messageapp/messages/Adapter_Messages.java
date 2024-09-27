package com.hebun.messageapp.messages;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_Messages extends RecyclerView.Adapter<Adapter_Messages.ViewHolderMSG> {

    @NonNull
    @Override
    public ViewHolderMSG onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMSG holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolderMSG extends RecyclerView.ViewHolder {
        public ViewHolderMSG(@NonNull View itemView) {
            super(itemView);
        }
    }
}
