package com.hebun.messageapp.messages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hebun.messageapp.R;

public class MessagePage extends AppCompatActivity {
    String user_name, user_id;
    TextView msg_user_name;
    RecyclerView msg_rv_messages;
    EditText msg_messagetxt_edittext;
    ImageView msg_send_btn;

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
        definitions();

    }

    private void definitions() {
        msg_user_name = findViewById(R.id.msg_user_name);
        msg_rv_messages = findViewById(R.id.msg_rv_messages);
        msg_messagetxt_edittext = findViewById(R.id.msg_messagetxt_edittext);
        msg_send_btn = findViewById(R.id.msg_send_btn);
    }
}