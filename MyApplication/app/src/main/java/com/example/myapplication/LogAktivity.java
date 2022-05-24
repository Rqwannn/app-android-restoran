package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.Adapter.LihatMenuAdapter;
import com.example.myapplication.Adapter.LogAktifitasAdapter;
import com.example.myapplication.Response.ResponseAPI;
import com.example.myapplication.ServerManage.APIRequest;
import com.example.myapplication.ServerManage.RetroServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogAktivity extends AppCompatActivity {

    RecyclerView rvData;
    RecyclerView.Adapter raData;
    RecyclerView.LayoutManager rlData;

    public void setLogPegawai(){
        APIRequest CallServer = new RetroServer().KonekToDB().create(APIRequest.class);
        Call<ResponseAPI> FeedBack = CallServer.getNamaPegawai();

        rlData = new LinearLayoutManager(LogAktivity.this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(rlData);

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                Boolean status = response.body().isStatus();

                if (status){
                    raData = new LogAktifitasAdapter(LogAktivity.this, response.body().getPegawai());
                    rvData.setAdapter(raData);
                    raData.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(LogAktivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_aktivity);

        rvData = findViewById(R.id.log_activity);
        setLogPegawai();
    }
}