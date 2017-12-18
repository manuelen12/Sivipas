<?PHP
$hostname_localhost="localhost";
$database_localhost="reporte";
$username_localhost="root";
$password_localhost="12345678";

$json=array();

	if(isset($_GET["NomPac"]) && isset($_GET["Documento"]) &&  isset($_GET["DescSuceso"])){
		
		$NomPac=$_GET['NomPac'];
		$Documento=$_GET['Documento'];
		$DescSuceso=$_GET['DescSuceso'];
		
		
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="INSERT INTO detallereport(NomPac,Documento,DescSuceso) VALUES ('{$NomPac}','{$Documento}','{$DescSuceso}')";
		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT * FROM detallereport WHERE NomPac = '{$NomPac}'";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['detallereport'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["NomPac"]=0;
			$resulta["Documento"]='No Registra';
			$resulta["DescSuceso"]='No Registra';
			$json['detallereport'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["NomPac"]=0;
			$resulta["Documento"]='WS No retorna';
			$resulta["DescSuceso"]='WS No retorna';
			$json['detallereport'][]=$resulta;
			echo json_encode($json);
		}

?>

