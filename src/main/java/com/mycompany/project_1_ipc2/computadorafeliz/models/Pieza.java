package com.mycompany.project_1_ipc2.computadorafeliz.models;

public class Pieza {
    private int id_pieza;
    private String nombre_pieza;
    private double costo;
     private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Pieza(int id, String tipo, double costo, int cantidad) {
        this.id_pieza = id;
        this.nombre_pieza = tipo;
        this.costo = costo;
        this.cantidad =cantidad;
    }

    public int getId() {
        return id_pieza;
    }

    public void setId(int id) {
        this.id_pieza = id;
    }

    public String getTipo() {
        return nombre_pieza;
    }

    public void setTipo(String tipo) {
        this.nombre_pieza = tipo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
