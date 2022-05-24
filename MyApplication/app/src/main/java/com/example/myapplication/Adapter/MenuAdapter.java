package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.MenuModel;
import com.example.myapplication.R;
import com.example.myapplication.Transaksi.PesanMenu;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.HolderData> {

    List<MenuModel> Data;
    Context ctx;
    SharedPreferences SessionManagement;
    String kategori;

    public MenuAdapter(Context ctx, List<MenuModel> Data, String newKategori){
        this.Data = Data;
        this.ctx = ctx;
        this.kategori = newKategori;
        SessionManagement = ctx.getSharedPreferences("AUTH", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_menu, parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        MenuModel Model = Data.get(position);
        holder.title.setText(Model.getTitle());
        holder.deskripsi.setText(Model.getDeskripsi());
        holder.harga.setText(Model.getHarga());

        if (SessionManagement.getString("Roles", "").equals("Kasir")){
            holder.card_manu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, PesanMenu.class);
                    intent.putExtra("Harga", Model.getHarga());
                    intent.putExtra("Id", Model.getId());
                    intent.putExtra("Kategori", kategori);
                    ctx.startActivity(intent);
                    ((Activity)ctx).overridePendingTransition(R.anim.enter_right_to_right, R.anim.exit_right_to_left);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView title, deskripsi, harga;
        RelativeLayout card_manu;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_card);
            deskripsi = itemView.findViewById(R.id.deskripsi);
            harga = itemView.findViewById(R.id.harga_menu);
            card_manu = itemView.findViewById(R.id.card_manu);
        }
    }
}
