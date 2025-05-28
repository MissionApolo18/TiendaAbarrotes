package mx.unam.aragon.zorrito.repository;

import mx.unam.aragon.zorrito.modelo.DetallePedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.PedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoDistribuidorRepository extends JpaRepository<DetallePedidoDistribuidor, Long> {
	boolean existsByIdpedidoDisAndIdProductoDist(PedidoDistribuidor idpedidoDis, Producto idProductoDist);
}
