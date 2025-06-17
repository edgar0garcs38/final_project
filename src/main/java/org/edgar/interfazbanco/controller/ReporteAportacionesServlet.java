package org.edgar.interfazbanco.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.edgar.interfazbanco.models.Aportacion;
import org.edgar.interfazbanco.models.Usuario;
import org.edgar.interfazbanco.service.AportacionServiceSessionImplement;

import java.io.IOException;
import java.util.List;

@WebServlet("/reporte-aportaciones")
public class ReporteAportacionesServlet extends HttpServlet {

    private final AportacionServiceSessionImplement aportacionService = new AportacionServiceSessionImplement();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            String cedulaUsuario = usuario.getCedula(); // ✅ Se usa la cédula
            List<Aportacion> aportaciones = aportacionService.obtenerAportacionesPorUsuario(cedulaUsuario);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=reporte_aportaciones.pdf");

            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Título
            document.add(new Paragraph("Reporte de Aportaciones", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            document.add(new Paragraph("Usuario: " + usuario.getNombres() + " " + usuario.getApellidos()));
            document.add(new Paragraph("Cédula: " + usuario.getCedula()));
            document.add(new Paragraph(" "));

            // Tabla
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 2, 3});

            table.addCell("Tipo de Aportación");
            table.addCell("Monto");
            table.addCell("Fecha");

            for (Aportacion a : aportaciones) {
                table.addCell(a.getTipo());
                table.addCell(String.format("%.2f", a.getMonto()));
                table.addCell(a.getFecha().toString());
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar el PDF.");
        }
    }
}
