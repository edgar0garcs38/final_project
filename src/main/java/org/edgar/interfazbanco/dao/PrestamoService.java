package org.edgar.interfazbanco.dao;

import org.edgar.interfazbanco.models.Prestamo;

import java.sql.SQLException;
import java.util.List;

public interface PrestamoService {
    boolean registrarPrestamo(int idUsuario, String tipo, double monto, int plazo) throws SQLException;
    List<Prestamo> obtenerPrestamosPorUsuario(int idUsuario) throws SQLException;
}
