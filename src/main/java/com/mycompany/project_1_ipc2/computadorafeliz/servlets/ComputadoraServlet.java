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
                request.getRequestDispatcher("listComputadoras.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
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
            }
        }
    }

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String nombre = request.getParameter("nombre");
    double precio = Double.parseDouble(request.getParameter("precio"));

    // Crear la computadora usando el constructor con nombre y precio (el ID no es necesario aquí)
    Computadora computadora = new Computadora(0, nombre, precio);  // El ID es 0 porque es nuevo y se generará automáticamente en la base de datos

    try {
        computadoraDAO.agregarComputadora(computadora);
        response.sendRedirect("computadora?action=list");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            computadoraDAO.eliminarComputadora(id);
            response.sendRedirect("computadora?action=list");
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
        response.sendRedirect("computadora?action=list");
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
