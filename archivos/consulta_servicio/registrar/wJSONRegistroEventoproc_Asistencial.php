<?PHP
$hostname_localhost="localhost";
$database_localhost="reporte";
$username_localhost="root";
$password_localhost="12345678";

$json=array();

	if(isset($_GET["Descripcion"])){
		
		$Descripcion=$_GET['Descripcion'];
		
		
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="INSERT INTO procasis (Descripcion) VALUES ('{$Descripcion}')";
		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT * FROM procasis WHERE Descripcion = '{$Descripcion}'";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['procasis'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["Descripcion"]='No Registra';
			$json['procasis'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["Descripcion"]='No Registra';
			$json['procasis'][]=$resulta;
			echo json_encode($json);
		
		}



?>

