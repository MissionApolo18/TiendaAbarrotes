package mx.unam.aragon.zorrito.repository;

import mx.unam.aragon.zorrito.modelo.DetalleVenta;
import mx.unam.aragon.zorrito.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    List<DetalleVenta> findByVentas(Venta venta);
    List<DetalleVenta> findByVentas_IdVenta(Long idVenta);
}
