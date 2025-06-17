package org.edgar.interfazbanco.dao;

import org.edgar.interfazbanco.models.Aportacion;

import java.sql.SQLException;
import java.util.List;

public interface AportacionService {
    boolean registrarAportacion(String cedulaUsuario, double monto, String tipo) throws SQLException;
    List<Aportacion> obtenerAportacionesPorUsuario(String cedulaUsuario) throws SQLException;
}
