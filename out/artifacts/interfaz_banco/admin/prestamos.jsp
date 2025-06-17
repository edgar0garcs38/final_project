<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.edgar.interfazbanco.models.Prestamo" %>
<%@ page import="org.edgar.interfazbanco.models.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuario == null || !"admin".equalsIgnoreCase(usuario.getRol())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamos");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Préstamos - Admin</title>
    <link rel="stylesheet" href="../recursos/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h3 class="mb-4">Gestión de Préstamos</h3>

    <table class="table table-bordered table-hover">
        <thead class="table-light">
        <tr>
            <th>ID</th>
            <th>Tipo</th>
            <th>Monto</th>
            <th>Interés</th>
            <th>Inicio</th>
            <th>Fin</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${prestamos}">
            <tr>
                <td>${p.id}</td>
                <td>${p.tipo}</td>
                <td>${p.monto}</td>
                <td>${p.interes}</td>
                <td>${p.fechaInicio}</td>
                <td>${p.fechaFin}</td>
                <td>${p.estado}</td>
                <td>
                    <form action="gestionar-prestamo" method="post" class="d-flex gap-1">
                        <input type="hidden" name="idPrestamo" value="${p.id}" />
                        <button name="accion" value="aprobar" class="btn btn-success btn-sm">Aprobar</button>
                        <button name="accion" value="rechazar" class="btn btn-danger btn-sm">Rechazar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>