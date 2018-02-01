package com.ajengkelin.androidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import models.Pelanggan;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
/**
 * Created by Ajeng on 1/12/2018.
 */

public class UserActivity extends AppCompatActivity {
    EditText TxtNama, TxtNoHp;
    Button BtnMasuk, BtnKembali;

    DatabaseReference databaseAndroidCafe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        databaseAndroidCafe = FirebaseDatabase.getInstance().getReference("pelanggan");

        TxtNama = (EditText) findViewById(R.id.txtNama);
        TxtNoHp = (EditText) findViewById(R.id.txtNoHp);
        BtnMasuk = (Button) findViewById(R.id.btnMasuk);

        BtnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMasuk();

            }

            public void saveMasuk() {
                if (!TextUtils.isEmpty(TxtNama.getText().toString()) && !TextUtils.isEmpty(TxtNoHp.getText().toString())) {
                    String id = databaseAndroidCafe.push().getKey();
                    Pelanggan pelanggan = new Pelanggan(id, TxtNama.getText().toString(), TxtNoHp.getText().toString());
                    databaseAndroidCafe.child(id).setValue(pelanggan);
                    Toast.makeText(UserActivity.this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplication(),PilihMenuActivity.class);
                    i.putExtra("nama",TxtNama.getText().toString());
                    startActivity(i);
                } else {
                    Toast.makeText(UserActivity.this, "Semua data harus diisi", Toast.LENGTH_SHORT).show();

                }
            }
        });


        BtnKembali = (Button) findViewById(R.id.btnKembali);
        BtnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
