package mx.unam.aragon.zorrito.repository;

import mx.unam.aragon.zorrito.modelo.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    Pago findByIdVenta(Long idVenta);
}
