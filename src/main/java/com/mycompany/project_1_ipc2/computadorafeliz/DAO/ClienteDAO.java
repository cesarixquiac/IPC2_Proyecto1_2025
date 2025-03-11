/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_1_ipc2.computadorafeliz.DAO;

/**
 *
 * @author cesar
 */
import com.mycompany.project_1_ipc2.computadorafeliz.models.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mycompany.project_1_ipc2.computadorafeliz.db.DatabaseConnection;

public class ClienteDAO {
    
    private Connection con;

    public ClienteDAO() throws ClassNotFoundException {
        con = DatabaseConnection.getConnection();
    }

    // Buscar cliente por NIT
    public Cliente buscarPorNIT(String nit) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Clientes WHERE nit = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nit);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                cliente = new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nit"),
                    rs.getString("nombre_cliente"),
                    rs.getString("direccion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    // Registrar un nuevo cliente
    public boolean registrarCliente(Cliente cliente) {
        String sql = "INSERT INTO Clientes (nit, nombre_cliente, direccion) VALUES (?, ?, ?)";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNit());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDireccion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}