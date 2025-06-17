<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear Usuario</title>
    <link rel="stylesheet" href="../recursos/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h3 class="text-center">Registro de Nuevo Usuario</h3>

    <div class="card shadow mt-4">
        <div class="card-body">
            <form id="crearUsuarioForm">
                <div class="mb-3">
                    <label class="form-label">Cédula</label>
                    <input type="text" class="form-control" name="cedula" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Clave</label>
                    <input type="password" class="form-control" name="clave" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Rol</label>
                    <select class="form-select" name="rol" required>
                        <option value="">Seleccione un rol</option>
                        <option value="admin">Administrador</option>
                        <option value="cliente">Cliente</option>
                        <option value="socio">Socio</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label">Nombres</label>
                    <input type="text" class="form-control" name="nombres" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Apellidos</label>
                    <input type="text" class="form-control" name="apellidos" required>
                </div>
                <div id="mensaje" class="text-center mb-3"></div>
                <div class="d-grid">
                    <button type="submit" class="btn btn-success">Crear Usuario</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    document.getElementById("crearUsuarioForm").addEventListener("submit", function(e) {
        e.preventDefault();
        const form = new FormData(this);

        fetch("../crear-usuario", {
            method: "POST",
            body: new URLSearchParams(form)
        })
            .then(res => res.text())
            .then(texto => {
                const mensaje = document.getElementById("mensaje");
                mensaje.textContent = texto;
                mensaje.className = "text-success";
            })
            .catch(err => {
                const mensaje = document.getElementById("mensaje");
                mensaje.textContent = "Error en el servidor.";
                mensaje.className = "text-danger";
            });
    });
</script>
</body>
</html>
