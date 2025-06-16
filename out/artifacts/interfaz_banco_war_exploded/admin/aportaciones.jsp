<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.edgar.interfazbanco.models.Aportacion" %>
<%@ page import="org.edgar.interfazbanco.models.Usuario" %>
<%@ page import="java.util.List" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuario == null || !"admin".equalsIgnoreCase(usuario.getRol())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    List<Aportacion> aportaciones = (List<Aportacion>) request.getAttribute("aportaciones");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Aportaciones</title>
    <link rel="stylesheet" href="../recursos/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h3 class="mb-4">Aportaciones registradas</h3>
    <table class="table table-striped table-bordered">
        <thead class="table-light">
        <tr>
            <th>ID</th>
            <th>ID Usuario</th>
            <th>Tipo</th>
            <th>Monto</th>
            <th>Fecha</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="a" items="${aportaciones}">
            <tr>
                <td>${a.id}</td>
                <td>${a.idUsuario}</td>
                <td>${a.tipo}</td>
                <td>${a.monto}</td>
                <td>${a.fecha}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
