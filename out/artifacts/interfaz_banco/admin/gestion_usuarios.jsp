<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.edgar.interfazbanco.models.Usuario" %>
<%
    Usuario admin = (Usuario) session.getAttribute("usuarioLogueado");
    if (admin == null || !"admin".equalsIgnoreCase(admin.getRol())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Usuarios</title>
    <link rel="stylesheet" href="../recursos/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h3 class="mb-4">Gestión de Usuarios</h3>

    <!-- Formulario para crear nuevo usuario -->
    <form action="crear-usuario" method="post" class="card p-3 shadow-sm mb-4">
        <h5>Nuevo Usuario</h5>
        <div class="row g-2">
            <div class="col-md-3">
                <input type="text" name="cedula" class="form-control" placeholder="Cédula" required>
            </div>
            <div class="col-md-3">
                <input type="text" name="nombres" class="form-control" placeholder="Nombres" required>
            </div>
            <div class="col-md-3">
                <input type="text" name="apellidos" class="form-control" placeholder="Apellidos" required>
            </div>
            <div class="col-md-2">
                <input type="password" name="clave" class="form-control" placeholder="Contraseña" required>
            </div>
            <div class="col-md-1">
                <select name="rol" class="form-select">
                    <option value="cliente">Cliente</option>
                    <option value="socio">Socio</option>
                    <option value="admin">Admin</option>
                </select>
            </div>
        </div>
        <div class="mt-3">
            <button class="btn btn-primary">Crear Usuario</button>
        </div>
    </form>

    <!-- Tabla de usuarios -->
    <table class="table table-striped table-hover">
        <thead class="table-light">
        <tr>
            <th>Cédula</th>
            <th>Nombres</th>
            <th>Apellidos</th>
            <th>Rol</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${usuarios}">
            <tr>
                <td>${u.cedula}</td>
                <td>${u.nombres}</td>
                <td>${u.apellidos}</td>
                <td>${u.rol}</td>
                <td>
                    <form action="eliminar-usuario" method="post" onsubmit="return confirm('¿Eliminar usuario?')" style="display:inline;">
                        <input type="hidden" name="cedula" value="${u.cedula}" />
                        <button class="btn btn-sm btn-danger">Eliminar</button>
                    </form>
                    <!-- Edición opcional con modal -->
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
