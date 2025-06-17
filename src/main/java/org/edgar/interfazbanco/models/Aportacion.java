package org.edgar.interfazbanco.models;

import java.time.LocalDateTime;

public class Aportacion {
    private int id;
    private String cedulaUsuario; // cambiado de int a String
    private double monto;
    private LocalDateTime fecha;
    private String tipo; // Ej: "Ordinaria", "Extraordinaria"

    // Getters y setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }
    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
