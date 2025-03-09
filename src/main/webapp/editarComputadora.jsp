<%-- 
    Document   : editarComputadora
    Created on : 9 mar 2025, 01:52:03
    Author     : cesar
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Computadora</title>
</head>
<body>
    <h1>Editar Computadora</h1>

    <form action="computadoraServlet" method="post">
        <input type="hidden" name="id" value="${computadora.id}" />
        
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="${computadora.nombre}" required /><br><br>
        
        <label for="precio">Precio:</label>
        <input type="number" id="precio" name="precio" value="${computadora.precio}" step="0.01" required /><br><br>
        
        <button type="submit">Actualizar</button>
    </form>

    <a href="computadoraServlet">Volver a la lista</a>
</body>
</html>
