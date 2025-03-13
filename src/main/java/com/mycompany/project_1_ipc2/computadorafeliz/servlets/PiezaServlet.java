/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.project_1_ipc2.computadorafeliz.servlets;

import com.mycompany.project_1_ipc2.computadorafeliz.DAO.PiezaDAO;
import com.mycompany.project_1_ipc2.computadorafeliz.models.Pieza;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cesar
 */
public class PiezaServlet extends HttpServlet {
    
    private PiezaDAO piezaDAO = new PiezaDAO();
    

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
    String action = request.getParameter("action");

    if ("delete".equals(action)) {
        try {
            int idPieza = Integer.parseInt(request.getParameter("id"));
            boolean eliminada = piezaDAO.eliminarPieza(idPieza);
            
            if (eliminada) {
                System.out.println("Pieza eliminada con éxito. ID: " + idPieza);
            } else {
                System.out.println("Error al eliminar la pieza con ID: " + idPieza);
            }

            response.sendRedirect("PiezaServlet"); // Redirigir después de eliminar
            return;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de pieza inválido");
            return;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PiezaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Si no es delete, seguimos con la obtención normal de piezas
    try {
        List<Pieza> piezas = piezaDAO.obtenerTodasLasPiezas();
        request.setAttribute("piezas", piezas != null ? piezas : new ArrayList<>());
        request.getRequestDispatcher("piezas.jsp").forward(request, response);
    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener las piezas");
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
             response.setContentType("text/html"); // Establecer el tipo de contenido a HTML
    PrintWriter out = response.getWriter();
    out.println("<html><body>");
    out.println("<h1>El método doPost se ha llamado correctamente.</h1>");
    out.println("<p>Este es un mensaje de verificación.</p>");
    out.println("</body></html>");
            String nombre = request.getParameter("tipo");
            double precio = Double.parseDouble(request.getParameter("costo"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));

            Pieza pieza = new Pieza(0, nombre, precio, cantidad); // ID generado automáticamente por la base de datos
            piezaDAO.insertarPieza(pieza);
//             if (piezaDAO.insertarPieza(pieza)) {
//    out.println("<html><body>");
//    out.println("<h1>Inserción Exitosa</h1>");
//    out.println("<p>Pieza " + nombre + " insertada correctamente.</p>");
//    out.println("</body></html>");
//} else {
//    out.println("<html><body>");
//    out.println("<h1>Error en la inserción</h1>");
//    out.println("<p>No se pudo insertar la pieza.</p>");
//    out.println("</body></html>");
//}
            

//             Redirigir después de agregar la pieza
            response.sendRedirect("PiezaServlet");
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
