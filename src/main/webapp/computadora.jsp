<%-- 
    Document   : computadora
    Created on : 9 mar 2025, 00:30:08
    Author     : cesar
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Computadoras</title>
</head>
<body>
    <h1>Lista de Computadoras</h1>

    <a href="computadoraServlet?action=add">Agregar Nueva Computadora</a>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="computadora" items="${computadoras}">
                <tr>
                    <td>${computadora.id}</td>
                    <td>${computadora.nombre}</td>
                    <td>${computadora.precio}</td>
                    <td>
                        <a href="computadoraServlet?action=edit&id=${computadora.id}">Editar</a>
                        <a href="computadoraServlet?action=delete&id=${computadora.id}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

