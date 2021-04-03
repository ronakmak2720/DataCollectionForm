<?php

require 'connect.php';
$tableName=$_POST["tableName"];//To be received
// $tableName="table001";
$query="select * FROM $tableName";

if($result = mysqli_query($con,$query)){

while ($row = mysqli_fetch_row($result)) {
    echo "\n";
    // echo "$row[0] $row[1] $row[2] $row[3]";
    $length=count($row);
    for($i=0;$i<$length;$i++){
        echo "$row[$i]	";
    }
  }
}