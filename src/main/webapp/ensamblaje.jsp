<%-- 
    Document   : ensamblaje
    Created on : 9 mar 2025, 00:29:31
    Author     : cesar
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ensamblajes</title>
</head>
<body>
    <h1>Lista de Ensamblajes</h1>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Computadora</th>
                <th>Usuario</th>
                <th>Fecha</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ensamblaje" items="${ensamblajes}">
                <tr>
                    <td>${ensamblaje.id}</td>
                    <td>${ensamblaje.computadora.nombre}</td>
                    <td>${ensamblaje.usuario.nombreUsuario}</td>
                    <td>${ensamblaje.fecha}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Nuevo Ensamblaje</h2>
    <form action="ensamblaje" method="post">
        <label for="computadora_id">Computadora:</label>
        <input type="text" id="computadora_id" name="computadora_id" required><br>

        <label for="usuario_id">Usuario:</label>
        <input type="text" id="usuario_id" name="usuario_id" required><br>

        <label for="fecha">Fecha:</label>
        <input type="date" id="fecha" name="fecha" required><br>

        <button type="submit">Crear Ensamblaje</button>
    </form>
</body>
</html>

