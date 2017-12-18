<?PHP
$hostname_localhost="localhost";
$database_localhost="reporte";
$username_localhost="root";
$password_localhost="12345678";

$json=array();

	if(isset($_GET["NomServicio"])){
		
		$NomServicio=$_GET['NomServicio'];
		
		
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="INSERT INTO servicio(NomServicio) VALUES ('{$NomServicio}')";
		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT * FROM servicio WHERE NomServicio = '{$NomServicio}'";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['servicio'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["NomServicio"]='No Registra';
			$json['servicio'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["NomServicio"]='No Registra';
			$json['servicio'][]=$resulta;
			echo json_encode($json);
		
		}



?>

