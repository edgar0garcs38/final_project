<%@ page import="org.edgar.interfazbanco.models.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuario == null || !"cliente".equalsIgnoreCase(usuario.getRol())) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Solicitud de Préstamo</title>
    <link rel="stylesheet" href="../recursos/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h3>Solicitar Préstamo</h3>

    <form action="../prestamo" method="post" class="card p-4 shadow-sm mt-4">
        <%
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) {
        %>
        <div class="alert alert-info mt-3"><%= mensaje %></div>
        <%
            }
        %>
        <div class="mb-3">
            <label for="tipo" class="form-label">Tipo de Préstamo</label>
            <select class="form-select" id="tipo" name="tipo" required>
                <option value="">Seleccione...</option>
                <option value="Consumo">Consumo</option>
                <option value="Educativo">Educativo</option>
                <option value="Emergente">Emergente</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="monto" class="form-label">Monto</label>
            <input type="number" class="form-control" name="monto" required>
        </div>
        <div class="mb-3">
            <label for="plazo" class="form-label">Plazo (meses)</label>
            <input type="number" class="form-control" name="plazo" required>
        </div>
        <button class="btn btn-primary">Solicitar</button>
    </form>
</div>
</body>
</html>
