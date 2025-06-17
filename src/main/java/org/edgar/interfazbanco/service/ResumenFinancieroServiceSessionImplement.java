package org.edgar.interfazbanco.service;

import org.edgar.interfazbanco.dao.AportacionService;
import org.edgar.interfazbanco.dao.AportacionServiceImplement;
import org.edgar.interfazbanco.dao.PrestamoService;
import org.edgar.interfazbanco.dao.PrestamoServiceImplement;
import org.edgar.interfazbanco.models.Aportacion;
import org.edgar.interfazbanco.models.Prestamo;
import org.edgar.interfazbanco.models.ResumenFinanciero;

import java.sql.SQLException;
import java.util.List;

public class ResumenFinancieroServiceSessionImplement {

    private final AportacionService aportacionDao = new AportacionServiceImplement();
    private final PrestamoService prestamoDao = new PrestamoServiceImplement();

    public ResumenFinanciero calcularResumen(String cedulaUsuario) {
        ResumenFinanciero resumen = new ResumenFinanciero();

        try {
            // Aportaciones
            List<Aportacion> aportaciones = aportacionDao.obtenerAportacionesPorUsuario(cedulaUsuario);
            double totalAportado = aportaciones.stream().mapToDouble(Aportacion::getMonto).sum();
            resumen.setTotalAportado(totalAportado);

            // Préstamos
            List<Prestamo> prestamos = prestamoDao.obtenerPrestamosPorUsuario(cedulaUsuario);
            double totalPrestamos = prestamos.stream().mapToDouble(Prestamo::getMonto).sum();
            double totalInteres = prestamos.stream().mapToDouble(Prestamo::getInteres).sum();
            resumen.setTotalPrestamos(totalPrestamos);
            resumen.setTotalInteres(totalInteres);
            resumen.setCantidadPrestamos(prestamos.size());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resumen;
    }
}
