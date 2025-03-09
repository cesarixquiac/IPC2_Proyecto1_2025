/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_1_ipc2.computadorafeliz.DAO;

/**
 *
 * @author cesar
 */
import com.mycompany.project_1_ipc2.computadorafeliz.db.DatabaseConnection;
import com.mycompany.project_1_ipc2.computadorafeliz.models.Computadora;
import com.mycompany.project_1_ipc2.computadorafeliz.models.EnsamblarComputadora;
import com.mycompany.project_1_ipc2.computadorafeliz.models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnsamblarComputadoraDAO {

    public void insertarEnsamblaje(EnsamblarComputadora ensamblaje) throws ClassNotFoundException {
        String query = "INSERT INTO EnsamblarComputadora (computadora_id, usuario_id, fecha) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Establecer los parámetros del PreparedStatement usando los métodos de los modelos
        stmt.setInt(1, ensamblaje.getComputadoraId()); // ID de la computadora
        stmt.setInt(2, ensamblaje.getUsuarioId()); // ID del usuario
        stmt.setDate(3, java.sql.Date.valueOf(ensamblaje.getFecha())); // Fecha

        stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public List<EnsamblarComputadora> obtenerTodosLosEnsamblajes() throws ClassNotFoundException {
    List<EnsamblarComputadora> ensamblajes = new ArrayList<>();
    String query = "SELECT e.id, e.computadora_id, e.usuario_id, e.fecha, c.nombre AS computadora_nombre, c.precio, u.nombreUsuario, u.password, u.tipoUsuario " +
                   "FROM EnsamblarComputadora e " +
                   "JOIN Computadora c ON e.computadora_id = c.id " +
                   "JOIN Usuario u ON e.usuario_id = u.id";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            // Obtener los datos de la computadora
            Computadora computadora = new Computadora(
                    rs.getInt("computadora_id"),
                    rs.getString("computadora_nombre"),
                    rs.getDouble("precio")
            );

            // Obtener los datos del usuario
            User usuario = new User(
                    rs.getString("nombreUsuario"),
                    rs.getString("password"),
                    rs.getString("tipoUsuario")
            );

            // Crear el objeto EnsamblarComputadora
            EnsamblarComputadora ensamblaje = new EnsamblarComputadora(
                    rs.getInt("id"),
                    computadora,  // Asignar el objeto Computadora completo
                    usuario,      // Asignar el objeto User completo
                    rs.getDate("fecha").toLocalDate()
            );
            ensamblajes.add(ensamblaje);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ensamblajes;
}



public EnsamblarComputadora obtenerEnsamblajePorId(int id) throws ClassNotFoundException {
    String query = "SELECT e.id, e.computadora_id, e.usuario_id, e.fecha, c.nombre AS computadora_nombre, c.precio, u.nombreUsuario, u.password, u.tipoUsuario " +
                   "FROM EnsamblarComputadora e " +
                   "JOIN Computadora c ON e.computadora_id = c.id " +
                   "JOIN Usuario u ON e.usuario_id = u.id " +
                   "WHERE e.id = ?";

    EnsamblarComputadora ensamblaje = null;

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, id);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Obtener los datos de la computadora
                Computadora computadora = new Computadora(
                        rs.getInt("computadora_id"),
                        rs.getString("computadora_nombre"),
                        rs.getDouble("precio")
                );

                // Obtener los datos del usuario
                User usuario = new User(
                        rs.getString("nombreUsuario"),
                        rs.getString("password"),
                        rs.getString("tipoUsuario")
                );

                // Crear el objeto EnsamblarComputadora
                ensamblaje = new EnsamblarComputadora(
                        rs.getInt("id"),
                        computadora,  // Asignar el objeto Computadora completo
                        usuario,      // Asignar el objeto User completo
                        rs.getDate("fecha").toLocalDate()
                );
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ensamblaje;
}


    public void eliminarEnsamblaje(int id) throws ClassNotFoundException {
        String query = "DELETE FROM EnsamblarComputadora WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarEnsamblaje(EnsamblarComputadora ensamblaje) throws ClassNotFoundException {
    String query = "UPDATE EnsamblarComputadora SET computadora_id = ?, usuario_id = ?, fecha = ? WHERE id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        // Establecer los parámetros del PreparedStatement usando los métodos de los modelos
        stmt.setInt(1, ensamblaje.getComputadoraId()); // ID de la computadora
        stmt.setInt(2, ensamblaje.getUsuarioId()); // ID del usuario
        stmt.setDate(3, java.sql.Date.valueOf(ensamblaje.getFecha())); // Fecha
        stmt.setInt(4, ensamblaje.getId()); // ID del ensamblaje

        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    
}

