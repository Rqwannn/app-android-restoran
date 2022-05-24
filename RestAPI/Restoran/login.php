<?php

require "koneksi.php";

$username = $_POST['username'];
$password = $_POST['password'];

$data = mysqli_query($conn, "SELECT * FROM akun WHERE username = '$username' AND password = '$password'");

if(mysqli_num_rows($data) != 0){

    $array = mysqli_fetch_assoc($data);

    echo json_encode(
        [
            "Status" => true,
            "Pesan" => "Berhasil Login",
            "User" => [
                "Username" => $array['username'],
                "Roles" => $array['roles']
            ]
        ]
    );
} else {
    echo json_encode(
        [
            "Status" => false,
            "Pesan" => "Username Atau Password Anda Salah",
        ]
    );
}