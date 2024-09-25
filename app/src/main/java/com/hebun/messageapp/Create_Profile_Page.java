package com.hebun.messageapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Create_Profile_Page extends AppCompatActivity {
    TextInputEditText profile_create_name_edittext, profile_create_surname_edittext, profile_create_username_edittext;
    Button profile_save_btn;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    String name = "", surname = "", username = "";
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_profile_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        definitions();
    }

    private void definitions() {
        profile_create_name_edittext = findViewById(R.id.profile_create_name_edittext);
        profile_create_surname_edittext = findViewById(R.id.profile_create_surname_edittext);
        profile_create_username_edittext = findViewById(R.id.profile_create_username_edittext);
        profile_save_btn = findViewById(R.id.profile_save_btn);

        profile_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = profile_create_name_edittext.getText().toString();
                surname = profile_create_surname_edittext.getText().toString();
                username = profile_create_surname_edittext.getText().toString();

                if (!username.matches("")) {
                    Map<String, String> map = new HashMap<>();

                    map.put("name", name);
                    map.put("surname", surname);
                    map.put("username", username);

                    reference.child("users").child(user.getUid()).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Intent intent = new Intent(getApplicationContext(), HomePage.class);
                            startActivity(intent);
                            finishAffinity();
                        }
                    });
                }
            }
        });
    }
}