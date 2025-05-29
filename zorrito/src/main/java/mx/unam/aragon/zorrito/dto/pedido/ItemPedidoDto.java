package mx.unam.aragon.zorrito.dto.pedido;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPedidoDto {
    private Long idProducto;
    private Integer cantidad;
    private Double precioUnitario; // opcional si quieres pasar el precio
    // nuevo campo para nombre del producto
    private String nombreProducto;

    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
}