package mx.unam.aragon.zorrito.dto.pedido;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistorialPedidoDto {
    private String nombreProveedor;
    private LocalDate fecha;
    private List<String> productosYcantidades;

}
