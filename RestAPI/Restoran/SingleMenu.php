<?php

require "koneksi.php";

$kategori = $_POST['kategori'];
$id = $_POST['id'];

$toSmall = strtolower($kategori);

$data = mysqli_query($conn, "SELECT * FROM $toSmall WHERE id = '$id'");

$wrapper = [];

while($result = mysqli_fetch_assoc($data)){
    $wrapper[] = $result;
}

echo json_encode(
    [
        "Status" => true,
        "Pesan" => "Data Berhasil Di Ambil",
        "SingleMenu" => $wrapper[0],
        "Kategori" => $kategori
    ]
);