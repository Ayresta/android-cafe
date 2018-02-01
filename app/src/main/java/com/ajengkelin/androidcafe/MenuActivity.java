package com.ajengkelin.androidcafe;

import android.content.Intent;
import android.support.annotation.MenuRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import adapter.MenuAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import models.MenuCafe;

/**
 * Created by Ajeng on 1/12/2018.
 */

public class MenuActivity extends AppCompatActivity {
    Button BtnTambahMenu;
    private MenuAdapter mAdapter;
    private List<MenuCafe> mDaftarMenu= new ArrayList<>();
    DatabaseReference databaseRestoran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final RecyclerView recyclerMenu = (RecyclerView) findViewById(R.id.rvMenu);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMenu.setLayoutManager(mLayoutManager);
//        recyclerMenu.setItemAnimator(new DefaultItemAnimator());
//        recyclerMenu.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();

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

                mAdapter = new MenuAdapter(MenuActivity.this,mDaftarMenu);
                recyclerMenu.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

        BtnTambahMenu = (Button) findViewById(R.id.btnTambahMenu);
        BtnTambahMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TambahMenuActivity.class);
                startActivity(i);
            }
        });
    }
}
