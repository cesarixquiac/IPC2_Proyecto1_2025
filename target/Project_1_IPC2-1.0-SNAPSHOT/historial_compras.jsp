<%-- 
    Document   : historial_compras
    Created on : 11 mar 2025, 15:13:38
    Author     : cesar
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.mycompany.project_1_ipc2.computadorafeliz.models.Venta"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Historial de Compras</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <div class="container mt-4">
        <h2 class="text-center mb-4">Historial de Compras</h2>

        <!-- Formulario para buscar compras por NIT -->
        <form method="get" action="HistorialComprasServlet" class="row g-3 justify-content-center">
            <div class="col-md-4">
                <label for="clienteNIT" class="form-label">NIT del Cliente:</label>
                <input type="text" id="clienteNIT" name="clienteNIT" class="form-control" placeholder="Ingrese el NIT del cliente" required />
            </div>
            <div class="col-md-2 d-flex align-items-end">
                <button type="submit" class="btn btn-primary w-100">Consultar Compras</button>
            </div>
        </form>

        <!-- Tabla de compras -->
        <div class="table-responsive mt-4">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID Venta</th>
                        <th>Fecha de Venta</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Venta> compras = (List<Venta>) request.getAttribute("compras");
                        if (compras != null && !compras.isEmpty()) {
                            for (Venta venta : compras) {
                    %>
                        <tr>
                            <td><%= venta.getId() %></td>
                            <td><%= venta.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) %></td>
                            <td>Q<%= String.format("%.2f", venta.getTotal()) %></td>
                        </tr>
                    <% 
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="3" class="text-center text-muted">No hay compras registradas para este cliente.</td>
                        </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
