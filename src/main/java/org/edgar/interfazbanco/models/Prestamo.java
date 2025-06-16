package org.edgar.interfazbanco.models;

import java.time.LocalDateTime;

public class Prestamo {
    private int id;
    private int idUsuario;
    private String tipo;
    private double monto;
    private double interes;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estado;

    public Prestamo (){

    }

    public Prestamo (int id, int idUsuario, String tipo, double monto, double interes, LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.monto = monto;
        this.interes = interes;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
