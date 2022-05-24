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
import com.example.myapplication.Model.LogAktifitasModel;
import com.example.myapplication.Model.MenuModel;
import com.example.myapplication.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class LogAktifitasAdapter extends RecyclerView.Adapter<LogAktifitasAdapter.HolderData> {
    List<LogAktifitasModel> Data;
    Context ctx;

    public LogAktifitasAdapter(Context ctx, List<LogAktifitasModel> Data){
        this.Data = Data;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lihat_activity, parent,false);
        LogAktifitasAdapter.HolderData holder = new LogAktifitasAdapter.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        LogAktifitasModel Model = Data.get(position);
        holder.n_pegawai.setText(Model.getNama_pegawai());
        holder.t_status.setText(Model.getStatus());
        holder.date.setText(Model.getCreated_at());
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView n_pegawai, t_status, date;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            n_pegawai = itemView.findViewById(R.id.n_pegawai);
            t_status = itemView.findViewById(R.id.t_status);
            date = itemView.findViewById(R.id.date);
        }
    }
}
