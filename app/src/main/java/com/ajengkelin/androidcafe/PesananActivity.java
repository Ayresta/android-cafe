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
import adapter.PelangganAdapter;
import models.MenuCafe;
import models.Pelanggan;

public class PesananActivity extends AppCompatActivity {
    private PelangganAdapter mAdapter;
    private List<Pelanggan> mPelanggan= new ArrayList<>();
    DatabaseReference databaseRestoran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);

        final RecyclerView recyclerPelanggan = (RecyclerView) findViewById(R.id.rvPelanggan);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerPelanggan.setLayoutManager(mLayoutManager);

        databaseRestoran = FirebaseDatabase.getInstance().getReference();
        databaseRestoran.child("pelanggan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                mDaftarMenu= new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Pelanggan pelanggan= noteDataSnapshot.getValue(Pelanggan.class);
                    pelanggan.setId_pelanggan(noteDataSnapshot.getKey());
                    mPelanggan.add(pelanggan);
                }

                mAdapter = new PelangganAdapter(PesananActivity.this,mPelanggan);
                recyclerPelanggan.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
}
