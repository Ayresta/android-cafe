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

/**
 * Created by mkhamdank on 24/01/2018.
 */

public class TampilMenuAdapter extends RecyclerView.Adapter<TampilMenuAdapter.ViewHolder> {

    private Context context;
    private List<MenuCafe> listMenuRestoren;

    public TampilMenuAdapter(Context context, List<MenuCafe> listMenuRestoren) {
        this.context = context;
        this.listMenuRestoren = listMenuRestoren;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tampil_menu,parent,false);
        return new TampilMenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MenuCafe menu = listMenuRestoren.get(position);
        holder.TxtNamaMenu.setText(menu.getNama_menu());
        holder.TxtHarga.setText(menu.getHarga());
    }

    @Override
    public int getItemCount() {
        return listMenuRestoren.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TxtNamaMenu, TxtHarga;
        public ViewHolder(View itemView) {
            super(itemView);
            TxtNamaMenu = itemView.findViewById(R.id.txtMenu);
            TxtHarga = itemView.findViewById(R.id.txtHarga);
        }
    }
}
