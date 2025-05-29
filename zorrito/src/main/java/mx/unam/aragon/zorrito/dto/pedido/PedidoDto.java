package mx.unam.aragon.zorrito.dto.pedido;

import lombok.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDto {
    private Long idDistribuidor;
    private List<ItemPedidoDto> items;

    private Long idPedido;           // id del pedido generado
    private String correoProveedor;  // email del proveedor
    private String nombreProveedor;  // nombre del proveedor
    private Date fecha;

    // getter y setter para idDistribuidor
    public Long getIdDistribuidor() { return idDistribuidor; }
    public void setIdDistribuidor(Long idDistribuidor) { this.idDistribuidor = idDistribuidor; }

    // getter y setter para items
    public List<ItemPedidoDto> getItems() { return items; }
    public void setItems(List<ItemPedidoDto> items) { this.items = items; }
}
