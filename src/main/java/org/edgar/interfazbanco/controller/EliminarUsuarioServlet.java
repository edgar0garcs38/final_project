package org.edgar.interfazbanco.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;

import org.edgar.interfazbanco.dao.UsuarioService;
import org.edgar.interfazbanco.dao.UsuarioServiceImplement;

import java.io.IOException;

@WebServlet("/eliminar-usuario")
public class EliminarUsuarioServlet extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioServiceImplement();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cedula = request.getParameter("cedula");

        try {
            usuarioService.eliminarUsuario(cedula);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("admin/gestion-usuarios?mensaje=eliminado");
    }
}
