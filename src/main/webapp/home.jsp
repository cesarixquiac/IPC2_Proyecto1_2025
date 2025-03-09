<%-- 
    Document   : Home
    Created on : 6 mar 2025, 20:25:45
    Author     : cesar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>

<%
    // Obtener la sesión existente (sin crear una nueva)
    HttpSession sesion = request.getSession(false);
    
    // Verificar si el usuario está autenticado
    if (sesion == null || sesion.getAttribute("nombreUsuario") == null) {
        response.sendRedirect("login.jsp"); // Redirigir al login si no hay sesión
        return; // Evitar que el resto del código se ejecute
    }

    // Si la sesión existe, obtener los datos del usuario
    String nombreUsuario = (String) sesion.getAttribute("nombreUsuario");
    String tipoUsuario = (String) sesion.getAttribute("tipoUsuario");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio - Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Sistema de Gestión</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Usuarios</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Reportes</a>
                    </li>
                </ul>
                <!-- Mostrar Usuario -->
                <span class="navbar-text text-white me-3">
                    <strong><%= nombreUsuario %></strong> - <%= tipoUsuario %>
                </span>
                <a class="btn btn-danger btn-sm" href="logout.jsp">Cerrar Sesión</a>
            </div>
        </div>
    </nav>

    <!-- Contenido principal -->
    <div class="container text-center mt-5">
        <h1 class="mb-4">¡Bienvenido, <%= nombreUsuario %>!</h1>
        <p class="lead">Selecciona una de las opciones para continuar.</p>

        <!-- Botones de opciones -->
        <div class="row mt-4">
            <div class="col-md-4">
                <a href="gestionUsuarios.jsp" class="btn btn-primary btn-lg w-100">Gestión de Usuarios</a>
            </div>
            <div class="col-md-4">
                <a href="reportes.jsp" class="btn btn-success btn-lg w-100">Ver Reportes</a>
            </div>
            <div class="col-md-4">
                <a href="configuracion.jsp" class="btn btn-warning btn-lg w-100">Configuración</a>
            </div>
        </div>
    </div>

    <!-- Bootstrap Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


