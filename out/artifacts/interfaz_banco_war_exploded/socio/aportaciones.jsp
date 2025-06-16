<%@ page import="org.edgar.interfazbanco.models.Usuario" %>
<%@ page import="org.edgar.interfazbanco.models.Aportacion" %>
<%@ page import="java.util.List" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuario == null || (!"socio".equals(usuario.getRol()) && !"cliente".equals(usuario.getRol()))) {
        response.sendRedirect("../login.jsp");
        return;
    }

    List<Aportacion> aportaciones = (List<Aportacion>) request.getAttribute("aportaciones");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mis Aportaciones</title>
    <link rel="stylesheet" href="../recursos/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h3>Registrar Aportación</h3>

    <form action="../aportacion" method="post" class="row g-3 mb-5 mt-3">
        <div class="col-md-4">
            <input type="text" name="tipo" class="form-control" placeholder="Tipo (Ej: Ahorro)" required>
        </div>
        <div class="col-md-4">
            <input type="number" name="monto" class="form-control" placeholder="Monto" required>
        </div>
        <div class="col-md-4">
            <button class="btn btn-success w-100">Registrar</button>
        </div>
    </form>

    <h4>Historial</h4>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tipo</th>
            <th>Monto</th>
            <th>Fecha</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="a" items="${aportaciones}">
            <tr>
                <td>${a.id}</td>
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
