package mx.unam.aragon.zorrito.service.Pago;

import mx.unam.aragon.zorrito.modelo.MetodoPago;
import mx.unam.aragon.zorrito.modelo.Pago;

import java.util.List;

public interface PagoService {
    Pago save(Pago pago);
    List<Pago> findAll();
    void deleteById(Long id);
    Pago findById(Long id);
}
