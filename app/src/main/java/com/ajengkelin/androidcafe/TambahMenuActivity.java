package com.ajengkelin.androidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import models.MenuCafe;

/**
 * Created by Ajeng on 1/13/2018.
 */

public class TambahMenuActivity extends AppCompatActivity {
    EditText TxtNamaMenu,TxtHarga,TxtStok;
    Button BtnTambah, BtnKembali;
    DatabaseReference databaseRestoranMenu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_menu);

        databaseRestoranMenu = FirebaseDatabase.getInstance().getReference("menu");

        TxtNamaMenu = (EditText) findViewById(R.id.txtNamaMenu);
        TxtHarga= (EditText) findViewById(R.id.txtHarga);
        TxtStok= (EditText) findViewById(R.id.txtStok);
        BtnTambah = (Button) findViewById(R.id.btnTambah);

        BtnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMenu();
                Intent i = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(i);
            }
        });

        BtnKembali = (Button) findViewById(R.id.btnKembali);
        BtnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AdminActivity.class);
                startActivity(i);
            }
        });
    }

    public void saveMenu(){
        if (!TextUtils.isEmpty(TxtNamaMenu.toString()) && !TextUtils.isEmpty(TxtHarga.toString()) && !TextUtils.isEmpty(TxtStok.toString())){
            String idMenu = databaseRestoranMenu.push().getKey();
            MenuCafe menu= new MenuCafe(idMenu,TxtNamaMenu.getText().toString(),TxtHarga.getText().toString(),TxtStok.getText().toString());
            databaseRestoranMenu.child(idMenu).setValue(menu);
            Toast.makeText(this, "Menu Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplication(),MenuActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Menu Gagal Ditambahkan",Toast.LENGTH_SHORT).show();
        }
    }
}
