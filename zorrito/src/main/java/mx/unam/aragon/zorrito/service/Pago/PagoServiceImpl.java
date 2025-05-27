package mx.unam.aragon.zorrito.service.Pago;

import mx.unam.aragon.zorrito.modelo.MetodoPago;
import mx.unam.aragon.zorrito.modelo.Pago;
import mx.unam.aragon.zorrito.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl  implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    @Transactional
    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        pagoRepository.deleteById(id);
    }

    @Override
    public Pago findById(Long id) {
        Optional<Pago> op = pagoRepository.findById(id);
        return op.orElse(null);
    }

    @Override
    public Pago findByIdVenta(Long idVenta) {
        return pagoRepository.findByIdVenta(idVenta);
    }
}
