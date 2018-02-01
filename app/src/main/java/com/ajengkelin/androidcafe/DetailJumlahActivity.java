package com.ajengkelin.androidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapter.MasukkanJumlahAdapter;
import models.MenuCafe;
import models.Pesanan;

public class DetailJumlahActivity extends AppCompatActivity {
    TextView TxtNamaMenu,TxtHarga,TxtJumlah;
    Button BtnSelesai;
    DatabaseReference databaseRestoranMenu;
    DatabaseReference databaseRestoranMenuGetMenu;
    DatabaseReference databaseRestoranPesanan;
    String stokMenu;
    //    String idMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jumlah);

        TxtNamaMenu = (TextView) findViewById(R.id.txtNamaMenu);
        TxtHarga = (TextView) findViewById(R.id.txtHarga);
        TxtJumlah = (TextView) findViewById(R.id.txtJumlah);
        BtnSelesai = (Button) findViewById(R.id.btnSelesaiDetail);

        Intent i = getIntent();
        final String nama = i.getStringExtra("nama");
        final String namaMenu = i.getStringExtra("namaMenu");
        final String harga = i.getStringExtra("harga");
        final String jumlah = i.getStringExtra("jumlah");
        final String idPesanan = i.getStringExtra("idPesanan");
        final String idMenu = i.getStringExtra("idMenu");

        TxtNamaMenu.setText(namaMenu);
        TxtHarga.setText(harga);

        databaseRestoranMenuGetMenu = FirebaseDatabase.getInstance().getReference("menu");
        databaseRestoranMenuGetMenu.orderByChild("nama_menu").equalTo(namaMenu);
        databaseRestoranMenuGetMenu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    MenuCafe menuCafe = noteDataSnapshot.getValue(MenuCafe.class);
                    menuCafe.setId_menu(noteDataSnapshot.getKey());
                    stokMenu = menuCafe.getStok();
//                    idMenu = menuCafe.getId_menu();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        BtnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int jml = Integer.parseInt(TxtJumlah.getText().toString());
                int stok = Integer.parseInt(stokMenu);
                int stokBaru = stok - jml;
                String stokUpdate = String.valueOf(stokBaru);

                int hargaMenu = Integer.parseInt(harga);
                int hargaTot = jml*hargaMenu;
                String hargaBaru = String.valueOf(hargaTot);

                databaseRestoranMenu = FirebaseDatabase.getInstance().getReference("menu").child(idMenu);
                MenuCafe menuRestoran = new MenuCafe(idMenu,namaMenu,harga,stokUpdate);
                databaseRestoranMenu.setValue(menuRestoran);

                databaseRestoranPesanan = FirebaseDatabase.getInstance().getReference("pesanan").child(idPesanan);
                Pesanan pesanan= new Pesanan(idPesanan,nama,namaMenu,TxtJumlah.getText().toString(),hargaBaru,"Belum Dibayar");
                databaseRestoranPesanan.setValue(pesanan);

                Toast.makeText(DetailJumlahActivity.this,"Pesanan berhasil dibuat. Tekan tombol kembali.."+idMenu, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
