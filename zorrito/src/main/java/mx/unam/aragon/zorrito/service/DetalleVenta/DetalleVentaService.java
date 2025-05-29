package mx.unam.aragon.zorrito.service.DetalleVenta;

import mx.unam.aragon.zorrito.dto.ItemVentaDto;
import mx.unam.aragon.zorrito.modelo.DetallePedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.DetalleVenta;
import mx.unam.aragon.zorrito.modelo.Venta;

import java.util.List;

public interface DetalleVentaService {
    DetalleVenta save(DetalleVenta detalleVenta);
    List<DetalleVenta> findAll();
    List<DetalleVenta> findByVentas(Venta venta);
    void deleteById(Long id);
    DetalleVenta findById(Long id);
    public List<ItemVentaDto> obtenerItemsDeVenta(Long idVenta);
}
