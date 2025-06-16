package org.edgar.interfazbanco.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.edgar.interfazbanco.models.Prestamo;
import org.edgar.interfazbanco.models.Usuario;
import org.edgar.interfazbanco.service.PrestamoServiceSessionImplement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/prestamo")
public class PrestamoServlet extends HttpServlet {

    private final PrestamoServiceSessionImplement prestamoService = new PrestamoServiceSessionImplement();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);
        Usuario usuario = (Usuario) (sesion != null ? sesion.getAttribute("usuarioLogueado") : null);

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int idUsuario = usuario.getId(); // asegúrate que `Usuario` tenga `getId()`
        String tipo = request.getParameter("tipo");
        String montoStr = request.getParameter("monto");
        String plazoStr = request.getParameter("plazo");

        if (tipo == null || tipo.isEmpty() ||
                montoStr == null || montoStr.isEmpty() ||
                plazoStr == null || plazoStr.isEmpty()) {
            request.setAttribute("mensaje", "Debes completar todos los campos del formulario.");
            request.getRequestDispatcher("prestamos.jsp").forward(request, response);
            return;
        }

        try {
            double monto = Double.parseDouble(montoStr);
            int plazo = Integer.parseInt(plazoStr);

            boolean exito = prestamoService.registrarPrestamo(idUsuario, tipo, monto, plazo);

            if (exito) {
                request.setAttribute("mensaje", "Préstamo registrado correctamente.");
            } else {
                request.setAttribute("mensaje", "No se pudo registrar el préstamo.");
            }

        } catch (NumberFormatException e) {
            request.setAttribute("mensaje", "Monto o plazo inválidos.");
        } catch (SQLException e) {
            e.printStackTrace(); // Log para desarrollo
            request.setAttribute("mensaje", "Error de base de datos: " + e.getMessage());
        }

        request.getRequestDispatcher("prestamos.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);
        Usuario usuario = (Usuario) (sesion != null ? sesion.getAttribute("usuarioLogueado") : null);

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int idUsuario = usuario.getId();

        try {
            List<Prestamo> prestamos = prestamoService.obtenerPrestamosPorUsuario(idUsuario);
            request.setAttribute("listaPrestamos", prestamos);
        } catch (SQLException e) {
            e.printStackTrace(); // Logging
            request.setAttribute("mensaje", "Error al obtener préstamos: " + e.getMessage());
        }

        request.getRequestDispatcher("prestamos.jsp").forward(request, response);
    }

}
