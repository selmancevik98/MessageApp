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
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hebun.messageapp.messages.MessagePage;
import com.hebun.messageapp.R;
import com.hebun.messageapp.models.Model_User_Search;

import java.util.List;

public class Adapter_User_Search extends RecyclerView.Adapter<Adapter_User_Search.ViewHolderUS> {

    List<Model_User_Search> searchList;
    Context context;
    Activity activity;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String room_id = "";

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
                String user_id = searchList.get(position).getUser_id();
                Intent intent = new Intent(context, MessagePage.class);

                // Firebase'de mevcut "chatkeys" için tek seferlik sorgu
                reference.child("users").child(user.getUid()).child("chatkeys").orderByKey().equalTo(user_id)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        room_id = snapshot.getValue(String.class);
                                    }
                                    page_change(intent, position, true);
                                } else {
                                    page_change(intent, position, false);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
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

    private void page_change(Intent intent, int position, boolean data_query) {
        if (data_query) {
            intent.putExtra("room_id", room_id);
        } else {
            intent.putExtra("room_id", "");
        }
        intent.putExtra("user_name", searchList.get(position).getUsername());
        intent.putExtra("user_id", searchList.get(position).getUser_id());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}

