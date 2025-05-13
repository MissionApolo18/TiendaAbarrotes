package mx.unam.aragon.zorrito.service.Rol;

import mx.unam.aragon.zorrito.modelo.Producto;
import mx.unam.aragon.zorrito.modelo.Rol;

import java.util.List;

public interface RolService {
    Rol save(Rol rol);
    List<Rol> findAll();
    void deleteById(Long id);
    Rol findById(Long id);
}
