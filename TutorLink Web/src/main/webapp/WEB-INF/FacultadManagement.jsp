<%@ page import="entities.Facultad"%>
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
    <!-- Una vez recuperado estos objetos puedo ir al codigo -->
<title>Gestión de Facultades</title>
</head>
<body>
<div class="container">
    <h2>Alta y Gestión de Facultades</h2>
    
    <!-- Formulario para agregar/modificar una facultad -->
    <form action="FacultadServlet" method="post" class="mb-3">
        <input type="hidden" name="id" id="facultadId">
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>
        <div class="form-group">
            <label for="ciudad">Ciudad</label>
            <input type="text" class="form-control" id="ciudad" name="ciudad" required>
        </div>
        <div class="form-group">
            <label for="pais">País</label>
            <input type="text" class="form-control" id="pais" name="pais" required>
        </div>
        <button type="submit" class="btn btn-primary" name="action" value="add">Agregar Facultad</button>
        <button type="submit" class="btn btn-warning" name="action" value="update">Modificar Facultad</button>
        <button type="submit" class="btn btn-danger" name="action" value="delete">Eliminar Facultad</button>
    </form>

    <!-- Lista desplegable de facultades -->
    <div class="form-group">
        <label for="facultadesSelect">Seleccionar Facultad para Modificar</label>
        <select class="form-control" id="facultadesSelect" onchange="loadFacultadData()">
            <option value="">Seleccione una facultad</option>
            <% 
                LinkedList<Facultad> facultades = (LinkedList<Facultad>) request.getAttribute("facultades");
                for (Facultad f : facultades) {
            %>
                <option value="<%=f.getId()%>" data-nombre="<%=f.getNombre()%>" data-ciudad="<%=f.getCiudad()%>" data-pais="<%=f.getPais()%>">
                    <%=f.getNombre()%>
                </option>
            <% } %>
        </select>
    </div>
</div>

<script>
    // Cargar datos de la facultad seleccionada en el formulario
    function loadFacultadData() {
        const select = document.getElementById("facultadesSelect");
        const selectedOption = select.options[select.selectedIndex];
        
        if (selectedOption.value) {
            document.getElementById("facultadId").value = selectedOption.value;
            document.getElementById("nombre").value = selectedOption.getAttribute("data-nombre");
            document.getElementById("ciudad").value = selectedOption.getAttribute("data-ciudad");
            document.getElementById("pais").value = selectedOption.getAttribute("data-pais");
        } else {
            document.getElementById("facultadId").value = "";
            document.getElementById("nombre").value = "";
            document.getElementById("ciudad").value = "";
            document.getElementById("pais").value = "";
        }
    }
</script>

</body>
</html>