package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.Adapter.LihatMenuAdapter;
import com.example.myapplication.Adapter.MenuAdapter;
import com.example.myapplication.Response.ResponseAPI;
import com.example.myapplication.ServerManage.APIRequest;
import com.example.myapplication.ServerManage.RetroServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LihatMakanan extends AppCompatActivity {

    RecyclerView rvData;
    RecyclerView.Adapter raData;
    RecyclerView.LayoutManager rlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_makanan);

        rvData = findViewById(R.id.parent_menu);
        setMenu("Makanan");
    }

    public void setMenu(String Kategori){
        APIRequest CallServer = new RetroServer().KonekToDB().create(APIRequest.class);
        Call<ResponseAPI> FeedBack = CallServer.getMenu(
                Kategori
        );

        rlData = new LinearLayoutManager(LihatMakanan.this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(rlData);

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                Boolean status = response.body().isStatus();
                String kategori = response.body().getKategori();

                if (status){
                    raData = new LihatMenuAdapter(LihatMakanan.this, response.body().getMenu(), kategori);
                    rvData.setAdapter(raData);
                    raData.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(LihatMakanan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}