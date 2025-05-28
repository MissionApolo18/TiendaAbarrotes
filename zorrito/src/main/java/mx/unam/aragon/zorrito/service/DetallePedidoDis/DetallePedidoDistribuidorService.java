package mx.unam.aragon.zorrito.service.DetallePedidoDis;

import mx.unam.aragon.zorrito.modelo.DetallePedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.PedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.Producto;

import java.util.List;

public interface DetallePedidoDistribuidorService {
    DetallePedidoDistribuidor save(DetallePedidoDistribuidor detallePedido);
    List<DetallePedidoDistribuidor> findAll();
    void deleteById(Long id);
    DetallePedidoDistribuidor findById(Long id);
    boolean existsByPedidoAndProducto(PedidoDistribuidor pedido, Producto producto);
    
}
