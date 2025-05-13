package mx.unam.aragon.zorrito.service.Tarjeta;

import mx.unam.aragon.zorrito.modelo.Rol;
import mx.unam.aragon.zorrito.modelo.Tarjeta;

import java.util.List;

public interface TarjetaService {
    Tarjeta save(Tarjeta tarjeta);
    List<Tarjeta> findAll();
    void deleteById(Long id);
    Tarjeta findById(Long id);
}
