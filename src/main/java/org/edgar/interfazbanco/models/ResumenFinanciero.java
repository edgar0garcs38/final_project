package org.edgar.interfazbanco.models;

public class ResumenFinanciero {
    private double totalAportado;
    private double totalPrestamos;
    private double totalInteres;
    private int cantidadPrestamos;

    // Getters y Setters
    public double getTotalAportado() {
        return totalAportado;
    }

    public void setTotalAportado(double totalAportado) {
        this.totalAportado = totalAportado;
    }

    public double getTotalPrestamos() {
        return totalPrestamos;
    }

    public void setTotalPrestamos(double totalPrestamos) {
        this.totalPrestamos = totalPrestamos;
    }

    public double getTotalInteres() {
        return totalInteres;
    }

    public void setTotalInteres(double totalInteres) {
        this.totalInteres = totalInteres;
    }

    public int getCantidadPrestamos() {
        return cantidadPrestamos;
    }

    public void setCantidadPrestamos(int cantidadPrestamos) {
        this.cantidadPrestamos = cantidadPrestamos;
    }
}
