package mx.unam.aragon.zorrito.service.MetodoPago;

import mx.unam.aragon.zorrito.modelo.Distribuidor;
import mx.unam.aragon.zorrito.modelo.MetodoPago;
import mx.unam.aragon.zorrito.repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MetodoPagoServiceImpl  implements MetodoPagoService{
    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Override
    @Transactional
    public MetodoPago save(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MetodoPago> findAll() {
        return metodoPagoRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        metodoPagoRepository.deleteById(id);
    }

    @Override
    public MetodoPago findById(Long id) {
        Optional<MetodoPago> op = metodoPagoRepository.findById(id);
        return op.orElse(null);
    }
}
