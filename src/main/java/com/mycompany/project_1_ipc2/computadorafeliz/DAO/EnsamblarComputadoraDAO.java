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
    String query = "INSERT INTO computadorasensambladas (id_computadora, id_usuario, fecha_ensamblaje, costo_total) VALUES (?, ?, ?, ?)";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        // Establecer los parámetros del PreparedStatement usando los métodos de los modelos
        int computadoraId = ensamblaje.getComputadoraId();
        int usuarioId = ensamblaje.getUsuarioId();
        java.sql.Date fecha = java.sql.Date.valueOf(ensamblaje.getFecha());
        java.math.BigDecimal costoTotal = ensamblaje.getCosto();

        // Agregar los prints para verificar los valores
        System.out.println("Computadora ID: " + computadoraId);
        System.out.println("Usuario ID: " + usuarioId);
        System.out.println("Fecha de Ensamblaje: " + fecha);
        System.out.println("Costo Total: " + costoTotal);

        stmt.setInt(1, computadoraId); // ID de la computadora
        stmt.setInt(2, usuarioId); // ID del usuario
        stmt.setDate(3, fecha); // Fecha
        stmt.setBigDecimal(4, costoTotal); // Costo total

        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


  public List<EnsamblarComputadora> obtenerTodosLosEnsamblajes() throws ClassNotFoundException {
    List<EnsamblarComputadora> ensamblajes = new ArrayList<>();
    String query = "SELECT e.id_computadora_ensamblada, e.id_computadora, e.id_usuario, e.fecha_ensamblaje, e.costo_total, " +
                   "c.nombre_computadora AS computadora_nombre, c.precio_venta, u.nombre_usuario, u.password, u.tipo_usuario " +
                   "FROM computadorasensambladas e " +
                   "JOIN computadoras c ON e.id_computadora = c.id_computadora " +  // Asegurarse de que la columna de relación es 'id_computadora'
                   "JOIN usuarios u ON e.id_usuario = u.id_usuario"; // Asegurarse de que la columna de relación es 'id_usuario'

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            // Obtener los datos de la computadora
            Computadora computadora = new Computadora(
                    rs.getInt("id_computadora"), // Asignar el id de la computadora
                    rs.getString("computadora_nombre"), // Nombre de la computadora
                    rs.getDouble("precio_venta") // Precio de la computadora
            );

            // Obtener los datos del usuario
            User usuario = new User(
                    rs.getInt("id_usuario"), // Asignar el id del usuario
                    rs.getString("nombre_usuario"), // Nombre del usuario
                    rs.getString("password"), // Contraseña del usuario
                    rs.getString("tipo_usuario") // Tipo de usuario
            );

            // Crear el objeto EnsamblarComputadora
            EnsamblarComputadora ensamblaje = new EnsamblarComputadora(
                    rs.getInt("id_computadora_ensamblada"), // Asignar el id del ensamblaje
                    computadora,  // Asignar el objeto Computadora completo
                    usuario,      // Asignar el objeto User completo
                    rs.getDate("fecha_ensamblaje").toLocalDate(), // Asignar la fecha de ensamblaje
                    rs.getBigDecimal("costo_total") // Asignar el costo total
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
                                            rs.getInt("id_usuario"),
                        rs.getString("nombreUsuario"),
                        rs.getString("password"),
                        rs.getString("tipoUsuario")
                );

                // Crear el objeto EnsamblarComputadora
             ensamblaje = new EnsamblarComputadora(
                    rs.getInt("id_usuario"),
                    computadora,  // Asignar el objeto Computadora completo
                    usuario,      // Asignar el objeto User completo
                    rs.getDate("fecha_ensamblaje").toLocalDate(),
                    rs.getBigDecimal("costo_total")
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

