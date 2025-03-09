/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.project_1_ipc2.computadorafeliz.servlets;

import com.mycompany.project_1_ipc2.computadorafeliz.db.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author cesar
 */

@WebServlet("/createUser")
public class CreateUserServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CreateUserServlet.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Establecer el tipo de contenido como HTML
    response.setContentType("text/html;charset=UTF-8");

    // Obtener el RequestDispatcher para la página JSP de login
    RequestDispatcher dispatcher = request.getRequestDispatcher("/createUser.jsp");
    System.out.println("CreateUserServlet ha sido llamado");

    // Redirigir la solicitud al JSP de login
    dispatcher.forward(request, response);
}

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
          
          response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<h1>CreateUserServlet ha sido llamado</h1>");
          
            LOGGER.log(Level.INFO, "CreateUserServlet ha sido llamado");
          
        String nombreUsuario = request.getParameter("nombre_usuario");
        String password = request.getParameter("password");
        String tipoUsuario = request.getParameter("tipo_usuario");
        
        System.out.println(" Datos recibidos: " + nombreUsuario + ", " + password);
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Usuarios (nombre_usuario, password, tipo_usuario) VALUES (?, ?, ?)";
            out.println("<h1>Datos</h1>"+nombreUsuario+"password"+password+"Tipousurio"+tipoUsuario);
                

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, nombreUsuario);
                stmt.setString(2, password);
                stmt.setString(3, tipoUsuario);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    response.sendRedirect("login.jsp?success=true");  // Redirige a login con mensaje de éxito
                } else {
                    response.sendRedirect("createUser.jsp?error=true");  // Redirige a crear usuario con error
                }
            }
            out.close();
        } catch (Exception e) {
             out.println("<h1>Error al insertar usuario</h1>");
             out.println("<p>" + e.getMessage() + "</p>");
             out.close();
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
