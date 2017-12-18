<?php 

//recibiendo usuario y pas 

$cargo=$_GET['name'];
$contra=$_GET['contrasena'];


$mysqli = new mysqli('localhost', 'root', '12345678', 'reporte');

$myArray = array();
if($result = $mysqli->query("select * from login where name = '$cargo' and contrasena = '$contra'")){
    while ($row = $result->fetch_array(MYSQLI_ASSOC)){
        $myArray[] = $row;
    }

echo json_encode($myArray);
}

 ?>


 