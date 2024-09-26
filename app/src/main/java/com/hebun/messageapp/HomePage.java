package com.hebun.messageapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hebun.messageapp.fragments.Fragment_Chats;
import com.hebun.messageapp.fragments.Fragment_User_Search;

public class HomePage extends AppCompatActivity {
    FrameLayout fragment_holder;
    BottomNavigationView bv_bar;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        definitions();
        fragment_page_change();
    }

    private void definitions () {
        fragment_holder = findViewById(R.id.fragment_holder);
        bv_bar = findViewById(R.id.bv_bar);
    }

    public void fragment_page_change () {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_holder, new Fragment_Chats()).commit();
        bv_bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.message_ic) {
                    fragment = new Fragment_Chats();
                }
                if (item.getItemId() == R.id.user_search_ic) {
                    fragment = new Fragment_User_Search();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, fragment).commit();
                return true;
            }
        });
    }
}