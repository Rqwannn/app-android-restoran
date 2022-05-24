package com.example.myapplication.Transaksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myapplication.Adapter.LogAktifitasAdapter;
import com.example.myapplication.Adapter.TransaksiAdapter;
import com.example.myapplication.LogAktivity;
import com.example.myapplication.R;
import com.example.myapplication.Response.ResponseAPI;
import com.example.myapplication.ServerManage.APIRequest;
import com.example.myapplication.ServerManage.RetroServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransaksi extends AppCompatActivity {

    RecyclerView rvData;
    RecyclerView.Adapter raData;
    RecyclerView.LayoutManager rlData;
    String kategori = "", key = "";
    SearchView search_key;

    public void CallTransaksi(){
        APIRequest CallServer = new RetroServer().KonekToDB().create(APIRequest.class);
        Call<ResponseAPI> FeedBack = CallServer.getAktifitas(
                key,
                kategori
        );

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                Boolean status = response.body().isStatus();

                if (status){
                    raData = new TransaksiAdapter(DetailTransaksi.this, response.body().getPesanan());
                    rvData.setAdapter(raData);
                    raData.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(DetailTransaksi.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);

        rvData = findViewById(R.id.parent_menu);
        search_key = findViewById(R.id.search_key);

        rlData = new LinearLayoutManager(DetailTransaksi.this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(rlData);

        CallTransaksi();

        search_key.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                key = query;
                CallTransaksi();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void FilterData(View view) {
        String tag = view.getTag().toString();

        kategori = tag;

        if (tag.equals("Search") || tag.equals("Costum")){
            search_key.setVisibility(View.VISIBLE);
        } else {
            CallTransaksi();
            search_key.setVisibility(View.GONE);
        }
    }
}