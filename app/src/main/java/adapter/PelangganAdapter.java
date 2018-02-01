package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ajengkelin.androidcafe.DaftarPesananActivity;
import com.ajengkelin.androidcafe.R;

import java.util.List;

import models.MenuCafe;
import models.Pelanggan;

/**
 * Created by mkhamdank on 21/01/2018.
 */

public class PelangganAdapter extends RecyclerView.Adapter<PelangganAdapter.ViewHolder> {

    private Context context;
    private List<Pelanggan> listPelanggan;

    public PelangganAdapter(Context context, List<Pelanggan> listPelanggan) {
        this.context = context;
        this.listPelanggan = listPelanggan;
    }

    public PelangganAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelanggan,parent,false);
        return new PelangganAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pelanggan pelanggan = listPelanggan.get(position);
        holder.txtNamaPelanggan.setText(pelanggan.getNama());
        holder.BtnDaftarPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DaftarPesananActivity.class);
                i.putExtra("nama",pelanggan.getNama().toString());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPelanggan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNamaPelanggan;
        Button BtnDaftarPesanan;
        public ViewHolder(View itemView) {
            super(itemView);
            txtNamaPelanggan = itemView.findViewById(R.id.txtNama);
            BtnDaftarPesanan = itemView.findViewById(R.id.btnPesanan);
        }
    }
}
