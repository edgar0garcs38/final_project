package org.edgar.interfazbanco.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.edgar.interfazbanco.dao.UsuarioService;
import org.edgar.interfazbanco.dao.UsuarioServiceImplement;
import org.edgar.interfazbanco.models.Usuario;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/gestion-usuarios")
public class GestionUsuariosServlet extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioServiceImplement();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            request.setAttribute("usuarios", usuarios);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/admin/gestion_usuarios.jsp").forward(request, response);
    }
}
