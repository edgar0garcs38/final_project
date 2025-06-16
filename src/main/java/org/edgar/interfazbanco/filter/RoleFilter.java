package org.edgar.interfazbanco.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.edgar.interfazbanco.models.Usuario;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = {"/admin/*", "/cliente/*", "/socio/*", "/gestion-usuarios"})
public class RoleFilter implements Filter {

    private final Map<String, String> restricciones = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        // Define rutas y el rol requerido
        restricciones.put("/admin", "admin");
        restricciones.put("/gestion-usuarios", "admin");
        restricciones.put("/cliente", "cliente");
        restricciones.put("/socio", "socio");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuarioLogueado") : null;

        if (usuario == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();

        for (Map.Entry<String, String> entry : restricciones.entrySet()) {
            if (uri.startsWith(contextPath + entry.getKey())) {
                if (!usuario.getRol().equalsIgnoreCase(entry.getValue())) {
                    res.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado");
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
