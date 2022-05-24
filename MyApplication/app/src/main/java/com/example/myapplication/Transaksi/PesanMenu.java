package com.example.myapplication.Transaksi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Login;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.Response.ResponseAPI;
import com.example.myapplication.ServerManage.APIRequest;
import com.example.myapplication.ServerManage.RetroServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesanMenu extends AppCompatActivity {

    EditText et_customer, et_jumlah;
    TextView title_page;
    Button btn_kirim;
    int id_menu;
    String kategori, textCostumer, textJumlah, jumlah_harga;
    SharedPreferences SessionManagement;

    public void SubmitPesanan(){
        SessionManagement = getSharedPreferences("AUTH", MODE_PRIVATE);
        String nama_pegawai = SessionManagement.getString("Username", "");
        APIRequest CallServer = new RetroServer().KonekToDB().create(APIRequest.class);
        Call<ResponseAPI> FeedBack = CallServer.PesanMenu(
                id_menu,
                kategori,
                nama_pegawai,
                textCostumer,
                jumlah_harga,
                textJumlah
        );

        FeedBack.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {

                if (response.body().isStatus()){
                    Toast.makeText(PesanMenu.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(PesanMenu.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter_right_to_right, R.anim.exit_right_to_left);
                } else {
                    Toast.makeText(PesanMenu.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {
                Toast.makeText(PesanMenu.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_menu);

        et_customer = findViewById(R.id.et_customer);
        et_jumlah = findViewById(R.id.et_jumlah);

        Bundle extra = getIntent().getExtras();
        id_menu = extra.getInt("Id");
        kategori = extra.getString("Kategori");

        btn_kirim = findViewById(R.id.btn_kirim);
        title_page = findViewById(R.id.title_page);

        title_page.setText("Pesanan " + kategori);

        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textCostumer = et_customer.getText().toString();
                textJumlah = et_jumlah.getText().toString();
                jumlah_harga = String.valueOf(Integer.valueOf(extra.getString("Harga")) * Integer.parseInt(textJumlah));

                if (textCostumer.equals("") || textJumlah.equals("")){
                    Toast.makeText(PesanMenu.this, "Mohon Untuk Isi Field Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    SubmitPesanan();
                }

            }
        });
    }
}