package org.edgar.interfazbanco.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.edgar.interfazbanco.models.Aportacion;
import org.edgar.interfazbanco.models.Usuario;
import org.edgar.interfazbanco.service.AportacionServiceSessionImplement;

import java.io.IOException;
import java.util.List;

@WebServlet("/aportaciones")
public class AportacionServlet extends HttpServlet {

    private final AportacionServiceSessionImplement aportacionService = new AportacionServiceSessionImplement();

    // Registrar nueva aportación
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String montoStr = request.getParameter("monto");
        String tipo = request.getParameter("tipo");

        if (montoStr == null || tipo == null || montoStr.isEmpty() || tipo.isEmpty()) {
            request.setAttribute("mensaje", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("aportaciones.jsp").forward(request, response);
            return;
        }

        try {
            double monto = Double.parseDouble(montoStr);
            boolean registrado = aportacionService.registrarAportacion(usuario.getId(), monto, tipo);

            if (registrado) {
                request.setAttribute("mensaje", "Aportación registrada correctamente.");
            } else {
                request.setAttribute("mensaje", "No se pudo registrar la aportación.");
            }

        } catch (NumberFormatException e) {
            request.setAttribute("mensaje", "Monto inválido.");
        }

        doGet(request, response); // Mostrar lista actualizada después del registro
    }

    // Mostrar lista de aportaciones del usuario
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Aportacion> aportaciones = aportacionService.obtenerAportacionesPorUsuario(usuario.getId());
        request.setAttribute("aportaciones", aportaciones);
        request.getRequestDispatcher("aportaciones.jsp").forward(request, response);
    }
}
