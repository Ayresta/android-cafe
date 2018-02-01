package com.ajengkelin.androidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapter.MasukkanJumlahAdapter;
import adapter.PilihMenuAdapter;
import models.MenuCafe;
import models.Pelanggan;
import models.Pesanan;

/**
 * Created by mkhamdank on 15/01/2018.
 */

public class MasukkanJumlahActivity extends AppCompatActivity {
    TextView NamaPelanggan;
    private MasukkanJumlahAdapter mAdapter;
    private List<Pesanan> mPesanan= new ArrayList<>();
    Button Pesan;
    //    Spinner SpinnerJumlah;
//    ArrayAdapter<Integer> adapter;
    DatabaseReference databaseRestoranMenu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masukkan_jumlah);



        Intent i = getIntent();
        final String nama = i.getStringExtra("nama");
//        final ArrayList<String> data = i.getStringArrayListExtra("namaMenu");
//        final ArrayList<String> jumlah = (ArrayList<String>) i.getSerializableExtra("jumlah");
//        final ArrayList<String> harga = (ArrayList<String>) i.getSerializableExtra("harga");
        final ArrayList<String> idMenu = (ArrayList<String>) i.getStringArrayListExtra("idMenu");
//        final ArrayList<String> idPesanan = (ArrayList<String>) i.getSerializableExtra("idPesanan");


        NamaPelanggan = (TextView) findViewById(R.id.namaPelanggan);
        NamaPelanggan.setText(nama);

        Pesan = (Button) findViewById(R.id.btnPesan);
        Pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(),DetailPesananActivity.class);
                p.putExtra("nama",nama);
                startActivity(p);
            }
        });

        final RecyclerView recyclerMenu = (RecyclerView) findViewById(R.id.rvJumlah);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMenu.setLayoutManager(mLayoutManager);

        databaseRestoranMenu = FirebaseDatabase.getInstance().getReference("pesanan");
        databaseRestoranMenu.orderByChild("namaPelanggan").equalTo(nama).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mPesanan= new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Pesanan pesanan = noteDataSnapshot.getValue(Pesanan.class);
                    pesanan.setIdPesanan(noteDataSnapshot.getKey());
                    mPesanan.add(pesanan);
                }
                mAdapter = new MasukkanJumlahAdapter(MasukkanJumlahActivity.this,mPesanan,idMenu);
                recyclerMenu.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        recyclerMenu.setItemAnimator(new DefaultItemAnimator());
//        recyclerMenu.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
    }
}

