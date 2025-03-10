<%@page import="com.mycompany.project_1_ipc2.computadorafeliz.models.Pieza"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Piezas</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0-alpha1/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-4">Gestión de Piezas</h1>

        <!-- Mensaje de éxito -->
        <% Boolean success = (Boolean) request.getAttribute("success");
           if (success != null && success) { %>
            <script>console.log("GET exitoso: Se obtuvieron las piezas correctamente.");</script>
        <% } %>

        <!-- Formulario para agregar o editar una pieza -->
        <div class="card mt-4">
            <div class="card-header">
                <h5>Agregar o Editar Pieza</h5>
            </div>
            <div class="card-body">
                <form action="PiezaServlet" method="post">
                    <input type="hidden" name="id" value="${pieza.id}"> <!-- ID solo se usa cuando se edita -->
                    <div class="mb-3">
                        <label for="tipo" class="form-label">Tipo</label>
                        <input type="text" class="form-control" id="tipo" name="tipo" value="${pieza.tipo}" required>
                    </div>
                    <div class="mb-3">
                        <label for="costo" class="form-label">Costo</label>
                        <input type="number" class="form-control" id="costo" name="costo" value="${pieza.costo}" step="0.01" required>
                    </div>
                    <div class="mb-3">
                        <label for="cantidad" class="form-label">Cantidad</label>
                        <input type="number" class="form-control" id="cantidad" name="cantidad" value="${pieza.cantidad}" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </form>
            </div>
        </div>

        <!-- Tabla para listar las piezas -->
        <div class="card mt-4">
            <div class="card-header">
                <h5>Lista de Piezas</h5>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tipo</th>
                            <th>Costo</th>
                            <th>Cantidad</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            List<Pieza> piezas = (List<Pieza>) request.getAttribute("piezas");
                            if (piezas != null && !piezas.isEmpty()) {
                                for (Pieza pieza : piezas) { 
                        %>
                            <tr>
                                <td><%= pieza.getId() %></td>
                                <td><%= pieza.getTipo() %></td>
                                <td><%= pieza.getCosto() %></td>
                                <td><%= pieza.getCantidad() %></td>
                                <td>
                                    <a href="PiezaServlet?id=<%= pieza.getId() %>&action=edit" class="btn btn-warning btn-sm">Editar</a>
                                    <a href="PiezaServlet?id=<%= pieza.getId() %>&action=delete" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de eliminar esta pieza?');">Eliminar</a>
                                </td>
                            </tr>
                        <% 
                                }
                            } else { 
                        %>
                            <tr>
                                <td colspan="5">No se encontraron piezas.</td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Script para almacenar las piezas en un array de JavaScript -->
    <script>
        var piezas = [];
        <% if (piezas != null && !piezas.isEmpty()) { %>
            <% for (Pieza pieza : piezas) { %>
                piezas.push({
                    id: "<%= pieza.getId() %>",
                    nombre: "<%= pieza.getTipo() %>",
                    costo: "<%= pieza.getCosto() %>",
                    cantidad: "<%= pieza.getCantidad() %>"
                });
            <% } %>
            console.log("Piezas cargadas:", piezas);
        <% } %>
    </script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0-alpha1/js/bootstrap.bundle.min.js"></script>
</body>
</html>
