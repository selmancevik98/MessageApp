package com.hebun.messageapp.messages;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hebun.messageapp.R;

import java.util.List;

public class Adapter_Messages extends RecyclerView.Adapter<Adapter_Messages.ViewHolderMSG> {
    List<MessageModel> list_message;
    String username;
    Context context;
    Activity activity;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public Adapter_Messages(List<MessageModel> list_message, String username, Context context, Activity activity) {
        this.list_message = list_message;
        this.username = username;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolderMSG onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_adapter_message, parent, false);
        return new ViewHolderMSG(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMSG holder, int position) {
        if (list_message.get(position).getFrom().matches(user.getUid())) {
            holder.right_time_text_view.setText(list_message.get(position).getTime());
            holder.right_chat_text_view.setText(list_message.get(position).getText());
        } else {
            holder.left_time_text_view.setText(list_message.get(position).getTime());
            holder.left_username_text_view.setText("");
            holder.left_chat_text_view.setText(list_message.get(position).getText());
        }
    }

    @Override
    public int getItemCount() {
        return list_message.size();
    }

    public static class ViewHolderMSG extends RecyclerView.ViewHolder {
        TextView left_username_text_view, left_chat_text_view, left_time_text_view, right_chat_text_view, right_time_text_view;
        public ViewHolderMSG(@NonNull View itemView) {
            super(itemView);
            left_chat_text_view = itemView.findViewById(R.id.left_chat_text_view);
            left_username_text_view = itemView.findViewById(R.id.left_username_text_view);
            left_time_text_view = itemView.findViewById(R.id.left_time_text_view);
            right_chat_text_view = itemView.findViewById(R.id.right_chat_text_view);
            right_time_text_view = itemView.findViewById(R.id.right_time_text_view);
        }
    }
}
