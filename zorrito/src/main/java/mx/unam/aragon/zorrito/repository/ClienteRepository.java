package mx.unam.aragon.zorrito.repository;

import mx.unam.aragon.zorrito.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
