package org.edgar.interfazbanco.dao;

import org.edgar.interfazbanco.models.Usuario;
import org.edgar.interfazbanco.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServiceImplement implements LoginService {

    @Override
    public Usuario autenticar(String cedula, String clave) throws SQLException {
        Usuario usuario = null;

        try (Connection conn = Conexion.getConnection()) {
            String sql = "SELECT * FROM usuarios WHERE cedula = ? AND clave = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cedula);
            stmt.setString(2, clave);

            System.out.println("Ejecutando consulta de login para: " + cedula);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                //usuario.setId(rs.getInt("idUsuarios")); // Asegúrate que se llama así
                usuario.setCedula(rs.getString("cedula"));
                usuario.setClave(rs.getString("clave"));
                usuario.setRol(rs.getString("rol"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));

                System.out.println("✅ Usuario autenticado: " + usuario.getNombres() + " " + usuario.getApellidos());
            } else {
                System.out.println("❌ Usuario no encontrado con esas credenciales.");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("🚨 Error al autenticar usuario: " + e.getMessage());
            throw e;
        }

        return usuario;
    }
}
