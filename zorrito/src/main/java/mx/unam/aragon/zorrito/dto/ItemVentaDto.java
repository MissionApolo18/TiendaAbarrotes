package mx.unam.aragon.zorrito.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemVentaDto {
    private Long idProducto;
    private Integer cantidad;
    private Double precioUnitario;
}
