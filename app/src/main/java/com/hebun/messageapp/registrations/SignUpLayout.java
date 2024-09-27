package com.hebun.messageapp.registrations;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.hebun.messageapp.R;

public class SignUpLayout extends AppCompatActivity {
    Button signup_btn;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    TextInputEditText signup_mail_edittext, signup_password_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        definitions();
    }

    private void definitions () {
        signup_btn = findViewById(R.id.signup_btn);
        signup_mail_edittext = findViewById(R.id.signup_mail_edittext);
        signup_password_edittext = findViewById(R.id.signup_password_edittext);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = signup_mail_edittext.getText().toString();
                String password = signup_password_edittext.getText().toString();

                auth.createUserWithEmailAndPassword(mail, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent = new Intent(getApplicationContext(), Create_Profile_Page.class);
                        startActivity(intent);
                        finishAffinity();
                    }
                });
            }
        });
    }
}