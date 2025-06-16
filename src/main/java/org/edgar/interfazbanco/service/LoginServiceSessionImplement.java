package org.edgar.interfazbanco.service;

import org.edgar.interfazbanco.dao.LoginService;
import org.edgar.interfazbanco.dao.LoginServiceImplement;
import org.edgar.interfazbanco.models.Usuario;

import java.sql.SQLException;

public class LoginServiceSessionImplement {

    private final LoginService loginService;

    public LoginServiceSessionImplement() {
        this.loginService = new LoginServiceImplement();
    }

    /**
     * Autentica al usuario llamando al DAO
     * @param cedula Cédula ingresada
     * @param clave Clave ingresada
     * @return Usuario autenticado o null si no existe
     * @throws SQLException en caso de error de conexión
     */
    public Usuario login(String cedula, String clave) throws SQLException {
        return loginService.autenticar(cedula, clave);
    }
}
