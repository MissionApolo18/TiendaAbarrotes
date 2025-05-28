package mx.unam.aragon.zorrito.controllers;

import mx.unam.aragon.zorrito.dto.HistorialVentaDto;
import mx.unam.aragon.zorrito.modelo.CorteInventario;
import mx.unam.aragon.zorrito.service.CorteInventario.CorteInventarioService;
import mx.unam.aragon.zorrito.service.Venta.VentaService;
import mx.unam.aragon.zorrito.service.reportes.ReporteExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/reporte")
public class ReporteExcelController {
    @Autowired
    private ReporteExcelService reporteExcelService;

    @Autowired
    private CorteInventarioService corteInventarioService;

    @Autowired
    private VentaService ventaService;


    @GetMapping("/excel")
    public ResponseEntity<InputStreamResource> descargarReporteExcel() throws IOException {
        List<CorteInventario> listaCorte = corteInventarioService.obtenerListaCorte();
        List<HistorialVentaDto> historial = ventaService.obtenerHistorialVentas();

        ByteArrayInputStream in = reporteExcelService.generarReporte(listaCorte, historial);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=reporte_inventario_ventas.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }
}
