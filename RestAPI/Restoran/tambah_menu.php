<?php

require "koneksi.php";

$kategori = $_POST["kategori"];
$title = $_POST['title'];
$deskripsi = $_POST['deskripsi'];
$harga = $_POST['harga'];
$qty = $_POST['qty'];

$toSmall = strtolower($kategori);

mysqli_query($conn, "INSERT INTO $toSmall VALUES('','$title', '$deskripsi', '$harga', '$qty')");

echo json_encode(
    [
        "Status" => true,
        "Pesan" => "$kategori Berhasil Di Tambahkan"
    ]
);