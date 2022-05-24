<?php

require "koneksi.php";

$kategori = $_GET['kategori'];

$toSmall = strtolower($kategori);

$data = mysqli_query($conn, "SELECT * FROM $toSmall");

$wrapper = [];

while($result = mysqli_fetch_assoc($data)){
    $wrapper[] = $result;
}

echo json_encode(
    [
        "Status" => true,
        "Pesan" => "Data Berhasil Di Ambil",
        "Menu" => $wrapper,
        "Kategori" => $kategori
    ]
);