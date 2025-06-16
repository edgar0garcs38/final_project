package org.edgar.interfazbanco.dao;

import org.edgar.interfazbanco.models.Usuario;
import org.edgar.interfazbanco.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioServiceImplement implements UsuarioService {

    @Override
    public boolean crearUsuario(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuarios (cedula, clave, rol, nombres, apellidos) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getCedula());
            stmt.setString(2, u.getClave());
            stmt.setString(3, u.getRol());
            stmt.setString(4, u.getNombres());
            stmt.setString(5, u.getApellidos());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminarUsuario(String cedula) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE cedula = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cedula);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setCedula(rs.getString("cedula"));
                u.setClave(rs.getString("clave"));
                u.setRol(rs.getString("rol"));
                u.setNombres(rs.getString("nombres"));
                u.setApellidos(rs.getString("apellidos"));
                usuarios.add(u);
            }
        }
        return usuarios;
    }
}
