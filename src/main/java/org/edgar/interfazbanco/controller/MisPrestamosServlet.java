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

@WebServlet("/mis-prestamos")
public class MisPrestamosServlet extends HttpServlet {

    private final PrestamoServiceSessionImplement prestamoService = new PrestamoServiceSessionImplement();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);
        Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            String cedulaUsuario = usuario.getCedula();
            List<Prestamo> prestamos = prestamoService.obtenerPrestamosPorUsuario(cedulaUsuario);

            request.setAttribute("prestamos", prestamos);
            request.getRequestDispatcher("mis_prestamos.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "No se pudieron cargar los préstamos.");
            request.getRequestDispatcher("mis_prestamos.jsp").forward(request, response);
        }
    }
}
