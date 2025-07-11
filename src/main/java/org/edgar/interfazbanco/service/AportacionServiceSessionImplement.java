package org.edgar.interfazbanco.service;

import org.edgar.interfazbanco.dao.AportacionService;
import org.edgar.interfazbanco.dao.AportacionServiceImplement;
import org.edgar.interfazbanco.models.Aportacion;

import java.sql.SQLException;
import java.util.List;

public class AportacionServiceSessionImplement {

    private final AportacionService aportacionDao;

    public AportacionServiceSessionImplement() {
        this.aportacionDao = new AportacionServiceImplement();
    }

    public boolean registrarAportacion(String cedulaUsuario, double monto, String tipo) {
        try {
            return aportacionDao.registrarAportacion(cedulaUsuario, monto, tipo);
        } catch (SQLException e) {
            e.printStackTrace(); // Se puede mejorar con logs
            return false;
        }
    }

    public List<Aportacion> obtenerAportacionesPorUsuario(String cedulaUsuario) {
        try {
            return aportacionDao.obtenerAportacionesPorUsuario(cedulaUsuario);
        } catch (SQLException e) {
            e.printStackTrace(); // Se puede mejorar con logs
            return null;
        }
    }
}
