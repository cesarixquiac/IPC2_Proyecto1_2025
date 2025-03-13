/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.project_1_ipc2.computadorafeliz.servlets;

import com.mycompany.project_1_ipc2.computadorafeliz.DAO.ComputadoraDAO;
import com.mycompany.project_1_ipc2.computadorafeliz.models.Computadora;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cesar
 */
@WebServlet("/computadora")
public class ComputadoraServlet extends HttpServlet {

    private ComputadoraDAO computadoraDAO;

    @Override
    public void init() throws ServletException {
        computadoraDAO = new ComputadoraDAO();
    }

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    if (action == null) {
        // Obtener todas las computadoras
        try {
            List<Computadora> computadoras = computadoraDAO.obtenerComputadoras();
            request.setAttribute("computadoras", computadoras);
            request.getRequestDispatcher("computadora.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener las computadoras.");
        }
    } else if (action.equals("edit")) {
        // Buscar una computadora por ID
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Computadora computadora = computadoraDAO.buscarComputadoraPorId(id);
            request.setAttribute("computadora", computadora);
            request.getRequestDispatcher("editComputadora.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al buscar la computadora.");
        }
    } else if (action.equals("delete")) {
        // Eliminar una computadora
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            computadoraDAO.eliminarComputadora(id);
            System.out.println("Computadora eliminada con ID: " + id);
            response.sendRedirect("ComputadoraServlet"); // Redirigir después de eliminar
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar la computadora.");
        }
    }
}


   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String nombre = request.getParameter("nombre");
    double precio = Double.parseDouble(request.getParameter("precio"));

    Computadora computadora = new Computadora(0, nombre, precio);

    try {
        computadoraDAO.agregarComputadora(computadora);
        
        // Volver a cargar la lista de computadoras
        List<Computadora> computadoras = computadoraDAO.obtenerComputadoras();
        request.setAttribute("computadoras", computadoras);

        // Redirigir directamente a computadora.jsp
        request.getRequestDispatcher("computadora.jsp").forward(request, response);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        request.setAttribute("error", "Error al agregar la computadora.");
        request.getRequestDispatcher("computadora.jsp").forward(request, response);
    }
}


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            computadoraDAO.eliminarComputadora(id);
                  request.getRequestDispatcher("computadora.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
@Override
protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String nombre = request.getParameter("nombre");
    double precio = Double.parseDouble(request.getParameter("precio"));

    // Crear la computadora usando el constructor con id, nombre y precio
    Computadora computadora = new Computadora(id, nombre, precio);

    try {
        computadoraDAO.actualizarComputadora(computadora);
                request.getRequestDispatcher("computadora.jsp").forward(request, response);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
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
