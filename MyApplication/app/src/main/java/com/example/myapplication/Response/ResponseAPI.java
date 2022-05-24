package com.example.myapplication.Response;

import com.example.myapplication.Model.LogAktifitasModel;
import com.example.myapplication.Model.MenuModel;
import com.example.myapplication.Model.PesananModel;
import com.example.myapplication.Model.UserModel;

import java.util.List;

public class ResponseAPI {
    private boolean Status;
    private String Pesan, Kategori;
    private List<MenuModel> Menu;
    private List<LogAktifitasModel> Pegawai;
    private List<PesananModel> Pesanan;
    private MenuModel SingleMenu;
    private UserModel User;

    public boolean isStatus() {
        return Status;
    }

    public String getPesan() {
        return Pesan;
    }

    public List<MenuModel> getMenu() {
        return Menu;
    }

    public UserModel getUser() {
        return User;
    }

    public String getKategori() {
        return Kategori;
    }

    public MenuModel getSingleMenu() {
        return SingleMenu;
    }

    public List<LogAktifitasModel> getPegawai() {
        return Pegawai;
    }

    public List<PesananModel> getPesanan() {
        return Pesanan;
    }
}
