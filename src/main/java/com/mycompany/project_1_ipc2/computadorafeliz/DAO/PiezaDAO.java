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

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

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

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pieza pieza = new Pieza(rs.getInt("id_pieza"), rs.getString("nombre_pieza"), rs.getDouble("costo"), rs.getInt("cantidad"));
                piezas.add(pieza);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piezas;
    }

    // Método para actualizar una pieza
    public boolean actualizarPieza(Pieza pieza) throws ClassNotFoundException {
        String query = "UPDATE Pieza SET tipo = ?, costo = ?, cantidad = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

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

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

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

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

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


