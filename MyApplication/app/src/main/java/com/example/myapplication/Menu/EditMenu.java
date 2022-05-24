package com.example.myapplication.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Response.ResponseAPI;
import com.example.myapplication.ServerManage.APIRequest;
import com.example.myapplication.ServerManage.RetroServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMenu extends AppCompatActivity {

    EditText et_menu, et_deskripsi, et_harga, et_qty;
    Button btn_kirim;
    int id_menu;
    String kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);

        et_menu = findViewById(R.id.et_menu);
        et_deskripsi = findViewById(R.id.et_deskripsi);
        et_harga = findViewById(R.id.et_harga);
        et_qty = findViewById(R.id.et_qty);

        btn_kirim = findViewById(R.id.btn_kirim);

        Bundle extra = getIntent().getExtras();
        id_menu = extra.getInt("Id");
        kategori = extra.getString("Kategori");

        getSingleMenu();

        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String menu = et_menu.getText().toString();
                String deskripsi = et_deskripsi.getText().toString();
                String harga = et_harga.getText().toString();
                String qty = et_qty.getText().toString();

                if (menu.equals("") || deskripsi.equals("") || harga.equals("") || qty.equals("")){
                    Toast.makeText(EditMenu.this, "Mohon Isi Field Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    APIRequest CallServer = new RetroServer().KonekToDB().create(APIRequest.class);
                    Call<ResponseAPI> FeedBack = CallServer.EditMenu(
                            id_menu,
                            kategori,
                            menu,
                            deskripsi,
                            harga,
                            qty
                    );

                    FeedBack.enqueue(new Callback<ResponseAPI>() {
                        @Override
                        public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                            Toast.makeText(EditMenu.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(EditMenu.this, DashbordMenu.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.enter_right_to_right, R.anim.exit_right_to_left);
                        }

                        @Override
                        public void onFailure(Call<ResponseAPI> call, Throwable t) {
                            Toast.makeText(EditMenu.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }

    public void getSingleMenu(){
        APIRequest CallServer = new RetroServer().KonekToDB().create(APIRequest.class);
        Call<ResponseAPI> FeedBack = CallServer.getSingleMenu(
                id_menu,
                kategori
        );

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                et_menu.setText(response.body().getSingleMenu().getTitle());
                et_deskripsi.setText(response.body().getSingleMenu().getDeskripsi());
                et_harga.setText(response.body().getSingleMenu().getHarga());
                et_qty.setText(String.valueOf(response.body().getSingleMenu().getQty()));
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(EditMenu.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}