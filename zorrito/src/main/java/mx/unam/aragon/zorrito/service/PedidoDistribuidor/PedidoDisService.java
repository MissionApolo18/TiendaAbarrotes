package mx.unam.aragon.zorrito.service.PedidoDistribuidor;

import mx.unam.aragon.zorrito.modelo.DetallePedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.PedidoDistribuidor;

import java.util.List;

public interface PedidoDisService {
    PedidoDistribuidor save(PedidoDistribuidor pedidoDistribuidor);
    List<PedidoDistribuidor> findAll();
    void deleteById(Long id);
    PedidoDistribuidor findById(Long id);


}
