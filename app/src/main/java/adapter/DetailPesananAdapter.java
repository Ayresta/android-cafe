package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ajengkelin.androidcafe.R;

import java.util.List;

import models.MenuCafe;
import models.Pesanan;

/**
 * Created by mkhamdank on 20/01/2018.
 */

public class DetailPesananAdapter extends RecyclerView.Adapter<DetailPesananAdapter.ViewHolder> {

    private Context context;
    private List<Pesanan> listPesanan;

    public DetailPesananAdapter() {
    }

    public DetailPesananAdapter(Context context, List<Pesanan> listPesanan) {
        this.context = context;
        this.listPesanan = listPesanan;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesanan,parent,false);
        return new DetailPesananAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pesanan pesanan = listPesanan.get(position);
        holder.txtNamaMenu.setText(pesanan.getNamaMenu());
        holder.txtHarga.setText(pesanan.getHargaMenu());
        holder.txtJumlah.setText(pesanan.getJumlahMenu());
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
