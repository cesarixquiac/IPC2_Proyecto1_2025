<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    String nombreUsuario = (String) session.getAttribute("nombreUsuario");
    String tipoUsuario = (String) session.getAttribute("tipoUsuario");

    if (nombreUsuario == null || tipoUsuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel de Control</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">La Computadora Feliz</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <% if ("Fábrica".equals(tipoUsuario)) { %>
                        <li class="nav-item"><a class="nav-link" href="PiezaServlet">Registrar Componentes</a></li>
                        <li class="nav-item"><a class="nav-link" href="EnsamblajeServlet">Ensamblar Computadoras</a></li>
                    <% } else if ("Punto de Venta".equals(tipoUsuario)) { %>
                        <li class="nav-item"><a class="nav-link" href="ventas.jsp">Registrar Ventas</a></li>
                        <li class="nav-item"><a class="nav-link" href="devoluciones.jsp">Computadoras Devueltas</a></li>
                    <% } else if ("Financiero y administracion".equals(tipoUsuario)) { %>
                        <li class="nav-item"><a class="nav-link" href="costos.jsp">Revisión de Costos</a></li>
                        <li class="nav-item"><a class="nav-link" href="usuarios.jsp">Administrar Usuarios</a></li>
                    <% } %>
                </ul>
                <span class="navbar-text text-white">
                    <%= nombreUsuario %> - <%= tipoUsuario %>
                </span>
                <a href="logout.jsp" class="btn btn-danger ms-3">Cerrar Sesión</a>
            </div>
        </div>
    </nav>

    <div class="container text-center mt-5">
        <h1>Bienvenido, <%= nombreUsuario %>!</h1>
        <h3>Área de trabajo: <%= tipoUsuario %></h3>
        <p>Selecciona una opción del menú para continuar.</p>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
