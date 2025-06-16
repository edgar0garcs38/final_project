<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Cliente</title>
    <link rel="stylesheet" href="../recursos/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Bienvenido al Panel del Cliente</h2>
    <p class="text-center">Desde aquí puedes revisar tus préstamos y aportaciones.</p>

    <div class="d-grid gap-2 col-6 mx-auto mt-4">
        <a href="prestamos.jsp" class="btn btn-outline-primary">Ver Préstamos</a>
        <a href="aportaciones.jsp" class="btn btn-outline-success">Ver Aportaciones</a>
        <a href="resumen.jsp" class="btn btn-outline-dark">Resumen Financiero</a>
    </div>
</div>
</body>
</html>
