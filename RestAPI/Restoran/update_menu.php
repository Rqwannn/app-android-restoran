<?php

require "koneksi.php";

$id = $_POST["id"];
$kategori = $_POST["kategori"];
$title = $_POST['title'];
$deskripsi = $_POST['deskripsi'];
$harga = $_POST['harga'];
$qty = $_POST['qty'];

$toSmall = strtolower($kategori);

mysqli_query($conn, "UPDATE $toSmall SET title='$title', deskripsi='$deskripsi', harga='$harga', qty='$qty' WHERE id='$id'");

echo json_encode(
    [
        "Status" => true,
        "Pesan" => "$kategori Berhasil Di Ubah"
    ]
);