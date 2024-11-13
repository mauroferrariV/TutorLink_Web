<%@ page import="entities.Usuario"%>
<%@ page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">
	
	<title>Java Web Intro</title>
	
	<!-- Bootstrap core CSS -->
    <link href="style/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/start.css" rel="stylesheet">
    
    <!-- Voy a recuperar las variables desde el jsp - Esto se hace al final del head, para que el browser cargue bien y cargue todo lo necesario -->
    <!-- En jsp hay 2 formas de utilizar codigo Java y acceder a la info que tenemos
    	1- Utilizando CODIGO EMBEBIDO y TEMPLATES -->
    <% 
    	Usuario u = (Usuario)session.getAttribute("usuario"); //Tomamos el nombre del atributo definido en el servlet utilizo (Usuario) ya que getAttribute nos devuelve un object y debemos castearlo a su correspondiente clase
    	LinkedList<Usuario> lu = (LinkedList<Usuario>)request.getAttribute("listaUsuarios");
    %>
    <!-- Una vez recuperado estos objetos puedo ir al codigo -->
</head>
<body>
	<div class="container">
	<div class="row">
		<h4>Personas</h4>
			<div class="col-12 col-sm-12 col-lg12">
				<div class="table-responsive"></div>
					<table class="table">
						<thead>
							<tr>
								<th>id</th>
								<th>nombre</th>
								<th>apellido</th>
								<th>email</th>
								<th>telefono</th>
								<th>dni</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<!-- En el body tengo que recorrer el listado de usuarios y por cada fila de este listado tengo que mostrar una fila en la tabla, por cada elemento (usuario)-->
							usuario
							<% for (Usuario usuario : lu) { %> <!-- Este codigo HTML se repetira entonces 1 vez por cada elemento de la lista -->
								<tr>
									<td><%=usuario.getId()%></td> <!-- Lo que yo indico poniendo = despues de la expresion es que quiero se ejecute y que la respuesta se copie dentro del HTML-->
									<td><%=usuario.getNombre()%></td>
									<td><%=usuario.getApellido()%></td>
									<td><%=usuario.getEmail()%></td>
									<td><%=usuario.getTelefono()%></td>
									<td><%=usuario.getDni()%></td>
									<!-- hay un ejemplo en el video con un atributo binario que lo representa como un checkbox y le otorga el marcado/no marcado segun el valor -->
									<td></td> <!-- editar -->
									<td></td> <!-- borrar -->
								</tr>
							<% } %>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div> 
</body>
</html>