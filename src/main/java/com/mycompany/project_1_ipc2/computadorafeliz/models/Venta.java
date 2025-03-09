package com.mycompany.project_1_ipc2.computadorafeliz.models;

import java.time.LocalDateTime;

public class Venta {
    private int id;
    private Cliente cliente;
    private User usuario;
    private LocalDateTime fecha;
    private double total;

    public Venta(int id, Cliente cliente, User usuario, LocalDateTime fecha, double total) {
        this.id = id;
        this.cliente = cliente;
        this.usuario = usuario;
        this.fecha = fecha;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
