package com.example.myapplication.ServerManage;

import com.example.myapplication.Response.ResponseAPI;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIRequest {
    @GET("menu.php?")
    Call<ResponseAPI> getMenu(
            @Query("kategori") String Kategori
    );

    @GET("getNamaPegawai.php")
    Call<ResponseAPI> getNamaPegawai();

    @FormUrlEncoded
    @POST("SingleMenu.php")
    Call<ResponseAPI> getSingleMenu(
            @Field("id") int ID_Menu,
            @Field("kategori") String Kategori
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseAPI> Auth(
            @Field("username") String Username,
            @Field("password") String Password
    );

    @FormUrlEncoded
    @POST("tambah_menu.php")
    Call<ResponseAPI> TambahMenu(
            @Field("kategori") String Kategori,
            @Field("title") String Title,
            @Field("deskripsi") String Deskripsi,
            @Field("harga") String Harga,
            @Field("qty") String Qty
    );

    @FormUrlEncoded
    @POST("update_menu.php")
    Call<ResponseAPI> EditMenu(
            @Field("id") int id,
            @Field("kategori") String Kategori,
            @Field("title") String Title,
            @Field("deskripsi") String Deskripsi,
            @Field("harga") String Harga,
            @Field("qty") String Qty
    );

    @FormUrlEncoded
    @POST("pesan.php")
    Call<ResponseAPI> PesanMenu(
            @Field("id_menu") int id,
            @Field("kategori") String Kategori,
            @Field("nama_pegawai") String NamaPegawai,
            @Field("nama_costumer") String NamaCostumer,
            @Field("total_harga") String TotalHarga,
            @Field("jumlah_pesanan") String JumlahPesanan
    );

    @FormUrlEncoded
    @POST("getTransaksi.php")
    Call<ResponseAPI> getAktifitas(
        @Field("key") String Key,
        @Field("kategori") String Kategori
    );
}
