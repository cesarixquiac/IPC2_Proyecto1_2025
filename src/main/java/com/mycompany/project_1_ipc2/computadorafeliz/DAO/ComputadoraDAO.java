/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_1_ipc2.computadorafeliz.DAO;

/**
 *
 * @author cesar
 */

import com.mycompany.project_1_ipc2.computadorafeliz.models.Computadora;
import com.mycompany.project_1_ipc2.computadorafeliz.db.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComputadoraDAO {

    public void agregarComputadora(Computadora computadora) throws ClassNotFoundException {
        String query = "INSERT INTO Computadora (nombre, precio) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, computadora.getNombre());
            stmt.setDouble(2, computadora.getPrecio());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Computadora> obtenerComputadoras() throws ClassNotFoundException {
        List<Computadora> computadoras = new ArrayList<>();
        String query = "SELECT * FROM Computadora";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Computadora computadora = new Computadora(
                        rs.getInt("id_computadora"),
                        rs.getString("nombre_computadora"),
                        rs.getDouble("precio_venta")
                );
                computadoras.add(computadora);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return computadoras;
    }

    public Computadora buscarComputadoraPorId(int id) throws ClassNotFoundException {
        String query = "SELECT * FROM computadoras WHERE id_computadora = ?";
        Computadora computadora = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                computadora = new Computadora(
                        rs.getInt("id_computadora"),
                        rs.getString("nombre_computadora"),
                        rs.getDouble("precio_venta")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return computadora;
    }

    public void actualizarComputadora(Computadora computadora) throws ClassNotFoundException {
        String query = "UPDATE Computadora SET nombre = ?, precio = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, computadora.getNombre());
            stmt.setDouble(2, computadora.getPrecio());
            stmt.setInt(3, computadora.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarComputadora(int id) throws ClassNotFoundException {
        String query = "DELETE FROM Computadora WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
