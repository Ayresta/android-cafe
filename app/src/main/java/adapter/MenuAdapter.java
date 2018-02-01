package adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


import com.ajengkelin.androidcafe.DetailMenuActivity;
import com.ajengkelin.androidcafe.R;

import models.MenuCafe;

/**
 * Created by Ajeng on 1/13/2018.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private Context context;
    private List<MenuCafe> listMenuRestoren;

    public MenuAdapter(Context context, List<MenuCafe> listMenuRestoren) {
        this.context = context;
        this.listMenuRestoren = listMenuRestoren;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu,parent,false);
        return new MenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MenuCafe menu = listMenuRestoren.get(position);
        holder.TxtMenu.setText(menu.getNama_menu());
        holder.TxtStok.setText(menu.getStok());
        holder.TxtMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DetailMenuActivity.class);
                i.putExtra("idMenu",menu.getId_menu());
                i.putExtra("namaMenu",menu.getNama_menu());
                i.putExtra("harga",menu.getHarga());
                i.putExtra("stok",menu.getStok());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMenuRestoren.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView TxtMenu, TxtStok;
        public ViewHolder(View itemView) {
            super(itemView);
            TxtMenu = itemView.findViewById(R.id.txtMenu);
            TxtStok = itemView.findViewById(R.id.txtStok);
        }
    }
}
