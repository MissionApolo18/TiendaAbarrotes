package mx.unam.aragon.zorrito.service.DetallePedidoDis;

import mx.unam.aragon.zorrito.modelo.DetallePedidoDistribuidor;

import java.util.List;

public interface DetallePedidoDistribuidorService {
    DetallePedidoDistribuidor save(DetallePedidoDistribuidor detallePedido);
    List<DetallePedidoDistribuidor> findAll();
    void deleteById(Long id);
    DetallePedidoDistribuidor findById(Long id);
}
