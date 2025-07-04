package org.edgar.interfazbanco.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.edgar.interfazbanco.models.ResumenFinanciero;
import org.edgar.interfazbanco.models.Usuario;
import org.edgar.interfazbanco.service.ResumenFinancieroServiceSessionImplement;

import java.io.IOException;

@WebServlet("/resumen-financiero")
public class ResumenServlet extends HttpServlet {

    private final ResumenFinancieroServiceSessionImplement resumenService = new ResumenFinancieroServiceSessionImplement();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String cedulaUsuario = usuario.getCedula(); // ✅ cambiar a String
        ResumenFinanciero resumen = resumenService.calcularResumen(cedulaUsuario);

        request.setAttribute("resumen", resumen);
        request.getRequestDispatcher("resumen.jsp").forward(request, response);
    }
}
