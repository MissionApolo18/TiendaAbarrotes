package mx.unam.aragon.zorrito.service.MetodoPago;

import mx.unam.aragon.zorrito.modelo.Distribuidor;
import mx.unam.aragon.zorrito.modelo.MetodoPago;

import java.util.List;

public interface MetodoPagoService {
    MetodoPago save(MetodoPago metodoPago);
    List<MetodoPago> findAll();
    void deleteById(Long id);
    MetodoPago findById(Long id);
}
