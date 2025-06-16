package org.edgar.interfazbanco.dao;

import org.edgar.interfazbanco.models.Usuario;
import java.sql.SQLException;

public interface LoginService {
    Usuario autenticar(String cedula, String clave) throws SQLException;
}
