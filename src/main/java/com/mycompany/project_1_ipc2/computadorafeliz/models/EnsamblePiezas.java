package com.mycompany.project_1_ipc2.computadorafeliz.models;

public class EnsamblePiezas {
    private int id;
    private Computadora computadora;
    private Pieza pieza;
    private int cantidad;

    public EnsamblePiezas(int id, Computadora computadora, Pieza pieza, int cantidad) {
        this.id = id;
        this.computadora = computadora;
        this.pieza = pieza;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Computadora getComputadora() {
        return computadora;
    }

    public void setComputadora(Computadora computadora) {
        this.computadora = computadora;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
