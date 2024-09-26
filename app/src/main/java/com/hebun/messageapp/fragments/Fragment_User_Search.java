package com.hebun.messageapp.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hebun.messageapp.R;
import com.hebun.messageapp.adapters.Adapter_User_Search;
import com.hebun.messageapp.models.Model_User_Search;

import java.util.ArrayList;
import java.util.List;

public class Fragment_User_Search extends Fragment {
    List<Model_User_Search> searchList = new ArrayList<>();
    List<Model_User_Search> filteredlist = new ArrayList<>();
    Adapter_User_Search adapterUserSearch;
    EditText user_search_edittext;
    RecyclerView rv_user_search;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_user_search = inflater.inflate(R.layout.activity_fragment_user_search, container, false);
        definitions(view_user_search);
        return view_user_search;
    }

    private void definitions (View view) {
     user_search_edittext = view.findViewById(R.id.user_search_edittext);
     rv_user_search = view.findViewById(R.id.rv_user_search);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rv_user_search.setLayoutManager(manager);
        adapterUserSearch = new Adapter_User_Search(filteredlist, getContext(), getActivity());
        rv_user_search.setAdapter(adapterUserSearch);

        pull_data();
        setupSearch();
    }

    private void pull_data() {
        reference.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (!snapshot.getKey().matches(user.getUid())) {
                    Model_User_Search modelUserSearch = snapshot.getValue(Model_User_Search.class);
                    modelUserSearch.setUser_id(snapshot.getKey());
                    searchList.add(modelUserSearch);
                    filteredlist.add(modelUserSearch);
                    adapterUserSearch.notifyDataSetChanged();
                }
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

    private void setupSearch() {
        user_search_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void filter(String text) {
        filteredlist.clear();
        for (Model_User_Search item: searchList) {
            if (item.getUsername().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        adapterUserSearch.notifyDataSetChanged();
    }
}
