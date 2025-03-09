<%-- 
    Document   : logout
    Created on : 8 mar 2025, 01:21:21
    Author     : cesar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%
    session.invalidate(); // Cerrar sesión
    response.sendRedirect("login.jsp"); // Redirigir a login.jsp
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sesion cerrada!</h1>
    </body>
</html>
