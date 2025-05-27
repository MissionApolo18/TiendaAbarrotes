package mx.unam.aragon.zorrito.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistorialVentaDto {
    private String nombreCajero;
    private String nombreCliente;
    private Date fechaVenta;
    private List<String> productosYcantidades; // Ej. "Producto A (2)"
    private Double totalPagado;
}
