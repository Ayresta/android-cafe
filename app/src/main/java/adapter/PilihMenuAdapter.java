package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ajengkelin.androidcafe.R;

import java.util.ArrayList;
import java.util.List;

import models.MenuCafe;

/**
 * Created by Ajeng on 1/13/2018.
 */

public class PilihMenuAdapter extends RecyclerView.Adapter<PilihMenuAdapter.ViewHolder> {
    private Context context;
    private List<MenuCafe> listMenuRestoren;
    public List<MenuCafe> checkedMenu = new ArrayList<>();

    public PilihMenuAdapter(Context context, List<MenuCafe> listMenuRestoren) {
        this.context = context;
        this.listMenuRestoren = listMenuRestoren;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pilih_menu,parent,false);
        return new PilihMenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MenuCafe menu = listMenuRestoren.get(position);
        holder.TxtMenu.setText(menu.getNama_menu());
        holder.TxtHarga.setText(menu.getHarga());
        holder.chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox chk = (CheckBox) v;
                if (chk.isChecked()){
                    checkedMenu.add(menu);
                }
                else if (!chk.isChecked()){
                    checkedMenu.remove(menu);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMenuRestoren.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TxtMenu, TxtHarga;
        CheckBox chk;
        public ViewHolder(View itemView) {
            super(itemView);
            TxtMenu = itemView.findViewById(R.id.txtMenu);
            TxtHarga = itemView.findViewById(R.id.txtHarga);
            chk = itemView.findViewById(R.id.checkboxMenu);
        }
    }
}
