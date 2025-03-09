/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.project_1_ipc2.computadorafeliz.servlets;

import com.mycompany.project_1_ipc2.computadorafeliz.DAO.EnsamblarComputadoraDAO;
import com.mycompany.project_1_ipc2.computadorafeliz.models.EnsamblarComputadora;
import com.mycompany.project_1_ipc2.computadorafeliz.models.Computadora;
import com.mycompany.project_1_ipc2.computadorafeliz.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;


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
            request.setAttribute("ensamblajes", ensamblarComputadoraDAO.obtenerTodosLosEnsamblajes());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/ensamblajes.jsp").forward(request, response); // Mostrar en JSP
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
        // Procesar el formulario para insertar un ensamblaje
        try {
            int computadoraId = Integer.parseInt(request.getParameter("computadora_id"));
            int usuarioId = Integer.parseInt(request.getParameter("usuario_id"));
            LocalDate fecha = LocalDate.parse(request.getParameter("fecha"));

            // Crear los objetos necesarios
            Computadora computadora = new Computadora(computadoraId, "Computadora ejemplo", 1000.0);  // Asegúrate de obtenerlo de alguna parte
            User usuario = new User("UsuarioEjemplo", "password", "admin");  // Lo mismo, obtener de alguna parte

            // Crear el ensamblaje
            EnsamblarComputadora ensamblaje = new EnsamblarComputadora(0, computadora, usuario, fecha); // El id es 0 porque lo genera la base de datos

            // Insertar el ensamblaje
            ensamblarComputadoraDAO.insertarEnsamblaje(ensamblaje);

            response.sendRedirect("ensamblaje"); // Redirigir después de insertar
        } catch (Exception e) {
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
