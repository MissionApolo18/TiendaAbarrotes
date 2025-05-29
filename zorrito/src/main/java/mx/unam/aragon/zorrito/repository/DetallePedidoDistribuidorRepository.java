package mx.unam.aragon.zorrito.repository;

import mx.unam.aragon.zorrito.modelo.DetallePedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.PedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetallePedidoDistribuidorRepository extends JpaRepository<DetallePedidoDistribuidor, Long> {
    List<DetallePedidoDistribuidor> findByPedidoDistribuidor(PedidoDistribuidor pedido);
}
