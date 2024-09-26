package com.hebun.messageapp.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hebun.messageapp.MessagePage;
import com.hebun.messageapp.R;
import com.hebun.messageapp.models.Model_User_Search;

import java.util.List;

public class Adapter_User_Search extends RecyclerView.Adapter<Adapter_User_Search.ViewHolderUS> {

    List<Model_User_Search> searchList;
    Context context;
    Activity activity;

    public Adapter_User_Search(List<Model_User_Search> searchList, Context context, Activity activity) {
        this.searchList = searchList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolderUS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_adapter_user_search, parent, false);
        return new ViewHolderUS(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUS holder, @SuppressLint("RecyclerView") int position) {
        holder.user_name_text.setText(searchList.get(position).getUsername());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MessagePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public static class ViewHolderUS extends RecyclerView.ViewHolder {
        TextView user_name_text;

        public ViewHolderUS(@NonNull View itemView) {
            super(itemView);
            user_name_text = itemView.findViewById(R.id.user_name_text);
        }
    }
}
