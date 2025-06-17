<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel de Administración</title>
    <link rel="stylesheet" href="../recursos/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-center">Bienvenido al Panel del Administrador</h2>

    <div class="row row-cols-1 row-cols-md-2 g-4 mt-4 justify-content-center">

        <!-- Aportaciones -->
        <div class="col">
            <div class="card h-100 shadow">
                <div class="card-body text-center">
                    <h5 class="card-title">Aportaciones</h5>
                    <a href="aportaciones.jsp" class="btn btn-success mt-2">Gestionar</a>
                </div>
            </div>
        </div>

        <!-- Resumen Financiero -->
        <div class="col">
            <div class="card h-100 shadow">
                <div class="card-body text-center">
                    <h5 class="card-title">Resumen Financiero</h5>
                    <a href="resumen.jsp" class="btn btn-dark mt-2">Ver</a>
                </div>
            </div>
        </div>

        <!-- Gestión de Usuarios -->
        <div class="col">
            <div class="card h-100 shadow">
                <div class="card-body text-center">
                    <h5 class="card-title">Gestión de Usuarios</h5>
                    <a href="gestion_usuarios.jsp" class="btn btn-warning mt-2">Administrar</a>
                </div>
            </div>
        </div>

        <!-- Crear Usuario -->
        <div class="col">
            <div class="card h-100 shadow">
                <div class="card-body text-center">
                    <h5 class="card-title">Crear Nuevo Usuario</h5>
                    <a href="crear_usuario.jsp" class="btn btn-outline-success mt-2">Registrar</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
