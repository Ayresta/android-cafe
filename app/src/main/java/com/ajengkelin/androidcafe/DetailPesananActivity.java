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

import adapter.DetailPesananAdapter;
import adapter.MenuAdapter;
import models.MenuCafe;
import models.Pesanan;

public class DetailPesananActivity extends AppCompatActivity {
    TextView TxtNamaPelanggan,TotalHarga;
    Button BtnPesan;
//    String namaPelanggan;
    DatabaseReference databaseRestoran;
    private DetailPesananAdapter mAdapter;
    private List<Pesanan> mPesanan= new ArrayList<>();
    ArrayList<String> harga = new ArrayList<String>();
    String hartot;
    int hargaTotal = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);

        Intent p = getIntent();
        final String namaPelanggan = p.getStringExtra("nama");

        TxtNamaPelanggan = (TextView) findViewById(R.id.txtNamaPelanggan);
        TotalHarga = (TextView) findViewById(R.id.txtHargaTotal);
        BtnPesan = (Button) findViewById(R.id.btnPesan);

        TxtNamaPelanggan.setText(namaPelanggan);

        final RecyclerView recyclerPesanan = (RecyclerView) findViewById(R.id.rvPesanan);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerPesanan.setLayoutManager(mLayoutManager);

        databaseRestoran = FirebaseDatabase.getInstance().getReference("pesanan");
        databaseRestoran.orderByChild("namaPelanggan").equalTo(namaPelanggan).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Pesanan pesanan= noteDataSnapshot.getValue(Pesanan.class);
                    pesanan.setIdPesanan(noteDataSnapshot.getKey());
                    harga.add(pesanan.getHargaMenu());
                    mPesanan.add(pesanan);
                }
                for(int i = 0;i<harga.size();i++){
                    hargaTotal = Integer.parseInt(harga.get(i)) + hargaTotal;
                    hartot = String.valueOf(hargaTotal);
                }
                TotalHarga.setText(hartot);

                mAdapter = new DetailPesananAdapter(DetailPesananActivity.this,mPesanan);
                recyclerPesanan.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });





        BtnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(o);
                Toast.makeText(getApplicationContext(),"Terima kasih telah memesan. Silahkan tunggu pesanan Anda. Silahkan bertanya ke Kasir/Pelayan jika ada kesulitan.",Toast.LENGTH_LONG).show();
            }
        });
    }
}
