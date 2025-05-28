package mx.unam.aragon.zorrito.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaDto {

    private Long idCliente;
    private Long idUsuario;  // quien hace la venta (cajero)
    private Long idMetodoPago;
    private String telefonoCliente;
    private List<ItemVentaDto> items = new ArrayList<>();

    private Double total; // opcional, puede calcularse en el backend

}
