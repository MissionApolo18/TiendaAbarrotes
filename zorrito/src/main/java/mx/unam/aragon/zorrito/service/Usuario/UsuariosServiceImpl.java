package mx.unam.aragon.zorrito.service.Usuario;

import mx.unam.aragon.zorrito.modelo.Tarjeta;
import mx.unam.aragon.zorrito.modelo.Usuarios;
import mx.unam.aragon.zorrito.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosServiceImpl implements UsuariosService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Usuarios save(Usuarios usuarios) {
        return usuarioRepository.save(usuarios);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuarios> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuarios findById(Long id) {
        Optional<Usuarios> op = usuarioRepository.findById(id);
        return op.orElse(null);
    }
}
