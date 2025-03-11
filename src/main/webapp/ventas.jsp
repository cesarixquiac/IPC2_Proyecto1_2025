<%-- 
    Document   : ventas
    Created on : 10 mar 2025, 19:18:57
    Author     : cesar
--%>

<%@page import="com.mycompany.project_1_ipc2.computadorafeliz.models.Computadora"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Venta</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Registrar Venta</h2>
        
        <form action="VentaServlet" method="post">
            <div class="mb-3">
                <label for="nit" class="form-label">NIT del Cliente:</label>
                <input type="text" class="form-control" id="nit" name="nit" required>
                <button type="submit" class="btn btn-primary mt-2" name="buscarCliente">Buscar Cliente</button>
            </div>
        </form>

        <%
            String nit = request.getParameter("nit");
            String nombre = (String) request.getAttribute("nombreCliente");
            String direccion = (String) request.getAttribute("direccionCliente");
            boolean clienteNuevo = (nombre == null);
        %>

        <% if (!clienteNuevo) { %>
            <form action="VentaServlet" method="post">
                <input type="hidden" name="nit" value="<%= nit %>">
                <div class="mb-3">
                    <label class="form-label">Nombre:</label>
                    <input type="text" class="form-control" name="nombre" value="<%= nombre %>" readonly>
                </div>
                <div class="mb-3">
                    <label class="form-label">Dirección:</label>
                    <input type="text" class="form-control" name="direccion" value="<%= direccion %>" readonly>
                </div>
        <% } else { %>
            <h3>Nuevo Cliente</h3>
            <form action="VentaServlet" method="post">
                <input type="hidden" name="nit" value="<%= nit %>">
                <div class="mb-3">
                    <label class="form-label">Nombre:</label>
                    <input type="text" class="form-control" name="nombre" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Dirección:</label>
                    <input type="text" class="form-control" name="direccion" required>
                </div>
        <% } %>

            <h3>Seleccionar Computadoras</h3>
            <div class="mb-3">
                <label class="form-label">Computadora:</label>
                <select class="form-select" name="computadoraId">
                    <%
                        List<Computadora> computadoras = (List<Computadora>) request.getAttribute("computadorasDisponibles");
                        if (computadoras != null) {
                            for (Computadora c : computadoras) {
                    %>
                        <option value="<%= c.getId() %>"><%= c.getNombre() %> - $<%= c.getPrecio() %></option>
                    <% 
                            }
                        }
                    %>
                </select>
            </div>

            <button type="submit" class="btn btn-success" name="registrarVenta">Confirmar Venta</button>
        </form>
    </div>
</body>
</html>

