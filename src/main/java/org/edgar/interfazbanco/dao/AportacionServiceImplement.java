package org.edgar.interfazbanco.dao;

import org.edgar.interfazbanco.models.Aportacion;
import org.edgar.interfazbanco.util.Conexion;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AportacionServiceImplement implements AportacionService {

    @Override
    public boolean registrarAportacion(String cedulaUsuario, double monto, String tipo) throws SQLException {
        LocalDateTime fecha = LocalDateTime.now();

        try (Connection conn = Conexion.getConnection()) {
            String sql = "INSERT INTO Aportaciones (idUsuario, Monto, Tipo, Fecha) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cedulaUsuario); // ✅ ahora se usa String
            stmt.setDouble(2, monto);
            stmt.setString(3, tipo);
            stmt.setTimestamp(4, Timestamp.valueOf(fecha));

            int filas = stmt.executeUpdate();
            return filas > 0;
        }
    }

    @Override
    public List<Aportacion> obtenerAportacionesPorUsuario(String cedulaUsuario) throws SQLException {
        List<Aportacion> lista = new ArrayList<>();

        try (Connection conn = Conexion.getConnection()) {
            String sql = "SELECT * FROM Aportaciones WHERE idUsuario = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cedulaUsuario); // ✅ ahora se usa String
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Aportacion a = new Aportacion();
                a.setId(rs.getInt("idAportacion"));
                a.setCedulaUsuario(rs.getString("idUsuario")); // ✅ nuevo setter
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
