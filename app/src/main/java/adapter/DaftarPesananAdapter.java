package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ajengkelin.androidcafe.R;

import java.util.List;

import models.Pesanan;

/**
 * Created by mkhamdank on 21/01/2018.
 */

public class DaftarPesananAdapter extends RecyclerView.Adapter<DaftarPesananAdapter.ViewHolder> {
    private Context context;
    private List<Pesanan> listPesanan;

    public DaftarPesananAdapter(Context context, List<Pesanan> listPesanan) {
        this.context = context;
        this.listPesanan = listPesanan;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daftar_pesanan,parent,false);
        return new DaftarPesananAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pesanan pesanan = listPesanan.get(position);
        holder.txtNamaMenu.setText(pesanan.getNamaMenu());
        holder.txtJumlah.setText(pesanan.getJumlahMenu());
        holder.txtHarga.setText(pesanan.getHargaMenu());
    }

    @Override
    public int getItemCount() {
        return listPesanan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNamaMenu,txtJumlah,txtHarga;
        public ViewHolder(View itemView) {
            super(itemView);
            txtNamaMenu = itemView.findViewById(R.id.txtNamaMenu);
            txtJumlah = itemView.findViewById(R.id.txtJumlah);
            txtHarga = itemView.findViewById(R.id.txtHarga);
        }
    }
}
