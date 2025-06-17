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
    if (usuarios == null) {
        response.sendRedirect("gestion-usuarios"); // Reenvía al servlet si se entra directo
        return;
    }
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

    <%
        String mensaje = request.getParameter("mensaje");
        if ("ok".equals(mensaje)) {
    %>
    <div class="alert alert-success">Usuario creado correctamente.</div>
    <%
    } else if ("error".equals(mensaje)) {
    %>
    <div class="alert alert-danger">No se pudo crear el usuario.</div>
    <%
        }
    %>

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
        <%
            if (usuarios != null) {
                for (Usuario u : usuarios) {
        %>
        <tr>
            <td><%= u.getCedula() %></td>
            <td><%= u.getNombres() %></td>
            <td><%= u.getApellidos() %></td>
            <td><%= u.getRol() %></td>
            <td>
                <form action="../eliminar-usuario" method="post" onsubmit="return confirm('¿Eliminar usuario?')" style="display:inline;">
                    <input type="hidden" name="cedula" value="<%= u.getCedula() %>" />
                    <button class="btn btn-sm btn-danger">Eliminar</button>
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
