package com.ajengkelin.androidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

import adapter.PilihMenuAdapter;
import models.MenuCafe;
import models.Pesanan;

/**
 * Created by Ajeng on 1/13/2018.
 */

    public class PilihMenuActivity extends AppCompatActivity {
        StringBuffer sb = null;
        Button BtnSelesai;
        TextView namaPelanggan;
        private PilihMenuAdapter mAdapter;
        private List<MenuCafe> mDaftarMenu= new ArrayList<>();
        DatabaseReference databaseRestoran;
        DatabaseReference databaseRestoranPesanan;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pilih_menu);

            BtnSelesai = (Button) findViewById(R.id.btnSelesai);
            namaPelanggan = (TextView) findViewById(R.id.namaPelanggan);

            final RecyclerView recyclerMenu = (RecyclerView) findViewById(R.id.rvPilihMenu);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerMenu.setLayoutManager(mLayoutManager);

            Intent i = getIntent();
            final String nama = i.getStringExtra("nama");

            namaPelanggan.setText(nama);

            databaseRestoran = FirebaseDatabase.getInstance().getReference();
            databaseRestoranPesanan = FirebaseDatabase.getInstance().getReference("pesanan");
            databaseRestoran.child("menu").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    mDaftarMenu= new ArrayList<>();
                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                        MenuCafe menuRestoran= noteDataSnapshot.getValue(MenuCafe.class);
                        menuRestoran.setId_menu(noteDataSnapshot.getKey());
                        mDaftarMenu.add(menuRestoran);
                    }
                    mAdapter = new PilihMenuAdapter(com.ajengkelin.androidcafe.PilihMenuActivity.this,mDaftarMenu);
                    recyclerMenu.setAdapter(mAdapter);

                    BtnSelesai.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sb=new StringBuffer();
                            ArrayList<String> data = new ArrayList<String>();
                            ArrayList<String> idMenu= new ArrayList<String>();
//                        ArrayList<String> harga = new ArrayList<String>();
//                        ArrayList<String> jumlah = new ArrayList<String>();
//                        ArrayList<String> idPesan= new ArrayList<String>();
                            for(MenuCafe menuRestoran : mAdapter.checkedMenu)
                            {
                                data.add(menuRestoran.getNama_menu());
                                idMenu.add(menuRestoran.getId_menu());
//                            jumlah.add(menuRestoran.getStok());
//                            harga.add(menuRestoran.getHarga());
                                sb.append(menuRestoran.getId_menu());
                                sb.append("\n");
                                String idPesanan = databaseRestoranPesanan.push().getKey();
//                            idPesan.add(idPesanan);
                                Pesanan pesanan= new Pesanan(idPesanan,nama,menuRestoran.getNama_menu(),"1",menuRestoran.getHarga(),"Belum Dibayar");
                                databaseRestoranPesanan.child(idPesanan).setValue(pesanan);
                            }

                            if(mAdapter.checkedMenu.size()>0)
                            {
                                Intent i = new Intent(getApplication(),MasukkanJumlahActivity.class);
//                            i.putExtra("namaMenu", data);
                                i.putExtra("idMenu", idMenu);
//                            i.putExtra("jumlah", jumlah);
//                            i.putExtra("harga", harga);
                                i.putExtra("nama",nama);
//                            i.putExtra("idPesanan",idPesan);
                                startActivity(i);
                                Toast.makeText(com.ajengkelin.androidcafe.PilihMenuActivity.this,data.toString(),Toast.LENGTH_SHORT).show();
                            }else
                            {
                                Toast.makeText(com.ajengkelin.androidcafe.PilihMenuActivity.this,"Silahkan Pilih Menu.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                }
            });
        }
    }

