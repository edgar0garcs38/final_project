package org.edgar.interfazbanco.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;

import org.edgar.interfazbanco.dao.UsuarioService;
import org.edgar.interfazbanco.dao.UsuarioServiceImplement;
import org.edgar.interfazbanco.models.Usuario;

import java.io.IOException;

@WebServlet("/crear-usuario")
public class CrearUsuarioServlet extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioServiceImplement();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cedula = request.getParameter("cedula");
        String clave = request.getParameter("clave");
        String rol = request.getParameter("rol");
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");

        Usuario u = new Usuario();
        u.setCedula(cedula);
        u.setClave(clave);
        u.setRol(rol);
        u.setNombres(nombres);
        u.setApellidos(apellidos);

        try {
            usuarioService.crearUsuario(u);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("admin/gestion_usuarios.jsp");
    }
}
