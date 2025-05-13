package mx.unam.aragon.zorrito.service.CorteInventario;

import mx.unam.aragon.zorrito.modelo.CorteInventario;

import java.util.List;

public interface CorteInventarioService {
    CorteInventario save(CorteInventario corteInventario);
    List<CorteInventario> findAll();
    void deleteById(Long id);
    CorteInventario findById(Long id);
}
