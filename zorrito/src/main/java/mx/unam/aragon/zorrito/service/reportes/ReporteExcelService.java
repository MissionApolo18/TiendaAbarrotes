package mx.unam.aragon.zorrito.service.reportes;

import mx.unam.aragon.zorrito.dto.HistorialVentaDto;
import mx.unam.aragon.zorrito.modelo.CorteInventario;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReporteExcelService {
    public ByteArrayInputStream generarReporte(List<CorteInventario> cortes, List<HistorialVentaDto> historial) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            // Hoja 1: Lista Corte
            Sheet hojaCorte = workbook.createSheet("Lista Corte");
            Row encabezadoCorte = hojaCorte.createRow(0);
            encabezadoCorte.createCell(0).setCellValue("Fecha");
            encabezadoCorte.createCell(1).setCellValue("Tipo");
            encabezadoCorte.createCell(2).setCellValue("Producto");
            encabezadoCorte.createCell(3).setCellValue("Cantidad");

            int rowCorte = 1;
            for (CorteInventario corte : cortes) {
                Row fila = hojaCorte.createRow(rowCorte++);
                fila.createCell(0).setCellValue(corte.getFecha().toString());
                fila.createCell(1).setCellValue(corte.getTipo().name());
                fila.createCell(2).setCellValue(corte.getProducto().getNombreProducto());
                fila.createCell(3).setCellValue(corte.getCantidad());
            }

            // Hoja 2: Historial de ventas
            Sheet hojaVentas = workbook.createSheet("Historial Ventas");
            Row encabezadoVentas = hojaVentas.createRow(0);
            encabezadoVentas.createCell(0).setCellValue("Cajero");
            encabezadoVentas.createCell(1).setCellValue("Cliente");
            encabezadoVentas.createCell(2).setCellValue("Fecha");
            encabezadoVentas.createCell(3).setCellValue("Productos y Cantidades");
            encabezadoVentas.createCell(4).setCellValue("Total");

            int rowVenta = 1;
            for (HistorialVentaDto venta : historial) {
                Row fila = hojaVentas.createRow(rowVenta++);
                fila.createCell(0).setCellValue(venta.getNombreCajero());
                fila.createCell(1).setCellValue(venta.getNombreCliente());
                fila.createCell(2).setCellValue(venta.getFechaVenta().toString());
                fila.createCell(3).setCellValue(String.join(", ", venta.getProductosYcantidades()));
                fila.createCell(4).setCellValue(venta.getTotalPagado());
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
