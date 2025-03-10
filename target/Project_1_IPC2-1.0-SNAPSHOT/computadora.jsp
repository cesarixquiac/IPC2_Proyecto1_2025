<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.project_1_ipc2.computadorafeliz.models.Computadora" %> 
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Computadoras</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <div class="container mt-4">
        <h1 class="text-center">Lista de Computadoras</h1>
        
        <!-- Formulario para agregar computadora -->
        <div class="card my-4">
            <div class="card-header bg-primary text-white">Agregar Nueva Computadora</div>
            <div class="card-body">
                <form action="ComputadoraServlet" method="post">
                    <input type="hidden" name="action" value="add">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre de la Computadora</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>
                    <div class="mb-3">
                        <label for="precio" class="form-label">Precio</label>
                        <input type="number" class="form-control" id="precio" name="precio" step="0.01" required>
                    </div>
                    <button type="submit" class="btn btn-success">Registrar Computadora</button>
                </form>
            </div>
        </div>

        <!-- Tabla de computadoras -->
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Computadora> computadoras = (List<Computadora>) request.getAttribute("computadoras");
                    if (computadoras != null) {
                        for (Computadora computadora : computadoras) { 
                %>
                    <tr>
                        <td><%= computadora.getId() %></td>
                        <td><%= computadora.getNombre() %></td>
                        <td>$<%= computadora.getPrecio() %></td>
                        <td>
                            <a href="ComputadoraServlet?action=edit&id=<%= computadora.getId() %>" class="btn btn-warning btn-sm">Editar</a>
                            <a href="ComputadoraServlet?action=delete&id=<%= computadora.getId() %>" class="btn btn-danger btn-sm">Eliminar</a>
                        </td>
                    </tr>
                <% 
                        } 
                    } else { 
                %>
                    <tr>
                        <td colspan="4" class="text-center">No hay computadoras registradas.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
