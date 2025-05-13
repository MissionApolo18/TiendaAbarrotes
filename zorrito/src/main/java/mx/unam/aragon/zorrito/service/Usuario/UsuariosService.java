package mx.unam.aragon.zorrito.service.Usuario;

import mx.unam.aragon.zorrito.modelo.Tarjeta;
import mx.unam.aragon.zorrito.modelo.Usuarios;

import java.util.List;

public interface UsuariosService {
    Usuarios save(Usuarios usuarios);
    List<Usuarios> findAll();
    void deleteById(Long id);
    Usuarios findById(Long id);
}
