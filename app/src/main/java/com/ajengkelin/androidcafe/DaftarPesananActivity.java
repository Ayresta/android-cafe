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

import adapter.DaftarPesananAdapter;
import adapter.DetailPesananAdapter;
import models.Pesanan;

public class DaftarPesananActivity extends AppCompatActivity {

    TextView txtNamaPelanggan,txtTotalHarga,txtStatus;
    DatabaseReference databaseRestoran;
    DatabaseReference databasePesanan;
    Button BtnBayar;
    String namaPelanggan;
    private DaftarPesananAdapter mAdapter;
    private List<Pesanan> mPesanan= new ArrayList<>();
    ArrayList<String> harga = new ArrayList<String>();
    ArrayList<String> idPesanan = new ArrayList<String>();
    ArrayList<String> status = new ArrayList<String>();
    String hartot;
    int hargaTotal = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pesanan);

        Intent p = getIntent();
        String nama = p.getStringExtra("nama");

        txtNamaPelanggan = (TextView) findViewById(R.id.txtNama);
        txtNamaPelanggan.setText(nama);

        txtTotalHarga = (TextView) findViewById(R.id.txtHargaTotal);
        BtnBayar = (Button) findViewById(R.id.btnBayar);

        txtStatus = (TextView) findViewById(R.id.txtStatus);

        final RecyclerView recyclerPesanan = (RecyclerView) findViewById(R.id.rvDaftarPesanan);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerPesanan.setLayoutManager(mLayoutManager);

        databaseRestoran = FirebaseDatabase.getInstance().getReference("pesanan");
        databaseRestoran.orderByChild("namaPelanggan").equalTo(nama).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Pesanan pesanan = noteDataSnapshot.getValue(Pesanan.class);
                    pesanan.setIdPesanan(noteDataSnapshot.getKey());
                    harga.add(pesanan.getHargaMenu());
                    mPesanan.add(pesanan);
                    idPesanan.add(pesanan.getIdPesanan());
                    status.add(pesanan.getStatus());
                }

                for (int i = 0; i < harga.size(); i++) {
                    hargaTotal = Integer.parseInt(harga.get(i)) + hargaTotal;
                    hartot = String.valueOf(hargaTotal);
                }
                txtTotalHarga.setText(hartot);

                mAdapter = new DaftarPesananAdapter(DaftarPesananActivity.this, mPesanan);
                recyclerPesanan.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());
            }
        });

        for (int j=0;j<status.size();j++){
            txtStatus.setText(status.get(j));
        }

        BtnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databasePesanan = FirebaseDatabase.getInstance().getReference("pesanan");
                for (int o = 0;o<idPesanan.size();o++){
                    databasePesanan.child(idPesanan.get(o)).child("status").setValue("Sudah Dibayar");
                }
                Intent o = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(o);
                Toast.makeText(getApplicationContext(), "Pesanan Sudah Dibayar.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
