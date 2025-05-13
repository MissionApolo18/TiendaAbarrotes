package mx.unam.aragon.zorrito.service.Distribuidor;

import mx.unam.aragon.zorrito.modelo.DetalleVenta;
import mx.unam.aragon.zorrito.modelo.Distribuidor;
import mx.unam.aragon.zorrito.repository.DistribuidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DistribuidorSeriveImpl implements DistribuidorService {

    @Autowired
    private DistribuidorRepository distribuidorRepository;

    @Override
    @Transactional
    public Distribuidor save(Distribuidor distribuidor) {
        return distribuidorRepository.save(distribuidor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Distribuidor> findAll() {
        return distribuidorRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        distribuidorRepository.deleteById(id);
    }

    @Override
    public Distribuidor findById(Long id) {
        Optional<Distribuidor> op = distribuidorRepository.findById(id);
        return op.orElse(null);
    }
}
