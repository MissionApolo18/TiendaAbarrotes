package mx.unam.aragon.zorrito.repository;

import mx.unam.aragon.zorrito.modelo.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    Usuarios findByUsername(String username);
}
