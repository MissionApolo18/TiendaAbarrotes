package mx.unam.aragon.zorrito.service.CorteInventario;

import mx.unam.aragon.zorrito.modelo.CorteInventario;
import mx.unam.aragon.zorrito.repository.CorteInventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CorteInventarioServiceImpl implements CorteInventarioService {
    @Autowired
    private CorteInventarioRepository corteInventarioRepository;

    @Override
    @Transactional
    public CorteInventario save(CorteInventario corteInventario) {
        return corteInventarioRepository.save(corteInventario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CorteInventario> findAll() {
        return corteInventarioRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        corteInventarioRepository.deleteById(id);
    }

    @Override
    public CorteInventario findById(Long id) {
        Optional<CorteInventario> op = corteInventarioRepository.findById(id);
        return op.orElse(null);
    }
}
