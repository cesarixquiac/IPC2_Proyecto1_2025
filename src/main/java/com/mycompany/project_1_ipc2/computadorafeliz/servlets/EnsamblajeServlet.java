/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.project_1_ipc2.computadorafeliz.servlets;

import com.mycompany.project_1_ipc2.computadorafeliz.DAO.ComputadoraDAO;
import com.mycompany.project_1_ipc2.computadorafeliz.DAO.EnsamblarComputadoraDAO;
import com.mycompany.project_1_ipc2.computadorafeliz.DAO.UserDAO;
import com.mycompany.project_1_ipc2.computadorafeliz.models.EnsamblarComputadora;
import com.mycompany.project_1_ipc2.computadorafeliz.models.Computadora;
import com.mycompany.project_1_ipc2.computadorafeliz.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author cesar
 */
@WebServlet("/ensamblaje")
public class EnsamblajeServlet extends HttpServlet {

    private EnsamblarComputadoraDAO ensamblarComputadoraDAO = new EnsamblarComputadoraDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Obtener todos los ensamblajes
    try {
        List<EnsamblarComputadora> ensamblajes = ensamblarComputadoraDAO.obtenerTodosLosEnsamblajes();
        request.setAttribute("ensamblajes", ensamblajes);
        
        // Imprimir para verificar los ensamblajes
        if (ensamblajes != null && !ensamblajes.isEmpty()) {
            for (EnsamblarComputadora ensamblaje : ensamblajes) {
                System.out.println("Ensamblaje ID: " + ensamblaje.getId() +
                                   ", Computadora ID: " + ensamblaje.getComputadoraId()+
                                   ", Usuario ID: " + ensamblaje.getUsuarioId()+
                                   ", Fecha: " + ensamblaje.getFecha() +
                                   ", Costo Total: " + ensamblaje.getCosto());
            }
        } else {
            System.out.println("No se encontraron ensamblajes.");
        }
         request.getRequestDispatcher("ensamblaje.jsp").forward(request, response);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Validar parámetros
            String computadoraIdParam = request.getParameter("computadora_id");
            String usuarioIdParam = request.getParameter("usuario_id");
            String fechaParam = request.getParameter("fecha");
            String costoParam = request.getParameter("costo");  // Obtener el costo

            // Validar que todos los parámetros sean válidos
            if (computadoraIdParam == null || usuarioIdParam == null || fechaParam == null || costoParam == null) {
                request.setAttribute("error", "Todos los campos son obligatorios.");
                request.getRequestDispatcher("/views/ensamblajes.jsp").forward(request, response);
                return;
            }

            int computadoraId = Integer.parseInt(computadoraIdParam);
            int usuarioId = Integer.parseInt(usuarioIdParam);
            LocalDate fecha = LocalDate.parse(fechaParam);
            BigDecimal costo = new BigDecimal(costoParam);  // Convertir el costo a BigDecimal

            ComputadoraDAO computadoraDAO = new ComputadoraDAO();
            UserDAO userDAO = new UserDAO();

            Computadora computadora = computadoraDAO.buscarComputadoraPorId(computadoraId);
            User usuario = userDAO.obtenerUsuarioPorId(usuarioId);

            if (computadora == null || usuario == null) {
                request.setAttribute("error", "Computadora o Usuario no existen.");
                request.getRequestDispatcher("/views/ensamblajes.jsp").forward(request, response);
                return;
            }

            // Crear el ensamblaje
            EnsamblarComputadora ensamblaje = new EnsamblarComputadora(0, computadora, usuario, fecha, costo);

            // Insertar el ensamblaje
            EnsamblarComputadoraDAO ensamblarComputadoraDAO = new EnsamblarComputadoraDAO();
            ensamblarComputadoraDAO.insertarEnsamblaje(ensamblaje);

            // Redirigir después de éxito
            response.sendRedirect("ensamblaje");

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Los IDs deben ser números enteros.");
            request.getRequestDispatcher("/views/ensamblajes.jsp").forward(request, response);
        } catch (DateTimeParseException e) {
            request.setAttribute("error", "Formato de fecha inválido.");
            request.getRequestDispatcher("/views/ensamblajes.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error inesperado. Inténtalo de nuevo.");
            request.getRequestDispatcher("/views/ensamblajes.jsp").forward(request, response);
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
