package org.edgar.interfazbanco.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.edgar.interfazbanco.models.Usuario;
import org.edgar.interfazbanco.service.LoginServiceSessionImplement;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final LoginServiceSessionImplement loginService = new LoginServiceSessionImplement();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cedula = request.getParameter("cedula");
        String clave = request.getParameter("clave");
        System.out.println("Intentando login: " + cedula + " / " + clave);



        if (cedula == null || clave == null || cedula.isEmpty() || clave.isEmpty()) {
            request.setAttribute("error", "Debe ingresar su cédula y clave.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try {
            Usuario usuario = loginService.login(cedula, clave);

            if (usuario != null) {
                HttpSession sesion = request.getSession(true);
                sesion.setAttribute("usuarioLogueado", usuario);

                // Redirige según el rol (opcional)
                switch (usuario.getRol()) {
                    case "admin":
                        response.sendRedirect("admin/dashboard.jsp");
                        break;
                    case "cliente":
                        response.sendRedirect("cliente/dashboard.jsp");
                        break;
                    case "socio":
                        response.sendRedirect("socio/dashboard.jsp");
                        break;
                    default:
                        response.sendRedirect("Login.jsp");
                        break;
                }

            } else {
                request.setAttribute("error", "Credenciales incorrectas.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error interno del servidor.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}
