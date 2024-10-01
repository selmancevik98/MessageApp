package com.hebun.messageapp.messages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hebun.messageapp.R;
import com.hebun.messageapp.models.DateTimeManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagePage extends AppCompatActivity {
    List<MessageModel> list_message = new ArrayList<>();
    String user_name, user_id, room_id;
    TextView msg_user_name;
    RecyclerView msg_rv_messages;
    EditText msg_messagetxt_edittext;
    ImageView msg_send_btn;
    Adapter_Messages adapterMessages;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    int msg_count;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_message_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        user_name = intent.getStringExtra("user_name");
        user_id = intent.getStringExtra("user_id");
        room_id = intent.getStringExtra("room_id");
        if (room_id.matches("")) {
            room_id = reference.child("messages").push().getKey().toString();
        }
        definitions();
        get_message();
    }


    private void definitions() {
        msg_user_name = findViewById(R.id.msg_user_name);
        msg_rv_messages = findViewById(R.id.msg_rv_messages);
        msg_messagetxt_edittext = findViewById(R.id.msg_messagetxt_edittext);
        msg_send_btn = findViewById(R.id.msg_send_btn);

        msg_user_name.setText(user_name);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        msg_rv_messages.setLayoutManager(manager);
        adapterMessages = new Adapter_Messages(list_message, user_name, getApplicationContext(), getParent());
        msg_rv_messages.setAdapter(adapterMessages);

        msg_send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = msg_messagetxt_edittext.getText().toString();
                if (!text.isEmpty()) {
                    msg_send(text);
                }
            }
        });
    }

    private void msg_send(String text) {
        String date = DateTimeManager.getCurrentDate();
        String time = DateTimeManager.getCurrentTime();

        Map<String, String> map = new HashMap<>();
        map.put("from", user.getUid());
        map.put("text", text);
        map.put("date", date);
        map.put("time", time);

        reference.child("messages").child(room_id).push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                msg_messagetxt_edittext.setText(null);
                msg_count++;
                room_id_save();
            }
        });
    }

    private void get_message() {
        reference.child("messages").child(room_id).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MessageModel messageModel = snapshot.getValue(MessageModel.class);
                list_message.add(messageModel);
                adapterMessages.notifyDataSetChanged();
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

    private void room_id_save() {
        if (msg_count == 1) {
            reference.child("users").child(user.getUid()).child("chatkeys").child(user_id).setValue(room_id);
            reference.child("users").child(user_id).child("chatkeys").child(user.getUid()).setValue(room_id);
        }
    }
}