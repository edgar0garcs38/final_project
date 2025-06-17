package org.edgar.interfazbanco.dao;

import org.edgar.interfazbanco.models.Prestamo;
import org.edgar.interfazbanco.util.Conexion;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PrestamoServiceImplement implements PrestamoService {

    @Override
    public boolean registrarPrestamo(String cedulaUsuario, String tipo, double monto, int plazo) throws SQLException {
        double interes = switch (tipo) {
            case "Consumo" -> 0.08;
            case "Educativo" -> 0.05;
            case "Emergente" -> 0.10;
            default -> 0.07;
        };

        LocalDateTime fechaInicio = LocalDateTime.now();
        LocalDateTime fechaFin = fechaInicio.plusMonths(plazo);
        String estado = "Pendiente";

        String sql = "INSERT INTO Prestamos (idUsuarios, Tipo_Prestamo, Monto, Interes, Fecha_Inicio, Fecha_Fin, Estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cedulaUsuario);
            stmt.setString(2, tipo);
            stmt.setDouble(3, monto);
            stmt.setDouble(4, interes);
            stmt.setTimestamp(5, Timestamp.valueOf(fechaInicio));
            stmt.setTimestamp(6, Timestamp.valueOf(fechaFin));
            stmt.setString(7, estado);

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<Prestamo> obtenerPrestamosPorUsuario(String cedulaUsuario) throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();

        String sql = "SELECT * FROM Prestamos WHERE idUsuarios = ? ORDER BY Fecha_Inicio DESC";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cedulaUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Prestamo p = new Prestamo();
                    p.setId(rs.getInt("idPrestamo"));
                    p.setCedulaUsuario(rs.getString("idUsuarios"));
                    p.setTipo(rs.getString("Tipo_Prestamo"));
                    p.setMonto(rs.getDouble("Monto"));
                    p.setInteres(rs.getDouble("Interes"));
                    p.setFechaInicio(rs.getTimestamp("Fecha_Inicio").toLocalDateTime());
                    p.setFechaFin(rs.getTimestamp("Fecha_Fin").toLocalDateTime());
                    p.setEstado(rs.getString("Estado"));
                    prestamos.add(p);
                }
            }
        }

        return prestamos;
    }
}
