package mx.unam.aragon.zorrito.service.Rol;

import mx.unam.aragon.zorrito.modelo.Producto;
import mx.unam.aragon.zorrito.modelo.Rol;
import mx.unam.aragon.zorrito.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService{
    @Autowired
    private RolRepository rolRepository;

    @Override
    @Transactional
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        rolRepository.deleteById(id);
    }

    @Override
    public Rol findById(Long id) {
        Optional<Rol> op = rolRepository.findById(id);
        return op.orElse(null);
    }
}
