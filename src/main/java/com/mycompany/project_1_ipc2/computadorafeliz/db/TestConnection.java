/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_1_ipc2.computadorafeliz.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cesar
 */
public class TestConnection {

    public static void main(String[] args) throws ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("¡Conexión exitosa a la base de datos!");
                
                // Prueba de consulta
                String query = "SELECT * FROM Usuarios LIMIT 1"; // Ajusta la tabla según tus necesidades
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        // Aquí extraemos los datos del usuario
                        int idUsuario = rs.getInt("id_usuario");
                        String nombreUsuario = rs.getString("nombre_usuario");
                        String tipoUsuario = rs.getString("tipo_usuario");
                        String passwordUsuario = rs.getString("password");


                        // Imprimir los datos encontrados
                        System.out.println("Consulta ejecutada correctamente.");
                        System.out.println("Usuario encontrado:");
                        System.out.println("ID: " + idUsuario);
                        System.out.println("Nombre de Usuario: " + nombreUsuario);
                        System.out.println("Password: " + passwordUsuario);
                        System.out.println("Tipo de Usuario: " + tipoUsuario);
                    } else {
                        System.out.println("No se encontraron usuarios en la base de datos.");
                    }
                }
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
            
            
            
            
        } catch (SQLException e) {
            System.out.println("Error al intentar conectar a la base de datos.");
            e.printStackTrace();
        }
    }
}


