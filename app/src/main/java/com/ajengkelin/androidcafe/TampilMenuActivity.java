package com.ajengkelin.androidcafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapter.MenuAdapter;
import adapter.TampilMenuAdapter;
import models.MenuCafe;

public class TampilMenuActivity extends AppCompatActivity {
    private TampilMenuAdapter mAdapter;
    private List<MenuCafe> mDaftarMenu= new ArrayList<>();
    DatabaseReference databaseRestoran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_menu);

        final RecyclerView recyclerMenu = (RecyclerView) findViewById(R.id.rvTampilMenu);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMenu.setLayoutManager(mLayoutManager);

        databaseRestoran = FirebaseDatabase.getInstance().getReference();
        databaseRestoran.child("menu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                mDaftarMenu= new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    MenuCafe menuRestoran= noteDataSnapshot.getValue(MenuCafe.class);
                    menuRestoran.setId_menu(noteDataSnapshot.getKey());
                    mDaftarMenu.add(menuRestoran);
                }

                mAdapter = new TampilMenuAdapter(TampilMenuActivity.this,mDaftarMenu);
                recyclerMenu.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
}
