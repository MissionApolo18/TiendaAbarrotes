package mx.unam.aragon.zorrito.repository;

import mx.unam.aragon.zorrito.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByTelefonoCliente(String telefono);
    Optional<Cliente> findByCorreoCliente(String correoCliente);
}
