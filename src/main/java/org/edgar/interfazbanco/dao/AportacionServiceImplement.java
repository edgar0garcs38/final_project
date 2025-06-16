package org.edgar.interfazbanco.dao;

import org.edgar.interfazbanco.models.Aportacion;
import org.edgar.interfazbanco.util.Conexion;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AportacionServiceImplement implements AportacionService {

    @Override
    public boolean registrarAportacion(int idUsuario, double monto, String tipo) throws SQLException {
        LocalDateTime fecha = LocalDateTime.now();

        try (Connection conn = Conexion.getConnection()) {
            String sql = "INSERT INTO Aportaciones (idUsuario, Monto, Tipo, Fecha) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            stmt.setDouble(2, monto);
            stmt.setString(3, tipo);
            stmt.setTimestamp(4, Timestamp.valueOf(fecha));

            int filas = stmt.executeUpdate();
            return filas > 0;
        }
    }

    @Override
    public List<Aportacion> obtenerAportacionesPorUsuario(int idUsuario) throws SQLException {
        List<Aportacion> lista = new ArrayList<>();

        try (Connection conn = Conexion.getConnection()) {
            String sql = "SELECT * FROM Aportaciones WHERE idUsuario = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Aportacion a = new Aportacion();
                a.setId(rs.getInt("idAportacion"));
                a.setIdUsuario(rs.getInt("idUsuario"));
                a.setMonto(rs.getDouble("Monto"));
                a.setTipo(rs.getString("Tipo"));
                a.setFecha(rs.getTimestamp("Fecha").toLocalDateTime());
                lista.add(a);
            }

            rs.close();
            stmt.close();
        }

        return lista;
    }
}
