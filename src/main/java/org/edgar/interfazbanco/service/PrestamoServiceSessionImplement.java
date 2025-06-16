package org.edgar.interfazbanco.service;

import org.edgar.interfazbanco.dao.PrestamoService;
import org.edgar.interfazbanco.dao.PrestamoServiceImplement;
import org.edgar.interfazbanco.models.Prestamo;

import java.sql.SQLException;
import java.util.List;

public class PrestamoServiceSessionImplement {

    private final PrestamoService prestamoService;

    public PrestamoServiceSessionImplement() {
        this.prestamoService = new PrestamoServiceImplement();
    }

    public boolean registrarPrestamo(int idUsuario, String tipo, double monto, int plazo) throws SQLException {
        return prestamoService.registrarPrestamo(idUsuario, tipo, monto, plazo);
    }

    public List<Prestamo> obtenerPrestamosPorUsuario(int idUsuario) throws SQLException {
        return prestamoService.obtenerPrestamosPorUsuario(idUsuario);
    }
}
