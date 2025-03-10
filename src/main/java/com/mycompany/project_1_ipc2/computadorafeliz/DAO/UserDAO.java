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
import com.mycompany.project_1_ipc2.computadorafeliz.models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Método para obtener todos los usuarios
    public List<User> obtenerTodosLosUsuarios() throws ClassNotFoundException {
        List<User> usuarios = new ArrayList<>();
        String query = "SELECT * FROM Usuario";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User usuario = new User(
                                            rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("password"),
                        rs.getString("tipo")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    // Método para obtener un usuario por su ID
    public User obtenerUsuarioPorId(int id) throws ClassNotFoundException {
        String query = "SELECT * FROM Usuarios WHERE id_usuario = ?";
        User usuario = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new User(
                            rs.getInt("id_usuario"),
                            rs.getString("nombre_usuario"),
                            rs.getString("password"),
                            rs.getString("tipo_usuario")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    // Método para insertar un nuevo usuario
    public void insertarUsuario(User usuario) throws ClassNotFoundException {
        String query = "INSERT INTO Usuario (nombre, password, tipo) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getTipoUsuario());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un usuario
    public void actualizarUsuario(User usuario, int id) throws ClassNotFoundException {
        String query = "UPDATE Usuario SET nombre = ?, password = ?, tipo = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getTipoUsuario());
            stmt.setInt(4, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(int id) throws ClassNotFoundException {
        String query = "DELETE FROM Usuario WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

