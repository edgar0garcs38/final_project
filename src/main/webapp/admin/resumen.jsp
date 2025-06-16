<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.edgar.interfazbanco.models.ResumenFinanciero" %>
<%@ page import="org.edgar.interfazbanco.models.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuario == null || !"admin".equalsIgnoreCase(usuario.getRol())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    ResumenFinanciero resumen = (ResumenFinanciero) request.getAttribute("resumen");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resumen Financiero</title>
    <link rel="stylesheet" href="../recursos/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h3>Resumen Financiero del Sistema</h3>
    <table class="table table-bordered mt-4">
        <tr>
            <th>Total Aportado</th>
            <td>$${resumen.totalAportado}</td>
        </tr>
        <tr>
            <th>Total en Préstamos</th>
            <td>$${resumen.totalPrestamos}</td>
        </tr>
        <tr>
            <th>Total en Intereses</th>
            <td>$${resumen.totalInteres}</td>
        </tr>
        <tr>
            <th>Cantidad de Préstamos</th>
            <td>${resumen.cantidadPrestamos}</td>
        </tr>
    </table>
</div>
</body>
</html>
