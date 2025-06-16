<%--
  Created by IntelliJ IDEA.
  User: israe
  Date: 4/6/2025
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Acceso - Banco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosLogin.css">
</head>
<body>

<div class="container">
    <div class="left">
        <h1 class="fw-bold">¡Donde puedes CRECER!</h1>
        <p class="text-muted">El banco que cree en tu futuro</p>
    </div>

    <div class="right">
        <div class="form-box">
            <div class="logo d-flex align-items-center mb-3">
                <img src="<%= request.getContextPath() %>/img/logo.png" alt="logo banco" class="me-2">
                <h2>BANCO ESTUDIANTIL</h2>
            </div>

            <div class="toggle-buttons">
                <button id="btnLogin" class="active" onclick="toggleForm('login')">Iniciar Sesión</button>
                <button id="btnRegister" onclick="toggleForm('register')">Crear Cuenta</button>
            </div>

            <!-- Formulario Login -->
            <form id="loginForm" class="active" action="<%= request.getContextPath() %>/login" method="post">
                <div class="form-group">
                    <label for="loginEmail">Correo electrónico</label>
                    <input type="email" id="loginEmail" name="email" required>
                </div>
                <div class="form-group">
                    <label for="loginPassword">Contraseña</label>
                    <input type="password" id="loginPassword" name="password" required>
                </div>
                <button type="submit" class="submit-btn">Acceder</button>
                <div class="link mt-2">
                    <a href="#">¿Olvidó su contraseña?</a>
                </div>
            </form>

            <!-- Formulario Registro de Usuario Nuevo -->
            <form id="registerForm" action="<%= request.getContextPath() %>/registro" method="post">
                <div class="form-group">
                    <label for="regNombres">Nombre</label>
                    <input type="text" id="regNombre" name="nombre" required>
                </div>
                <div class="form-group">
                    <label for="regApellidos">Apellido</label>
                    <input type="text" id="regApellido" name="apellido" required>
                </div>
                <div class="form-group">
                    <label for="regCedula">Cédula</label>
                    <input type="text" id="regCedula" name="cedula" required>
                </div>
                <div class="form-group">
                    <label for="regEmail">Correo electrónico</label>
                    <input type="email" id="regEmail" name="email" required>
                </div>
                <div class="form-group">
                    <label for="regPassword">Contraseña</label>
                    <input type="password" id="regPassword" name="password" required>
                </div>
                <button type="submit" class="submit-btn">Crear Cuenta</button>
            </form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/Js/Login.js"></script>

</body>
</html>