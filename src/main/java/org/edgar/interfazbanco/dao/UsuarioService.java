package org.edgar.interfazbanco.dao;

import org.edgar.interfazbanco.models.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioService {
    boolean crearUsuario(Usuario usuario) throws SQLException;
    boolean eliminarUsuario(String cedula) throws SQLException;
    List<Usuario> listarUsuarios() throws SQLException;
}
