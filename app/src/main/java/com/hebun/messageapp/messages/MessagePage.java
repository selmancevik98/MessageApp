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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
            room_id = reference.child("messages").push().toString();
        }
        definitions();

    }


    private void definitions() {
        msg_user_name = findViewById(R.id.msg_user_name);
        msg_rv_messages = findViewById(R.id.msg_rv_messages);
        msg_messagetxt_edittext = findViewById(R.id.msg_messagetxt_edittext);
        msg_send_btn = findViewById(R.id.msg_send_btn);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        msg_rv_messages.setLayoutManager(manager);
        adapterMessages = new Adapter_Messages(list_message, user_name, getApplicationContext(), getParent());
        msg_rv_messages.setAdapter(adapterMessages);

        msg_send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg_send();
            }
        });
    }

    private void msg_send() {
        String text = msg_messagetxt_edittext.getText().toString();
        String date = DateTimeManager.getCurrentDate();
        String time = DateTimeManager.getCurrentTime();

        Map<String, String> map = new HashMap<>();
        map.put("from", user.getUid());
        map.put("text", text);
        map.put("date", date);
        map.put("time", time);

        reference.child("messages").child(room_id).push().setValue(map);
    }
}