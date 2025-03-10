/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_1_ipc2.computadorafeliz.DAO;

import com.mycompany.project_1_ipc2.computadorafeliz.models.Pieza;
import com.mycompany.project_1_ipc2.computadorafeliz.db.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cesar
 */
public class PiezaDAO {

    // Método para insertar una nueva pieza
    public boolean insertarPieza(Pieza pieza) throws ClassNotFoundException {
        String query = "INSERT INTO piezas (nombre_pieza, costo, cantidad) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, pieza.getTipo());
            stmt.setDouble(2, pieza.getCosto());
            stmt.setInt(3, pieza.getCantidad()); // Agregar cantidad

            return stmt.executeUpdate() > 0; // Devuelve true si se insertó correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todas las piezas
public List<Pieza> obtenerTodasLasPiezas() throws ClassNotFoundException {
    List<Pieza> piezas = new ArrayList<>();
    String query = "SELECT * FROM piezas";
    
    
    System.out.println("Obteniendo piezas de la base de datos...");
    try (Connection conn = DatabaseConnection.getConnection(); 
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            System.out.println("Pieza obtenida: " + rs.getInt("id_pieza") + " - " + rs.getString("nombre_pieza"));

            Pieza pieza = new Pieza();
                    pieza.setId(rs.getInt("id_pieza"));
                    pieza.setTipo(rs.getString("nombre_pieza"));
                    pieza.setCosto(rs.getDouble("costo"));
                    pieza.setCantidad(rs.getInt("cantidad"));
//                rs.getInt("id_pieza"),
//                rs.getString("nombre_pieza"),
//                rs.getDouble("costo"),
//                rs.getInt("cantidad")
            
            piezas.add(pieza);
        }

    } catch (SQLException e) {
        // Captura de errores SQL, puede ser útil para depuración
        e.printStackTrace();
        // Podrías agregar un mensaje para indicar que hubo un error al obtener las piezas
    }

    // Si no se obtuvo ninguna pieza, puedes agregar un log o algún manejo extra
    if (piezas.isEmpty()) {
        System.out.println("No se encontraron piezas en la base de datos.");
    }

    return piezas; // Retorna la lista de piezas (vacía si no se encontraron piezas)
}


    // Método para actualizar una pieza
    public boolean actualizarPieza(Pieza pieza) throws ClassNotFoundException {
        String query = "UPDATE Pieza SET tipo = ?, costo = ?, cantidad = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, pieza.getTipo());
            stmt.setDouble(2, pieza.getCosto());
            stmt.setInt(3, pieza.getCantidad()); // Agregar cantidad
            stmt.setInt(4, pieza.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar una pieza
    public boolean eliminarPieza(int id) throws ClassNotFoundException {
        String query = "DELETE FROM Pieza WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener una pieza por ID
    public Pieza obtenerPiezaPorId(int id) throws ClassNotFoundException {
        String query = "SELECT * FROM Pieza WHERE id = ?";
        Pieza pieza = null;

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pieza = new Pieza(rs.getInt("id"), rs.getString("tipo"), rs.getDouble("costo"), rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieza;
    }
}
