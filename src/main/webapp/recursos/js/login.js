document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("loginForm");
    const mensaje = document.getElementById("mensajeError");

    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const cedula = document.getElementById("cedula").value;
        const clave = document.getElementById("clave").value;

        console.log("⏳ Enviando login con:", cedula, clave);

        fetch("login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: "cedula=" + encodeURIComponent(cedula) + "&clave=" + encodeURIComponent(clave)
        })
            .then((response) => {
                console.log("📩 Respuesta recibida. Redirected:", response.redirected, "Status:", response.status);
                if (response.redirected) {
                    window.location.href = response.url;
                    return null;
                } else {
                    return response.text();
                }
            })
            .then((text) => {
                if (text) {
                    console.log("📄 Respuesta textual:", text);
                    mensaje.textContent = "Credenciales incorrectas.";
                    mensaje.style.display = "block";
                }
            })
            .catch((error) => {
                console.error("❌ Error AJAX:", error);
                mensaje.textContent = "Error de conexión con el servidor.";
                mensaje.style.display = "block";
            });
    });
});
