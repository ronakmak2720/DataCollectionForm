<?php
require "connect.php";
$values=array();
$type=array();
$tableName=$_POST["tableName"];//To be received
$text=$_POST["data"];//To be received
// $text="name_varchar(255)@city_int@";//To be received
// $tableName="table0";//To be received
$count=0;
$word="";
$length=strlen($text);
for($i=0;$i<$length;$i++){
    if($text[$i]=="_"){
        array_push($values,$word);
        $word="";
        continue;
    }
    if($text[$i]=="@"){
        array_push($type,$word);
        $word="";
        continue;
    }
    $word.=$text[$i];
}
$length=count($type);
$mysql_qry="CREATE TABLE $tableName (";

for($i=0;$i<$length;$i++){
    $mysql_qry.=$values[$i];
    $mysql_qry.=" ";
    $mysql_qry.=$type[$i];
    if($i!=$length-1){
        $mysql_qry.=",";
    }
}
$mysql_qry.=");";
echo "$mysql_qry";
$result=mysqli_query($con,$mysql_qry);