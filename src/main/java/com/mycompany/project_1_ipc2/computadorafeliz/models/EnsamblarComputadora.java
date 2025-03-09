package com.mycompany.project_1_ipc2.computadorafeliz.models;

import java.time.LocalDate;

public class EnsamblarComputadora {
    private int id;
    private Computadora computadora;
    private User usuario;
    private LocalDate fecha;

    public EnsamblarComputadora(int id, Computadora computadora, User usuario, LocalDate fecha) {
        this.id = id;
        this.computadora = computadora;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComputadoraId() {
        return computadora.getId(); // Devuelve el ID de la computadora
    }

    public void setComputadora(Computadora computadora) {
        this.computadora = computadora;
    }

    public int getUsuarioId() {
        return usuario.getId(); // Devuelve el ID del usuario
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
