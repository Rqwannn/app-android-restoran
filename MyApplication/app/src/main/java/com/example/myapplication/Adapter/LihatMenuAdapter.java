package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Menu.EditMenu;
import com.example.myapplication.Model.MenuModel;
import com.example.myapplication.R;
import com.example.myapplication.Transaksi.PesanMenu;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class LihatMenuAdapter extends RecyclerView.Adapter<LihatMenuAdapter.HolderData>{
    List<MenuModel> Data;
    Context ctx;
    String Kategoril;

    public LihatMenuAdapter(Context ctx, List<MenuModel> Data, String newKategori){
        this.Data = Data;
        this.ctx = ctx;
        this.Kategoril = newKategori;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lihat_menu, parent,false);
        LihatMenuAdapter.HolderData holder = new LihatMenuAdapter.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        MenuModel Model = Data.get(position);
        holder.title.setText(Model.getTitle());
        holder.qty.setText(String.valueOf(Model.getQty()) + " Pcs");
        holder.harga.setText(Model.getHarga());

        holder.card_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, EditMenu.class);
                intent.putExtra("Id", Model.getId());
                intent.putExtra("Kategori", Kategoril);
                ctx.startActivity(intent);
                ((Activity)ctx).overridePendingTransition(R.anim.enter_right_to_right, R.anim.exit_right_to_left);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView title, qty, harga;
        MaterialCardView card_menu;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            qty = itemView.findViewById(R.id.qty);
            harga = itemView.findViewById(R.id.harga);
            card_menu = itemView.findViewById(R.id.card_menu);
        }
    }
}
