/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.project_1_ipc2.computadorafeliz.servlets;

import com.mycompany.project_1_ipc2.computadorafeliz.DAO.ClienteDAO;
import com.mycompany.project_1_ipc2.computadorafeliz.DAO.ComputadoraDAO;
import com.mycompany.project_1_ipc2.computadorafeliz.DAO.VentaDAO;
import com.mycompany.project_1_ipc2.computadorafeliz.models.Cliente;
import com.mycompany.project_1_ipc2.computadorafeliz.models.Computadora;
import com.mycompany.project_1_ipc2.computadorafeliz.models.User;
import com.mycompany.project_1_ipc2.computadorafeliz.models.Venta;


/**
 *
 * @author cesar
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

@WebServlet("/VentaServlet")
public class VentaServlet extends HttpServlet {
    private ClienteDAO clienteDAO;
    private ComputadoraDAO computadoraDAO;
    private VentaDAO ventaDAO;

    @Override
    public void init() throws ServletException {
        try {
            clienteDAO = new ClienteDAO();
            computadoraDAO = new ComputadoraDAO();
            ventaDAO = new VentaDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VentaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener la lista de computadoras disponibles
            List<Computadora> computadorasDisponibles = computadoraDAO.obtenerComputadoras();
            request.setAttribute("computadorasDisponibles", computadorasDisponibles);
            
            // Reenviar la solicitud a ventas.jsp para que pueda mostrar los datos
            request.getRequestDispatcher("ventas.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VentaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("buscarCliente") != null ? "buscarCliente" : 
                        request.getParameter("registrarVenta") != null ? "registrarVenta" : "";

        if (action.equals("buscarCliente")) {
            try {
                buscarCliente(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VentaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equals("registrarVenta")) {
            try {
                
                registrarVenta(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VentaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        String nit = request.getParameter("nit");
        Cliente cliente = clienteDAO.buscarPorNIT(nit);

        if (cliente != null) {
            request.setAttribute("nombreCliente", cliente.getNombre());
            request.setAttribute("direccionCliente", cliente.getDireccion());
        }

        List<Computadora> computadorasDisponibles = computadoraDAO.obtenerComputadoras();
        request.setAttribute("computadorasDisponibles", computadorasDisponibles);

        request.getRequestDispatcher("ventas.jsp").forward(request, response);
    }

    private void registrarVenta(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
    String nit = request.getParameter("nit");
    String nombre = request.getParameter("nombre");
    String direccion = request.getParameter("direccion");

    Cliente cliente = clienteDAO.buscarPorNIT(nit);
    if (cliente == null) {
        cliente = new Cliente(0, nit, nombre, direccion);
        clienteDAO.registrarCliente(cliente);
        // Obtener el ID generado después de registrarlo en la base de datos
        cliente = clienteDAO.buscarPorNIT(nit);
    }

    int computadoraId = Integer.parseInt(request.getParameter("computadoraId"));
    Computadora computadora = computadoraDAO.buscarComputadoraPorId(computadoraId);
    double total = computadora.getPrecio();

    // Suponiendo que el usuario está almacenado en la sesión
    HttpSession session = request.getSession();
    User usuario = (User) session.getAttribute("usuario"); 

    System.out.println("Usuario en sesión: " + (usuario != null ? usuario.getId() : "null"));
    System.out.println("Computadora ID: " + computadoraId);
    System.out.println("Cliente ID: " + (cliente != null ? cliente.getId() : "null"));

    // Obtener el ID de la computadora ensamblada
    int idComputadoraEnsamblada = computadoraDAO.obtenerIdComputadoraEnsamblada(computadoraId);

    // Verificar si realmente existe antes de registrar la venta
    if (idComputadoraEnsamblada == -1) {
        System.out.println("Error: No se encontró la computadora ensamblada para la computadora ID " + computadoraId);
        response.sendRedirect("ventas.jsp?error=computadoraNoEnsamblada");
        return;
    }

    // Si la computadora está ensamblada, proceder con la venta
    Venta venta = new Venta(0, cliente, usuario, LocalDateTime.now(), total);
    int ventaId = ventaDAO.registrarVenta(venta);

    // Registrar el detalle de la venta con el ID correcto
    ventaDAO.registrarDetalleVenta(ventaId, idComputadoraEnsamblada, 1, computadora.getPrecio());

    response.sendRedirect("ventas.jsp?success=true");
}




    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
