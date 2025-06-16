package org.edgar.interfazbanco.dao;

import org.edgar.interfazbanco.models.Aportacion;

import java.sql.SQLException;
import java.util.List;

public interface AportacionService {
    boolean registrarAportacion(int idUsuario, double monto, String tipo) throws SQLException;
    List<Aportacion> obtenerAportacionesPorUsuario(int idUsuario) throws SQLException;
}
