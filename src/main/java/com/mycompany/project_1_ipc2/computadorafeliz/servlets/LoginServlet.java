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
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");

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
        String nombreUsuario = request.getParameter("nombre_usuario");
        String password = request.getParameter("password");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT tipo_usuario FROM Usuarios WHERE nombre_usuario = ? AND password = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, nombreUsuario);
                stmt.setString(2, password);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String tipoUsuarioTexto = rs.getString("tipo_usuario"); // Obtiene el tipo de usuario

                        // Crear sesión y almacenar valores
                        HttpSession session = request.getSession();
                        session.setAttribute("nombreUsuario", nombreUsuario);

                         
                        switch (tipoUsuarioTexto) {
                            case "1":
                                tipoUsuarioTexto = "Fábrica";
                                break;
                            case "2":
                                tipoUsuarioTexto = "Punto de Venta";
                                break;
                            case "3":
                                tipoUsuarioTexto = "Financiero y administracion";
                                break;
                        }

//                        session.setAttribute("tipoUsuarioTexto", tipoUsuarioTexto);

                        session.setAttribute("tipoUsuario", tipoUsuarioTexto); // Almacenar tipo de usuario

                        response.sendRedirect("home.jsp"); // Redirigir al home
                    } else {
                        response.sendRedirect("login.jsp?error=true"); // Usuario o contraseña incorrectos
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=db"); // Redirigir con error de base de datos
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
