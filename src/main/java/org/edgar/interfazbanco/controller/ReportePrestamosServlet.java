package org.edgar.interfazbanco.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.edgar.interfazbanco.models.Prestamo;
import org.edgar.interfazbanco.models.Usuario;
import org.edgar.interfazbanco.service.PrestamoServiceSessionImplement;

import java.io.IOException;
import java.util.List;

@WebServlet("/reporte-prestamos")
public class ReportePrestamosServlet extends HttpServlet {

    private final PrestamoServiceSessionImplement prestamoService = new PrestamoServiceSessionImplement();

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
            List<Prestamo> prestamos = prestamoService.obtenerPrestamosPorUsuario(usuario.getCedula());

            // Configurar PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=reporte_prestamos.pdf");

            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Título
            document.add(new Paragraph("Reporte de Préstamos", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            document.add(new Paragraph("Usuario: " + usuario.getNombres() + " " + usuario.getApellidos()));
            document.add(new Paragraph("Cédula: " + usuario.getCedula()));
            document.add(new Paragraph(" "));

            // Tabla
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{2, 2, 2, 2, 3, 2});

            table.addCell("Tipo");
            table.addCell("Monto");
            table.addCell("Interés");
            table.addCell("Plazo");
            table.addCell("Fecha Inicio");
            table.addCell("Estado");

            for (Prestamo p : prestamos) {
                table.addCell(p.getTipo());
                table.addCell(String.valueOf(p.getMonto()));
                table.addCell(String.valueOf(p.getInteres()));
                table.addCell(String.valueOf(p.getFechaFin().getMonthValue() - p.getFechaInicio().getMonthValue()) + " meses");
                table.addCell(p.getFechaInicio().toString());
                table.addCell(p.getEstado());
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar el PDF");
        }
    }
}
