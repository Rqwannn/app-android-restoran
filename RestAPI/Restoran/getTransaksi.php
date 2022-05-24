<?php

require "koneksi.php";

$key = $_POST['key'];
$kategori = $_POST['kategori'];

$data = mysqli_query($conn, "SELECT * FROM pesanan_menu");

if(strcmp($kategori, "Hari") == 0 || strcmp($kategori, "Bulan") == 0){
    $convert = $kategori == "Hari" ? "DATE(created_at)" : "MONTH(created_at)";
    $initial = $kategori == "Hari" ? "DATE(NOW())" : "MONTH(NOW())";

    $data = mysqli_query($conn, "SELECT * FROM pesanan_menu WHERE $initial = $convert");
}

if(strcmp($kategori, "Costum") == 0){
    $data = mysqli_query($conn, "SELECT * FROM pesanan_menu WHERE created_at LIKE '%$key%'");
}

if(strcmp($kategori, "Search") == 0){
    $data = mysqli_query($conn, "SELECT * FROM pesanan_menu WHERE nama_pegawai LIKE '%$key%'");
}

$wrapper = [];

while($result = mysqli_fetch_assoc($data)){
    $wrapper[] = $result;
}

echo json_encode(
    [
        "Status" => true,
        "Pesan" => "Data Berhasil Di Ambil",
        "Pesanan" => $wrapper,
    ]
);