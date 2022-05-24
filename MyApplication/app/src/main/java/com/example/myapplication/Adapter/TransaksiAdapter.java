package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Menu.EditMenu;
import com.example.myapplication.Model.MenuModel;
import com.example.myapplication.Model.PesananModel;
import com.example.myapplication.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.HolderData>{
    List<PesananModel> Data;
    Context ctx;

    public TransaksiAdapter(Context ctx, List<PesananModel> Data){
        this.Data = Data;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_detail_transaksi, parent,false);
        TransaksiAdapter.HolderData holder = new TransaksiAdapter.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        PesananModel Model = Data.get(position);
        holder.title.setText(Model.getNama_pegawai());
        holder.n_customer.setText(Model.getNama_customer());
        holder.t_harga.setText(Model.getTotal_harga());
        holder.jmlh_pesanan.setText(Model.getJumlah_pesanan());
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView title, n_customer, t_harga, jmlh_pesanan;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            n_customer = itemView.findViewById(R.id.n_customer);
            t_harga = itemView.findViewById(R.id.t_harga);
            jmlh_pesanan = itemView.findViewById(R.id.jmlh_pesanan);

        }
    }
}
