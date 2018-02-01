package com.ajengkelin.androidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailMenuActivity extends AppCompatActivity {

    TextView TxtNamaMenu, TxtHarga, TxtStok;
    ImageButton BtnUpdate, BtnDelete;
    DatabaseReference databaseRestoran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);

        TxtNamaMenu = (TextView) findViewById(R.id.txtNamaMenu);
        TxtHarga = (TextView) findViewById(R.id.txtHarga);
        TxtStok = (TextView) findViewById(R.id.txtStok);

        BtnUpdate = (ImageButton) findViewById(R.id.btnUpdate);
        BtnDelete = (ImageButton) findViewById(R.id.btnDelete);

        Intent i = getIntent();
        final String idMenu = i.getStringExtra("idMenu");
        final String namaMenu = i.getStringExtra("namaMenu");
        final String harga = i.getStringExtra("harga");
        final String stok = i.getStringExtra("stok");

        TxtNamaMenu.setText(namaMenu);
        TxtHarga.setText(harga);
        TxtStok.setText(stok);

        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseRestoran = FirebaseDatabase.getInstance().getReference("menu").child(idMenu);
                databaseRestoran.removeValue();
                Toast.makeText(DetailMenuActivity.this,"Menu telah dihapus", Toast.LENGTH_SHORT).show();
                Intent p = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(p);
            }
        });

        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),UpdateMenuActivity.class);
                i.putExtra("idMenu",idMenu);
                i.putExtra("namaMenu",namaMenu);
                i.putExtra("harga",harga);
                i.putExtra("stok",stok);
                startActivity(i);
            }
        });
    }
}