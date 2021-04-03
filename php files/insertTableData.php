<?php
require "connect.php";
$values=array();
$tableName=$_POST["tableName"];//To be received
$text=$_POST["data"];//To be received
$length=strlen($text);
$word="";
for($i=0;$i<$length;$i++){
    if($text[$i]=="@"){
        array_push($values,$word);
        $word="";
        continue;
    }
    $word.=$text[$i];
}
$mysql_query="INSERT INTO $tableName VALUES (";
$count=count($values);
for($i=0;$i<$count;$i++){
    $mysql_query.="'";
    $mysql_query.=$values[$i];
    $mysql_query.="'";
    if($i!=$count-1){
        $mysql_query.=",";
    }
}
$mysql_query.=");";
echo "$mysql_query";

mysqli_query($con,$mysql_query);