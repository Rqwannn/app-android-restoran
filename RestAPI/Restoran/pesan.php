<?php

require "koneksi.php";

$id_menu = $_POST['id_menu'];
$nama_pegawai = $_POST["nama_pegawai"];
$nama_costumer = $_POST['nama_costumer'];
$total_harga = $_POST['total_harga'];
$jumlah_pesanan = $_POST['jumlah_pesanan'];
$kategori = $_POST["kategori"];

$toSmall = strtolower($kategori);

$insertMenu = mysqli_query($conn, "INSERT INTO pesanan_menu VALUES('', '$nama_pegawai', '$nama_costumer', '$total_harga', '$jumlah_pesanan', null)");
$idMenu = mysqli_insert_id($conn);

$status = "Menambahkan Pesanan $kategori";
$date = date("Y/m/d");

mysqli_query($conn, "INSERT INTO pesanan_$toSmall VALUES('', '$idMenu', '$id_menu')");
mysqli_query($conn, "UPDATE $toSmall SET qty = qty - '$jumlah_pesanan' WHERE id = '$id_menu'");
mysqli_query($conn, "INSERT INTO log_aktivitas VALUES('', '$nama_pegawai','$status','$date')");

echo json_encode(
    [
        "Status" => true,
        "Pesan" => "Pesanan Berhasil Di Pesan",
    ]
);