package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.Adapter.MenuAdapter;
import com.example.myapplication.Menu.DashbordMenu;
import com.example.myapplication.Menu.TambahMenu;
import com.example.myapplication.Response.ResponseAPI;
import com.example.myapplication.ServerManage.APIRequest;
import com.example.myapplication.ServerManage.RetroServer;
import com.example.myapplication.Transaksi.DetailTransaksi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvData_makanan, rvData_minuman;
    RecyclerView.Adapter raData;
    RecyclerView.LayoutManager rlData;
    SharedPreferences SessionManagement;
    SharedPreferences.Editor SessionEditor;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nav, menu);

        String roles = SessionManagement.getString("Roles", "");

        if (roles.equals("Kasir")){
            menu.findItem(R.id.menu).setVisible(false);
            menu.findItem(R.id.log_aktifitas).setVisible(false);
        }

        if (roles.equals("Admin")){
            menu.findItem(R.id.menu).setVisible(false);
            menu.findItem(R.id.transaksi).setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu:
                Intent intent = new Intent(MainActivity.this, DashbordMenu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_right_to_right, R.anim.exit_right_to_left);
                return true;
            case R.id.transaksi:
                Intent transakti = new Intent(MainActivity.this, DetailTransaksi.class);
                startActivity(transakti);
                overridePendingTransition(R.anim.enter_right_to_right, R.anim.exit_right_to_left);
                return true;
            case R.id.log_aktifitas:
                Intent log = new Intent(MainActivity.this, LogAktivity.class);
                startActivity(log);
                overridePendingTransition(R.anim.enter_right_to_right, R.anim.exit_right_to_left);
                return true;
            case R.id.logout:
                SessionEditor = SessionManagement.edit();
                SessionEditor.clear().commit();

                Intent intent2 = new Intent(MainActivity.this, Login.class);
                startActivity(intent2);
                finish();
                overridePendingTransition(R.anim.enter_right_to_right, R.anim.exit_right_to_left);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SessionManagement = getSharedPreferences("AUTH", MODE_PRIVATE);
        rvData_makanan = findViewById(R.id.parent_makanan);
        rvData_minuman = findViewById(R.id.parent_minuman);

        setMenu(rvData_makanan, "Makanan");
        setMenu(rvData_minuman, "Minuman");
    }

    public void setMenu(@NonNull RecyclerView newRvData, String Kategori){
        APIRequest CallServer = new RetroServer().KonekToDB().create(APIRequest.class);
        Call<ResponseAPI> FeedBack = CallServer.getMenu(
                Kategori
        );

        rlData = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        newRvData.setLayoutManager(rlData);

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                Boolean status = response.body().isStatus();

                if (status){
                    raData = new MenuAdapter(MainActivity.this, response.body().getMenu(), Kategori);
                    newRvData.setAdapter(raData);
                    raData.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}