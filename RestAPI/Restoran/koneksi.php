<?php

define("HOST", "localhost");
define("USER", "root");
define("PASSWORD", "");
define("DB", "restoran_penjualan");

$conn = mysqli_connect(HOST, USER, PASSWORD, DB) or die("Unable To Connect");

header("Content-Type:application/json");
