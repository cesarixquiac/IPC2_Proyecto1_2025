<%-- 
    Document   : ensamblaje
    Created on : 9 mar 2025, 00:29:31
    Author     : cesar
--%>

<%@page import="com.mycompany.project_1_ipc2.computadorafeliz.models.EnsamblarComputadora"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ensamblajes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Ensamblajes</h1>

        <div class="row">
            <!-- Lista de ensamblajes -->
            <div class="col-md-7">
                <h2>Lista de Ensamblajes</h2>
                <table class="table table-striped">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Computadora</th>
                            <th>Usuario</th>
                            <th>Fecha</th>
                            <th>Costo</th>
                        </tr>
                    </thead>
                     <tbody>
        <% 
            // Obtener la lista de ensamblajes desde el request
            List<EnsamblarComputadora> ensamblajes = (List<EnsamblarComputadora>) request.getAttribute("ensamblajes");

            // Iterar sobre la lista de ensamblajes
            if (ensamblajes != null) {
                for (EnsamblarComputadora ensamblaje : ensamblajes) {
        %>
        <tr>
            <td><%= ensamblaje.getId() %></td>
            <td><%= ensamblaje.getComputadoraId() %></td>
            <td><%= ensamblaje.getUsuarioId() %></td>
            <td><%= ensamblaje.getFecha() %></td>
            <td><%= ensamblaje.getCosto() %></td>
        </tr>
        <% 
                }
            }
        %>
    </tbody>
                </table>
            </div>

            <!-- Formulario de nuevo ensamblaje -->
            <div class="col-md-5">
                <h2>Nuevo Ensamblaje</h2>
                <form action="ensamblaje" method="post" class="p-3 border rounded">
                    <div class="mb-3">
                        <label for="computadora_id" class="form-label">Computadora:</label>
                        <input type="text" id="computadora_id" name="computadora_id" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="usuario_id" class="form-label">Usuario:</label>
                        <input type="text" id="usuario_id" name="usuario_id" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="fecha" class="form-label">Fecha:</label>
                        <input type="date" id="fecha" name="fecha" class="form-control" required>
                    </div>
                    
                    <div class="mb-3">
                        <label for="costo" class="form-label">Costo:</label>
                        <input type="number" id="costo" name="costo" class="form-control" step="0.01" required>
                    </div>


                    <button type="submit" class="btn btn-primary w-100">Crear Ensamblaje</button>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
