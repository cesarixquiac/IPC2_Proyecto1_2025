/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.project_1_ipc2.computadorafeliz.servlets;

import com.mycompany.project_1_ipc2.computadorafeliz.DAO.ClienteDAO;
import com.mycompany.project_1_ipc2.computadorafeliz.DAO.VentaDAO;
import com.mycompany.project_1_ipc2.computadorafeliz.models.Cliente;
import com.mycompany.project_1_ipc2.computadorafeliz.models.User;
import com.mycompany.project_1_ipc2.computadorafeliz.models.Venta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cesar
 */

@WebServlet("/HistorialComprasServlet")
public class HistorialComprasServlet extends HttpServlet {
    private VentaDAO ventaDAO;
     private ClienteDAO clienteDAO;

    @Override
    public void init() throws ServletException {
        try {
            ventaDAO = new VentaDAO();
            clienteDAO = new ClienteDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HistorialComprasServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Obtener el NIT del cliente desde el formulario
    String clienteNIT = request.getParameter("clienteNIT");

    if (clienteNIT != null && !clienteNIT.isEmpty()) {
        try {
            // Buscar el cliente por su NIT
            Cliente cliente = clienteDAO.buscarPorNIT(clienteNIT);
            if (cliente != null) {
                // Obtener las compras del cliente
                List<Venta> compras = ventaDAO.obtenerHistorialComprasPorCliente(cliente.getId());
                request.setAttribute("compras", compras);
            } else {
                // Si el cliente no existe, mostrar un mensaje de error
                request.setAttribute("compras", new ArrayList<>());
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HistorialComprasServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Redirigir al JSP para mostrar los resultados
    request.getRequestDispatcher("historial_compras.jsp").forward(request, response);
}

}

