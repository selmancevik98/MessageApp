package com.hebun.messageapp.registrations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hebun.messageapp.HomePage;
import com.hebun.messageapp.R;

public class LoginLayout extends AppCompatActivity {
    TextInputEditText login_mail_edittext, login_password_edittext;
    Button login_btn;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user;
    TextView login_signuplayout_txt;

    @Override
    protected void onStart() {
        user = auth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(getApplicationContext(), HomePage.class);
            startActivity(intent);
            finishAffinity();
        }
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        definitions();
    }

    private void definitions () {
        login_signuplayout_txt = findViewById(R.id.login_signuplayout_txt);
        login_mail_edittext = findViewById(R.id.login_mail_edittext);
        login_password_edittext = findViewById(R.id.login_password_edittext);
        login_btn = findViewById(R.id.login_btn);

        String mail = login_mail_edittext.getText().toString();
        String password = login_password_edittext.getText().toString();

        login_signuplayout_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpLayout.class);
                startActivity(intent);

            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signInWithEmailAndPassword(mail, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(intent);
                        finishAffinity();
                    }
                });
            }
        });
    }

}