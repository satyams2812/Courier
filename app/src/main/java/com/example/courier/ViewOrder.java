package com.example.courier;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewOrder extends AppCompatActivity {
    RecyclerView recview;
    myadapter adapter;
    DatabaseReference mbase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        recview = (RecyclerView) findViewById(R.id.recycler1);

        recview.setLayoutManager(
                new LinearLayoutManager(this));

        Intent intent=getIntent();
        String userphoneno= intent.getStringExtra("userphoneno");

        



        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<UserHelperClass> options
                = new FirebaseRecyclerOptions.Builder<UserHelperClass>()
                .setQuery(FirebaseDatabase.getInstance().getReference("users").orderByChild("userphoneno").equalTo(userphoneno), UserHelperClass.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself

        adapter = new myadapter(options);
        // Connecting Adapter class with the Recycler view*/
        recview.setAdapter(adapter);



    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }



    }

