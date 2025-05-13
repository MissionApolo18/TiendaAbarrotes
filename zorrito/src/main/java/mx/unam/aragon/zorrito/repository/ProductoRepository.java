package mx.unam.aragon.zorrito.repository;

import mx.unam.aragon.zorrito.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
