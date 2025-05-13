package mx.unam.aragon.zorrito.service.Distribuidor;

import mx.unam.aragon.zorrito.modelo.DetalleVenta;
import mx.unam.aragon.zorrito.modelo.Distribuidor;

import java.util.List;

public interface DistribuidorService {
    Distribuidor save(Distribuidor distribuidor);
    List<Distribuidor> findAll();
    void deleteById(Long id);
    Distribuidor findById(Long id);
}
