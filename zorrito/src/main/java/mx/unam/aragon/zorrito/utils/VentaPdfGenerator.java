package mx.unam.aragon.zorrito.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import mx.unam.aragon.zorrito.dto.ItemVentaDto;
import mx.unam.aragon.zorrito.modelo.Producto;
import mx.unam.aragon.zorrito.modelo.Venta;
import mx.unam.aragon.zorrito.service.Producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class VentaPdfGenerator {

    public static ByteArrayInputStream generarPdf(Venta venta, List<ItemVentaDto> items, String clienteNombre, String metodoPago, double total) {
        
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titulo = new Paragraph("Ticket de Venta", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Cliente: " + clienteNombre));
            document.add(new Paragraph("Método de pago: " + metodoPago));
            document.add(new Paragraph("Fecha: " + venta.getFecha()));
            document.add(new Paragraph("Cajero: " + venta.getIdUsuarioVenta().getNombreUsuario()));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.addCell("Producto");
            table.addCell("Cantidad");
            table.addCell("Precio Unitario");
            table.addCell("Subtotal");

            for (ItemVentaDto item : items) {
                table.addCell(item.getNombreProducto());
                table.addCell(String.valueOf(item.getCantidad()));
                table.addCell("$" + item.getPrecioUnitario());
                double subtotal = item.getPrecioUnitario() * item.getCantidad();
                table.addCell("$" + String.format("%.2f", subtotal));
            }

            document.add(table);
            document.add(new Paragraph(" "));
            Paragraph totalParrafo = new Paragraph("Total pagado: $" + String.format("%.2f", total));
            totalParrafo.setAlignment(Element.ALIGN_RIGHT);
            document.add(totalParrafo);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
