<?PHP
$hostname_localhost="localhost";
$database_localhost="reporte";
$username_localhost="root";
$password_localhost="12345678";

$json=array();

	if(isset($_GET["name"]) && isset($_GET["contrasena"] ) &&  isset($_GET["rol"] )){
		
		$name=$_GET['name'];
		$contrasena=$_GET['contrasena'];
		$rol=$_GET['rol'];
		
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="INSERT INTO login(contrasena, name, rol) VALUES ('{$contrasena}','{$name}','{$rol}')";
		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT * FROM login WHERE name = '{$name}'";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['login'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["name"]=0;
			$resulta["contrasena"]='No Registra';
			$resulta["rol"]='No Registra';
			$json['login'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["name"]=0;
			$resulta["rol"]='WS No retorna';
			$resulta["contrasena"]='WS No retorna';
			$json['login'][]=$resulta;
			echo json_encode($json);
		}

?>

