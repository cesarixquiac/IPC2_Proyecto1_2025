package com.mycompany.project_1_ipc2.computadorafeliz.models;

public class VentaDetalle {
    private int id;
    private Venta venta;
    private Computadora computadora;
    private int cantidad;
    private double precioUnitario;

    public VentaDetalle(int id, Venta venta, Computadora computadora, int cantidad, double precioUnitario) {
        this.id = id;
        this.venta = venta;
        this.computadora = computadora;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Computadora getComputadora() {
        return computadora;
    }

    public void setComputadora(Computadora computadora) {
        this.computadora = computadora;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
