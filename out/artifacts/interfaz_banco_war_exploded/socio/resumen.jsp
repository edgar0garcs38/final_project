<%@ page import="org.edgar.interfazbanco.models.ResumenFinanciero" %>
<%@ page import="org.edgar.interfazbanco.models.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuario == null || (!"socio".equals(usuario.getRol()) && !"cliente".equals(usuario.getRol()))) {
        response.sendRedirect("../login.jsp");
        return;
    }

    ResumenFinanciero resumen = (ResumenFinanciero) request.getAttribute("resumen");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mi Resumen</title>
    <link rel="stylesheet" href="../recursos/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h3>Resumen Financiero</h3>
    <table class="table table-bordered mt-4">
        <tr>
            <th>Total Aportado</th>
            <td>$${resumen.totalAportado}</td>
        </tr>
        <tr>
            <th>Préstamos Activos</th>
            <td>${resumen.cantidadPrestamos}</td>
        </tr>
        <tr>
            <th>Deuda Total</th>
            <td>$${resumen.totalPrestamos}</td>
        </tr>
        <tr>
            <th>Total Intereses</th>
            <td>$${resumen.totalInteres}</td>
        </tr>
    </table>
</div>
</body>
</html>
