package com.ajengkelin.androidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import models.MenuCafe;

public class UpdateMenuActivity extends AppCompatActivity {

    EditText TxtNamaMenuUpdate,TxtHargaUpdate,TxtStokUpdate;
    Button BtnUpdate, BtnKembaliUpdate;
    DatabaseReference databaseRestoranMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_menu);

        Intent i = getIntent();
        final String idMenu = i.getStringExtra("idMenu");
        final String namaMenu = i.getStringExtra("namaMenu");
        final String harga = i.getStringExtra("harga");
        final String stok = i.getStringExtra("stok");

        TxtNamaMenuUpdate = (EditText) findViewById(R.id.txtNamaMenuUpdate);
        TxtHargaUpdate = (EditText) findViewById(R.id.txtHargaUpdate);
        TxtStokUpdate = (EditText) findViewById(R.id.txtStokUpdate);

        TxtNamaMenuUpdate.setText(namaMenu);
        TxtHargaUpdate.setText(harga);
        TxtStokUpdate.setText(stok);

        BtnUpdate = (Button) findViewById(R.id.btnUpdate);
        BtnKembaliUpdate = (Button) findViewById(R.id.btnKembaliUpdate);

        BtnKembaliUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(a);
            }
        });

        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseRestoranMenu = FirebaseDatabase.getInstance().getReference("menu").child(idMenu);
                MenuCafe menuRestoran = new MenuCafe(idMenu,TxtNamaMenuUpdate.getText().toString(),TxtHargaUpdate.getText().toString(),TxtStokUpdate.getText().toString());
                databaseRestoranMenu.setValue(menuRestoran);
                Toast.makeText(UpdateMenuActivity.this,"Menu berhasil di update.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(i);
            }
        });
    }
}
