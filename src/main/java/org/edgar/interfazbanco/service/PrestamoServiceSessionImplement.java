package org.edgar.interfazbanco.service;

import org.edgar.interfazbanco.dao.PrestamoService;
import org.edgar.interfazbanco.dao.PrestamoServiceImplement;
import org.edgar.interfazbanco.models.Prestamo;

import java.sql.SQLException;
import java.util.List;

public class PrestamoServiceSessionImplement {

    private final PrestamoService prestamoService = new PrestamoServiceImplement();

    public boolean registrarPrestamo(String cedulaUsuario, String tipo, double monto, int plazo) throws SQLException {
        return prestamoService.registrarPrestamo(cedulaUsuario, tipo, monto, plazo);
    }

    public List<Prestamo> obtenerPrestamosPorUsuario(String cedulaUsuario) throws SQLException {
        return prestamoService.obtenerPrestamosPorUsuario(cedulaUsuario);
    }
}