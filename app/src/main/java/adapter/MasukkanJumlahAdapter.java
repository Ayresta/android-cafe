package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ajengkelin.androidcafe.DetailJumlahActivity;
import com.ajengkelin.androidcafe.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import models.MenuCafe;
import models.Pesanan;

/**
 * Created by Ajeng on 1/15/2018.
 */

public class MasukkanJumlahAdapter extends RecyclerView.Adapter<MasukkanJumlahAdapter.ViewHolder> {
    private Context context;
    private List<Pesanan> listPesanan;
    //    public ArrayList<String> nData;
//    public ArrayList<String> nJumlah;
//    public ArrayList<String> nHarga;
    public ArrayList<String> nIdMenu;
//    public ArrayList<String> idPesanan;
//    public String nama;


    public MasukkanJumlahAdapter(Context context, List<Pesanan> listPesanan,ArrayList<String> nIdMenu) {
        this.context = context;
        this.listPesanan= listPesanan;
        this.nIdMenu = nIdMenu;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_masukkan_jumlah,parent,false);
        return new MasukkanJumlahAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Pesanan pesanan = listPesanan.get(position);
//        final String data = nData.get(position);
        holder.TxtNamaMenu.setText(pesanan.getNamaMenu());
        holder.BtnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),DetailJumlahActivity.class);
                i.putExtra("namaMenu",pesanan.getNamaMenu().toString());
                i.putExtra("jumlah",pesanan.getJumlahMenu().toString());
                i.putExtra("harga",pesanan.getHargaMenu().toString());
                i.putExtra("idPesanan",pesanan.getIdPesanan().toString());
                i.putExtra("nama",pesanan.getNamaPelanggan().toString());
                i.putExtra("idMenu",nIdMenu.get(position));
                v.getContext().startActivity(i);
                Toast.makeText(v.getContext(),pesanan.getNamaMenu(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPesanan.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView TxtNamaMenu;
        public Button BtnPlus;
        public ViewHolder(View itemView) {
            super(itemView);
            TxtNamaMenu = itemView.findViewById(R.id.txtNamaMenu);
            BtnPlus = itemView.findViewById(R.id.btnPlus);
        }
    }
}
