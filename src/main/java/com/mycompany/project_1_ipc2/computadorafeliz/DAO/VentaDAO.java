/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project_1_ipc2.computadorafeliz.DAO;

/**
 *
 * @author cesar
 */
import com.mycompany.project_1_ipc2.computadorafeliz.models.Venta;
import com.mycompany.project_1_ipc2.computadorafeliz.db.DatabaseConnection;
import com.mycompany.project_1_ipc2.computadorafeliz.models.Cliente;
import com.mycompany.project_1_ipc2.computadorafeliz.models.User;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    
    private Connection con;

    public VentaDAO() throws ClassNotFoundException {
        con = DatabaseConnection.getConnection();
    }

    // Registrar una venta y devolver su ID generado
   public int registrarVenta(Venta venta) {
    String sql = "INSERT INTO Ventas (id_cliente, id_usuario, fecha_venta, total_venta) VALUES (?, ?, NOW(), ?)";
    int ventaId = -1;

    try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setInt(1, venta.getCliente().getId());  // Obtener el ID del cliente
        ps.setInt(2, venta.getUsuario().getId());  // Obtener el ID del usuario
        ps.setDouble(3, venta.getTotal());

        int affectedRows = ps.executeUpdate();
        if (affectedRows > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ventaId = rs.getInt(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ventaId;
}


    // Registrar un detalle de venta
    public boolean registrarDetalleVenta(int ventaId, int computadoraId, int cantidad, double precioUnitario) {
        String sql = "INSERT INTO detalleventas (id_venta , id_computadora_ensamblada, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ventaId);
            ps.setInt(2, computadoraId);
            ps.setInt(3, cantidad);
            ps.setDouble(4, precioUnitario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
public List<Venta> obtenerHistorialComprasPorCliente(int clienteId) throws ClassNotFoundException {
    List<Venta> compras = new ArrayList<>();
    String sql = "SELECT v.id_venta, v.fecha_venta, v.total_venta FROM ventas v WHERE v.id_cliente = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, clienteId);
        System.out.println("Ejecutando consulta: " + stmt.toString()); // Para ver la consulta con el ID

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int idVenta = rs.getInt("id_venta");
            LocalDateTime fecha = rs.getTimestamp("fecha_venta").toLocalDateTime();
            double total = rs.getDouble("total_venta");

            System.out.println("Venta encontrada - ID: " + idVenta + ", Fecha: " + fecha + ", Total: " + total);

            compras.add(new Venta(idVenta, null, null, fecha, total));
        }

        if (compras.isEmpty()) {
            System.out.println("No se encontraron compras para el cliente con ID: " + clienteId);
        }

    } catch (SQLException ex) {
        System.out.println("Error al obtener historial de compras: " + ex.getMessage());
        ex.printStackTrace();
    }

    return compras;
}





    
    
}
