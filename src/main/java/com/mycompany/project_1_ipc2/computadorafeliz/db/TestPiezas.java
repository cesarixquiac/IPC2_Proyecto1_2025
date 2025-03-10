/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_1_ipc2.computadorafeliz.db;

/**
 *
 * @author cesar
 */
import com.mycompany.project_1_ipc2.computadorafeliz.models.Pieza;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestPiezas {
    
    // Este es tu método para obtener las piezas
    public static List<Pieza> obtenerTodasLasPiezas() throws ClassNotFoundException {
        List<Pieza> piezas = new ArrayList<>();
        String query = "SELECT * FROM piezas"; // Asegúrate de que la tabla y las columnas sean correctas
        
        System.out.println("Obteniendo piezas de la base de datos...");
        
        try (Connection conn = DatabaseConnection.getConnection(); // Asegúrate de tener esta clase bien configurada
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Pieza obtenida: " + rs.getInt("id_pieza") + " - " + rs.getString("nombre_pieza"));

                Pieza pieza = new Pieza();
                pieza.setId(rs.getInt("id_pieza"));
                pieza.setTipo(rs.getString("nombre_pieza"));
                pieza.setCosto(rs.getDouble("costo"));
                pieza.setCantidad(rs.getInt("cantidad"));

                piezas.add(pieza);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si no se obtuvo ninguna pieza
        if (piezas.isEmpty()) {
            System.out.println("No se encontraron piezas en la base de datos.");
        }

        return piezas;
    }

    public static void main(String[] args) {
        try {
            List<Pieza> piezas = obtenerTodasLasPiezas();  // Llamamos al método para obtener las piezas

            if (piezas != null && !piezas.isEmpty()) {
                System.out.println("Total de piezas obtenidas: " + piezas.size());
                for (Pieza pieza : piezas) {
                    System.out.println("ID: " + pieza.getId() + ", Nombre: " + pieza.getTipo() + ", Costo: " + pieza.getCosto() + ", Cantidad: " + pieza.getCantidad());
                }
            } else {
                System.out.println("No se obtuvieron piezas.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

