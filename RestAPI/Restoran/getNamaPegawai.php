<?php

require "koneksi.php";

$data = mysqli_query($conn, "SELECT * FROM log_aktivitas");

$wrapper = [];

while($result = mysqli_fetch_assoc($data)){
    $wrapper[] = $result;
}

echo json_encode(
    [
        "Status" => true,
        "Pesan" => "Data Berhasil Di Ambil",
        "Pegawai" => $wrapper
    ]
);