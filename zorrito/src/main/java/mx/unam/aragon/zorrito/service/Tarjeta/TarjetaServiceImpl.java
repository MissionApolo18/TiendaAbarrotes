package mx.unam.aragon.zorrito.service.Tarjeta;

import mx.unam.aragon.zorrito.modelo.Rol;
import mx.unam.aragon.zorrito.modelo.Tarjeta;
import mx.unam.aragon.zorrito.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaServiceImpl implements TarjetaService {
    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Override
    @Transactional
    public Tarjeta save(Tarjeta tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tarjeta> findAll() {
        return tarjetaRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        tarjetaRepository.deleteById(id);
    }

    @Override
    public Tarjeta findById(Long id) {
        Optional<Tarjeta> op = tarjetaRepository.findById(id);
        return op.orElse(null);
    }
}
